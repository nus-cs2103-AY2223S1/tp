package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.level.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.NextOfKin;
import seedu.address.model.person.student.School;
import seedu.address.model.person.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String school;
    private final String level;
    private final String nextOfKin;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedTuitionClass> tuitionClasses = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("school") String school, @JsonProperty("level") String level,
                             @JsonProperty("nextOfKin") String nextOfKin,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("tuitionClasses") List<JsonAdaptedTuitionClass> tuitionClasses) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.school = school;
        this.level = level;
        this.nextOfKin = nextOfKin;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (tuitionClasses != null) {
            this.tuitionClasses.addAll(tuitionClasses);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        school = source.getSchool().school;
        level = source.getLevel().name();
        nextOfKin = source.getNextOfKin().nextOfKin;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        tuitionClasses.addAll(source.getTuitionClasses().stream()
                .map(JsonAdaptedTuitionClass::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

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

        if (school == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, School.class.getSimpleName()));
        }
        if (!School.isValidSchool(school)) {
            throw new IllegalValueException(School.MESSAGE_CONSTRAINTS);
        }
        final School modelSchool = new School(school);

        if (level == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Level.class.getSimpleName()));
        }
        if (!Level.isValidLevel(level)) {
            throw new IllegalValueException(Level.MESSAGE_CONSTRAINTS);
        }
        final Level modelLevel = Level.createLevel(level);

        if (nextOfKin == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NextOfKin.class.getSimpleName()));
        }
        if (!NextOfKin.isValidNextOfKin(nextOfKin)) {
            throw new IllegalValueException(NextOfKin.MESSAGE_CONSTRAINTS);
        }
        final NextOfKin modelNextOfKin = new NextOfKin(nextOfKin);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final List<TuitionClass> modelTuitionClasses = new ArrayList<>();
        for (JsonAdaptedTuitionClass tuitionClass: tuitionClasses) {
            modelTuitionClasses.add(tuitionClass.toModelType());
        }
        return new Student(modelName, modelPhone, modelEmail, modelAddress, modelTags,
                modelSchool, modelLevel, modelNextOfKin, modelTuitionClasses);
    }

}
