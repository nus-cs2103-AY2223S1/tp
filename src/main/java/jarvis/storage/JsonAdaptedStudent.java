package jarvis.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.GradeProfile;
import jarvis.model.MatricNum;
import jarvis.model.Student;
import jarvis.model.StudentName;

/**
 * Jackson-friendly version of {@link Student}.
 */
public class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String matricNum;
    private final GradeProfile gradeProfile;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("matricNum") String matricNum,
                              @JsonProperty("gradeProfile") GradeProfile gradeProfile) {
        this.name = name;
        this.matricNum = matricNum;
        this.gradeProfile = gradeProfile;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        matricNum = source.getMatricNum().value;
        gradeProfile = source.getGradeProfile();
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentName.class.getSimpleName()));
        }
        if (!StudentName.isValidName(name)) {
            throw new IllegalValueException(StudentName.MESSAGE_CONSTRAINTS);
        }
        final StudentName modelStudentName = new StudentName(name);

        if (matricNum == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MatricNum.class.getSimpleName()));
        }
        if (!MatricNum.isValidMatricNum(matricNum)) {
            throw new IllegalValueException(MatricNum.MESSAGE_CONSTRAINTS);
        }
        final MatricNum modelMatricNum = new MatricNum(matricNum);

        return new Student(modelStudentName, modelMatricNum, gradeProfile);
    }

}
