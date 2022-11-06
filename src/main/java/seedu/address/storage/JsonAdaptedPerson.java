package seedu.address.storage;

import static seedu.address.model.category.Category.NURSE_SYMBOL;
import static seedu.address.model.category.Category.PATIENT_SYMBOL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Physician;
import seedu.address.model.person.Uid;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    public static final String NOT_APPLICABLE = "NA";
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
    private final String nName;
    private final String nPhone;
    private final String nEmail;

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
            @JsonProperty("phys email") String pEmail,
            @JsonProperty("nok name") String nName,
            @JsonProperty("nok phone") String nPhone,
            @JsonProperty("nok email") String nEmail) {

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
        this.pName = Objects.requireNonNullElse(pName, NOT_APPLICABLE);
        this.pPhone = Objects.requireNonNullElse(pPhone, NOT_APPLICABLE);
        this.pEmail = Objects.requireNonNullElse(pEmail, NOT_APPLICABLE);
        this.nName = Objects.requireNonNullElse(nName,NOT_APPLICABLE);
        this.nPhone = Objects.requireNonNullElse(nPhone,NOT_APPLICABLE);
        this.nEmail = Objects.requireNonNullElse(nEmail,NOT_APPLICABLE);

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
            dateSlots.addAll(sourcePatient.getDatesSlots().stream().map(JsonAdaptedDateSlot::new)
                    .collect(Collectors.toList()));
            pName = getpName(sourcePatient);
            pEmail = getpEmail(sourcePatient);
            pPhone = getpPhone(sourcePatient);
            nName = getnName(sourcePatient);
            nEmail = getnEmail(sourcePatient);
            nPhone = getnPhone(sourcePatient);
        } else {
            homeVisits.addAll(((Nurse) source).getHomeVisits().stream().map(JsonAdaptedHomeVisit::new)
                    .collect(Collectors.toList()));
            unavailableDates.addAll(((Nurse) source).getUnavailableDates().stream().map(JsonAdaptedDate::new)
                    .collect(Collectors.toList()));
            fullyAssignedDates.addAll(((Nurse) source).getFullyScheduledDates().stream().map(JsonAdaptedDate::new)
                    .collect(Collectors.toList()));
            pName = NOT_APPLICABLE;
            pPhone = NOT_APPLICABLE;
            pEmail = NOT_APPLICABLE;
            nName = NOT_APPLICABLE;
            nEmail = NOT_APPLICABLE;
            nPhone = NOT_APPLICABLE;
        }

        uid = source.getUid().uid;
        name = source.getName().fullName;
        gender = source.getGender().gender;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    private String getpName(Patient sourcePatient) {
        String[] physNameArr = new String[] {NOT_APPLICABLE};
        sourcePatient.getAttendingPhysician().ifPresent(x -> physNameArr[0]
                = x.getName().fullName.substring(3));
        return physNameArr[0];
    }

    private String getpEmail(Patient sourcePatient) {
        String[] physEmailArr = new String[] { NOT_APPLICABLE };
        sourcePatient.getAttendingPhysician().ifPresent(x -> physEmailArr[0] = x.getEmail().value);
        return physEmailArr[0];
    }

    private String getpPhone(Patient sourcePatient) {
        String[] physPhoneArr = new String[] { NOT_APPLICABLE };
        sourcePatient.getAttendingPhysician().ifPresent(x -> physPhoneArr[0] = x.getPhone().value);
        return physPhoneArr[0];
    }

    private String getnName(Patient sourcePatient) {
        String[] nokNameArr = new String[] { NOT_APPLICABLE };
        sourcePatient.getNextOfKin().ifPresent(x -> nokNameArr[0] = x.getName().fullName);
        return nokNameArr[0];
    }

    private String getnEmail(Patient sourcePatient) {
        String[] nokEmailArr = new String[] { NOT_APPLICABLE };
        sourcePatient.getNextOfKin().ifPresent(x -> nokEmailArr[0] = x.getEmail().value);
        return nokEmailArr[0];
    }

    private String getnPhone(Patient sourcePatient) {
        String[] nokPhoneArr = new String[] { NOT_APPLICABLE };
        sourcePatient.getNextOfKin().ifPresent(x -> nokPhoneArr[0] = x.getPhone().value);
        return nokPhoneArr[0];
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final Uid modelUid = getModelUid();
        final Name modelName = getModelName(name, false, false);
        final Gender modelGender = getModelGender();
        final Phone modelPhone =getModelPhone(phone, false,false);
        final Email modelEmail = getModelEmail(email, false, false);
        final Address modelAddress = getModelAddress();
        final Set<Tag> modelTags = getModelTags();

        if (category == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Category.class.getSimpleName()));
        }

        if (category.equals(NURSE_SYMBOL)) {
            List<Date> modelUnavailableDates = getModelUnavailableDates();
            List<HomeVisit> modelHomeVisits = getModelHomeVisits();
            List<Date> modelFullySchedulledDates = getModelFullyScheduledDates();
            return new Nurse(modelUid, modelName, modelGender, modelPhone, modelEmail, modelAddress, modelTags,
                    modelUnavailableDates, modelHomeVisits, modelFullySchedulledDates);

        } else if (category.equals(PATIENT_SYMBOL)) {
            List<DateSlot> modelDateSlots = getModelDateSlots();
            Optional<Physician> modelPhysician = getModelPhysician();
            Optional<NextOfKin> modelNextOfKin = getModelNextOfKin();
            return new Patient(modelUid, modelName, modelGender, modelPhone, modelEmail,
                    modelAddress, modelTags, modelDateSlots, modelPhysician, modelNextOfKin);

        } else {
            throw new IllegalValueException(Category.MESSAGE_CONSTRAINTS);

        }

    }

    private Uid getModelUid() throws IllegalValueException {
        if (uid == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        return new Uid(uid);
    }

    private Name getModelName(String name, Boolean isPhysician, Boolean isNOK) throws IllegalValueException {
        String indicator = "";
        if (isPhysician) {
            indicator = "Physician's ";
        }
        if (isNOK) {
            indicator = "Next Of Kin's ";
        }
        if (name == null || name.equals(NOT_APPLICABLE)) {
            throw new IllegalValueException(indicator
                    + String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(indicator + Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    private Gender getModelGender() throws IllegalValueException {
        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(gender);
    }

    private Phone getModelPhone(String phone, Boolean isPhysician, Boolean isNOK) throws IllegalValueException {
        String indicator = "";
        if (isPhysician) {
            indicator = "Physician's ";
        }
        if (isNOK) {
            indicator = "Next Of Kin's ";
        }
        if (phone == null) {
            throw new IllegalValueException(indicator +
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(indicator + Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(phone);
    }

    private Email getModelEmail(String email, Boolean isPhysician, Boolean isNOK) throws IllegalValueException {
        String indicator = "";
        if (isPhysician) {
            indicator = "Physician's ";
        }
        if (isNOK) {
            indicator = "Next Of Kin's ";
        }
        if (email == null) {
            throw new IllegalValueException(indicator +
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(indicator + Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(email);
    }

    private Address getModelAddress() throws IllegalValueException {
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(address);
    }

    private Set<Tag> getModelTags() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        return new HashSet<>(personTags);
    }

    private List<HomeVisit> getModelHomeVisits() throws IllegalValueException {
        final List<HomeVisit> nurseHomeVisitList = new ArrayList<>();
        for (JsonAdaptedHomeVisit homeVisit : homeVisits) {
            nurseHomeVisitList.add(homeVisit.toModelType());
        }
        return nurseHomeVisitList;
    }

    private List<Date> getModelUnavailableDates() throws IllegalValueException {
        final List<Date> nurseUnavailableDate = new ArrayList<>();
        for (JsonAdaptedDate date : unavailableDates) {
            nurseUnavailableDate.add(date.toModelType());
        }
        return nurseUnavailableDate;
    }

    private List<Date> getModelFullyScheduledDates() throws IllegalValueException {
        final List<Date> nurseFullySchedulledDates = new ArrayList<>();
        for (JsonAdaptedDate date : fullyAssignedDates) {
            nurseFullySchedulledDates.add(date.toModelType());
        }
        return nurseFullySchedulledDates;
    }

    private List<DateSlot> getModelDateSlots() throws IllegalValueException {
        final List<DateSlot> patientHomeVisitDatesSlots = new ArrayList<>();
        for (JsonAdaptedDateSlot dateSlot : dateSlots) {
            patientHomeVisitDatesSlots.add(dateSlot.toModelType());
        }
        return patientHomeVisitDatesSlots;
    }

    private Optional<Physician> getModelPhysician() throws IllegalValueException {
        Boolean haspName = !pName.equals(NOT_APPLICABLE);
        Boolean haspPhone = !pPhone.equals(NOT_APPLICABLE);
        Boolean haspEmail = !pEmail.equals(NOT_APPLICABLE);
        if (!haspName && !haspPhone && !haspEmail) {
            return Optional.empty();
        } else {
            Name phyName = getModelName(pName, true, false);
            Phone phyPhone = getModelPhone(pPhone, true, false);
            Email phyEmail = getModelEmail(pEmail, true, false);
            return Optional.of(new Physician(phyName, phyPhone, phyEmail));
        }
    }

    private Optional<NextOfKin> getModelNextOfKin() throws IllegalValueException {
        Boolean hasnName = !nName.equals(NOT_APPLICABLE);
        Boolean hasnPhone = !nPhone.equals(NOT_APPLICABLE);
        Boolean hasnEmail = !nEmail.equals(NOT_APPLICABLE);
        if (!hasnName && !hasnPhone && !hasnEmail) {
            return Optional.empty();
        } else {
            Name nokName = getModelName(pName, false, true);
            Phone nokPhone = getModelPhone(pPhone, false, true);
            Email nokEmail = getModelEmail(pEmail, false, true);
            return Optional.of(new NextOfKin(nokName, nokPhone, nokEmail));
        }
    }

}
