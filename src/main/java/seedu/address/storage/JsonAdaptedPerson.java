package seedu.address.storage;

import static seedu.address.model.category.Category.NURSE_SYMBOL;
import static seedu.address.model.category.Category.PATIENT_SYMBOL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.category.Category;
import seedu.address.model.person.Address;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.HomeVisit;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final Long uid;
    private final String name;
    private final String category;
    private final String gender;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedHomeVisit> homeVisits = new ArrayList<>();
    private final List<JsonAdaptedDate> unavailableDates = new ArrayList<>();
    private final List<JsonAdaptedDate> fullyAssignedDates = new ArrayList<>();
    private final List<JsonAdaptedDateSlot> dateSlots = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String pName;
    private final String pPhone;
    private final String pEmail;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("uid") Long uid, @JsonProperty("name") String name,
            @JsonProperty("category") String category,
            @JsonProperty("gender") String gender, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("dateSlots") List<JsonAdaptedDateSlot> dateSlot,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("homeVisits") List<JsonAdaptedHomeVisit> homeVisit,
            @JsonProperty("unavailableDates") List<JsonAdaptedDate> unavailableDateList,
            @JsonProperty("fullyAssignedDates") List<JsonAdaptedDate> fullyAssignedDateList,
            @JsonProperty("phys name") String pName,
            @JsonProperty("phys phone") String pPhone,
            @JsonProperty("phys email") String pEmail) {

        this.uid = uid;
        this.name = name;
        this.category = category;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;

        if (dateSlot != null) {
            this.dateSlots.addAll(dateSlot);
        }
        this.pName = Objects.requireNonNullElse(pName, "NA");
        this.pPhone = Objects.requireNonNullElse(pPhone, "NA");
        this.pEmail = Objects.requireNonNullElse(pEmail, "NA");

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }

        if (homeVisits != null) {
            this.homeVisits.addAll(homeVisit);
        }

        if (unavailableDateList != null) {
            this.unavailableDates.addAll(unavailableDateList);
        }

        if (fullyAssignedDateList != null) {
            this.fullyAssignedDates.addAll(fullyAssignedDateList);
        }

    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        category = source.getCategory().categoryName;
        boolean isPatient = category.equals(PATIENT_SYMBOL);

        if (isPatient) {
            Patient sourcePatient = (Patient) source;
            dateSlots.addAll(sourcePatient.getDatesSlots().stream()
                    .map(JsonAdaptedDateSlot::new)
                    .collect(Collectors.toList()));
            String[] physNameArr = new String[] { "NA" };
            sourcePatient.getAttendingPhysician().ifPresent(x -> physNameArr[0] = x.getName().fullName);
            pName = physNameArr[0];
            String[] physEmailArr = new String[] { "NA" };
            sourcePatient.getAttendingPhysician().ifPresent(x -> physEmailArr[0] = x.getEmail().value);
            pEmail = physEmailArr[0];
            String[] physPhoneArr = new String[] { "NA" };
            sourcePatient.getAttendingPhysician().ifPresent(x -> physPhoneArr[0] = x.getPhone().value);
            pPhone = physPhoneArr[0];
        } else {
            homeVisits.addAll(((Nurse) source).getHomeVisits().stream()
                    .map(JsonAdaptedHomeVisit::new)
                    .collect(Collectors.toList()));
            unavailableDates.addAll(((Nurse) source).getUnavailableDates().stream()
                    .map(JsonAdaptedDate::new)
                    .collect(Collectors.toList()));
            fullyAssignedDates.addAll(((Nurse) source).getFullyScheduledDates().stream()
                    .map(JsonAdaptedDate::new)
                    .collect(Collectors.toList()));
            pName = "NA";
            pPhone = "NA";
            pEmail = "NA";
        }

        uid = source.getUid().uid;
        name = source.getName().fullName;
        gender = source.getGender().gender;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final List<HomeVisit> nurseHomeVisitList = new ArrayList<>();
        for (JsonAdaptedHomeVisit homeVisit : homeVisits) {
            nurseHomeVisitList.add(homeVisit.toModelType());
        }

        final List<Date> nurseUnavailableDate = new ArrayList<>();
        for (JsonAdaptedDate date : unavailableDates) {
            nurseUnavailableDate.add(date.toModelType());
        }

        final List<Date> nurseFullySchedulledDates = new ArrayList<>();
        for (JsonAdaptedDate date : fullyAssignedDates) {
            nurseFullySchedulledDates.add(date.toModelType());
        }

        final List<DateSlot> patientHomeVisitDatesSlots = new ArrayList<>();
        for (JsonAdaptedDateSlot dateSlot : dateSlots) {
            patientHomeVisitDatesSlots.add(dateSlot.toModelType());
        }

        if (uid == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }

        final Uid modelUid = new Uid(uid);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (category == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Category.class.getSimpleName()));
        }

        if (category.equals(NURSE_SYMBOL)) {
            return new Nurse(modelUid, modelName, modelGender, modelPhone, modelEmail, modelAddress, modelTags,
                    nurseUnavailableDate, nurseHomeVisitList, nurseFullySchedulledDates);

        } else if (category.equals(PATIENT_SYMBOL)) {
            return new Patient(modelUid, modelName, modelGender, modelPhone, modelEmail,
                    modelAddress, modelTags, patientHomeVisitDatesSlots);

        } else {
            throw new IllegalValueException(Category.MESSAGE_CONSTRAINTS);

        }

    }

}
