package seedu.watson.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.watson.commons.exceptions.IllegalValueException;
import seedu.watson.model.person.Address;
import seedu.watson.model.person.Attendance;
import seedu.watson.model.person.Email;
import seedu.watson.model.person.Name;
import seedu.watson.model.person.Person;
import seedu.watson.model.person.Phone;
import seedu.watson.model.person.Remark;
import seedu.watson.model.person.StudentClass;
import seedu.watson.model.person.subject.SubjectHandler;
import seedu.watson.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String studentClass;
    private final String attendance;
    private final String subjectHandler;

    private final List<JsonAdaptedRemark> remarks = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedSubject> subjects = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("watson") String address,
                             @JsonProperty("studentClass") String studentClass,
                             @JsonProperty("attendance") String attendance,
                             @JsonProperty("subjectHandler") String subjectHandler,
                             @JsonProperty("remarks") List<JsonAdaptedRemark> remarks,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.studentClass = studentClass;
        this.attendance = attendance;
        this.subjectHandler = subjectHandler;
        if (remarks != null) {
            this.remarks.addAll(remarks);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        studentClass = source.getStudentClass().value;
        attendance = source.getAttendance().toString();
        subjectHandler = source.getSubjectHandler().dataString();

        // Remarks follow the tag system
        remarks.addAll(source.getRemarks().stream()
                             .map(JsonAdaptedRemark::new)
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

        // Remarks follow the tag system
        final List<Remark> personRemarks = new ArrayList<>();
        for (JsonAdaptedRemark remark : remarks) {
            personRemarks.add(remark.toModelType());
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

        if (studentClass == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentClass.class.getSimpleName()));
        }
        final StudentClass modelStudentClass = new StudentClass(studentClass);

        if (attendance == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Attendance.class.getSimpleName()));
        }
        final Attendance modelAttendance = new Attendance(Attendance.parseAttendanceFromJson(attendance));

        if (subjectHandler == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, SubjectHandler.class.getSimpleName()));
        }
        final SubjectHandler modelSubjectHandler = new SubjectHandler(subjectHandler);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Set<Remark> modelRemarks = new HashSet<>(personRemarks);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelStudentClass,
                          modelAttendance, modelRemarks, modelSubjectHandler, modelTags);
    }

}
