package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.record.RecordList;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String birthdate;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedRecord> recordList = new ArrayList<>();
    private final String appointment;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("birthdate") String birthdate,
                             @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                             @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("recordList") List<JsonAdaptedRecord> recordList,
                             @JsonProperty("appointment") String appointment) {
        this.name = name;
        this.birthdate = birthdate;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.appointment = appointment;

        if (recordList != null) {
            this.recordList.addAll(recordList);
        }

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().properCaseName;
        birthdate = source.getBirthdate().toString();
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        appointment = source.getAppointment().storageFormat();
        recordList.addAll(source.getRecordList().asUnmodifiableObservableList().stream()
                .map(JsonAdaptedRecord::new)
                .collect(Collectors.toList()));

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final RecordList modelRecordList = new RecordList();
        for (JsonAdaptedRecord record : recordList) {
            modelRecordList.add(record.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (birthdate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Birthdate.class.getSimpleName()));
        }
        if (!Birthdate.isValidDateFormat(birthdate)) {
            throw new IllegalValueException(Birthdate.MESSAGE_INVALID_DATE_FORMAT);
        }
        if (Birthdate.isFutureDate(birthdate)) {
            throw new IllegalValueException(Birthdate.MESSAGE_FUTURE_DATE);
        }
        final Birthdate modelBirthdate = new Birthdate(birthdate);

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
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (!Appointment.isValidAppointment(appointment)) {
            throw new IllegalValueException(Appointment.MESSAGE_CONSTRAINTS);
        }
        final Appointment modelAppointment = Appointment.of(appointment);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Person(modelName, modelBirthdate, modelPhone, modelEmail, modelAddress,
                modelTags, modelRecordList, modelAppointment);
    }

}
