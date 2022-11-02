package seedu.classify.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.classify.commons.exceptions.IllegalValueException;
import seedu.classify.model.exam.Exam;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.Email;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.Phone;
import seedu.classify.model.student.Student;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String studentName;
    private final String id;
    private final String className;
    private final String parentName;
    private final String phone;
    private final String email;
    private final List<JsonAdaptedExam> exams = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("studentName") String studentName, @JsonProperty("id") String id,
                              @JsonProperty("className") String className,
                              @JsonProperty("parentName") String parentName,
                              @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                              @JsonProperty("exams") List<JsonAdaptedExam> exams) {
        this.studentName = studentName;
        this.id = id;
        this.className = className;
        this.parentName = parentName;
        this.phone = phone;
        this.email = email;
        if (exams != null) {
            this.exams.addAll(exams);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        studentName = source.getStudentName().fullName;
        id = source.getId().value;
        className = source.getClassName().className;
        parentName = source.getParentName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        exams.addAll(source.getExams().stream()
                .map(JsonAdaptedExam::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Exam> studentExams = new ArrayList<>();
        for (JsonAdaptedExam exam : exams) {
            studentExams.add(exam.toModelType());
        }

        if (studentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(studentName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelStudentName = new Name(studentName);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (!Id.isValidId(id)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        final Id modelId = new Id(id);

        if (className == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Class.class.getSimpleName()));
        }
        if (!Class.isValidClassName(className)) {
            throw new IllegalValueException(Class.MESSAGE_CONSTRAINTS);
        }
        final Class modelClassName = new Class(className);

        if (parentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!parentName.isEmpty() && !Name.isValidName(parentName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelParentName = parentName.isEmpty() ? new Name() : new Name(parentName);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!phone.isEmpty() && !Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = phone.isEmpty() ? new Phone() : new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Email.class.getSimpleName()));
        }
        if (!email.isEmpty() && !Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = email.isEmpty() ? new Email() : new Email(email);

        final Set<Exam> modelExams = new HashSet<>(studentExams);
        return new Student(modelStudentName, modelId, modelClassName, modelParentName, modelPhone,
                modelEmail, modelExams);
    }

}
