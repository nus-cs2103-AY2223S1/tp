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
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.StudentClass;
import seedu.address.model.person.subject.Subject;
import seedu.address.model.tag.Tag;

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
    private final List<JsonAdaptedRemark> remarks = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedSubject> subjects = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("studentClass") String studentClass,
                             @JsonProperty("remarks") List<JsonAdaptedRemark> remarks,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("subjects") List<JsonAdaptedSubject> subjects) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.studentClass = studentClass;
        if (remarks != null) {
            this.remarks.addAll(remarks);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (subjects != null) {
            this.subjects.addAll(subjects);
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

        // Remarks follow the tag system
        remarks.addAll(source.getRemarks().stream()
                             .map(JsonAdaptedRemark::new)
                             .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                            .map(JsonAdaptedTag::new)
                            .collect(Collectors.toList()));
        subjects.addAll(source.getSubjectHandler().getSubjectsTaken().stream()
                              .map(JsonAdaptedSubject::new)
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

        // So do Subjects
        final List<Subject> personSubjects = new ArrayList<>();
        for (JsonAdaptedSubject subject : subjects) {
            personSubjects.add(subject.toModelType());
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

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Set<Remark> modelRemarks = new HashSet<>(personRemarks);

        final Set<Subject> modelSubjects = new HashSet<>(personSubjects);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelStudentClass,
                          modelRemarks, modelSubjects, modelTags);
    }

}
