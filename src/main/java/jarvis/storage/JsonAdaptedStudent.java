package jarvis.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.MasteryCheckResult;
import jarvis.model.MasteryCheckStatus;
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
    private final boolean passMc1;
    private final boolean passMc2;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("matricNum") String matricNum,
                              @JsonProperty("passMc1") boolean passMc1, @JsonProperty("passMc2") boolean passMc2) {
        this.name = name;
        this.matricNum = matricNum;
        this.passMc1 = passMc1;
        this.passMc2 = passMc2;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        matricNum = source.getMatricNum().value;
        MasteryCheckStatus mcStatus = source.getMcStatus();
        passMc1 = mcStatus.didPassMc(1);
        passMc2 = mcStatus.didPassMc(2);
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

        MasteryCheckResult resultMc1 = new MasteryCheckResult(1, passMc1);
        MasteryCheckResult resultMc2 = new MasteryCheckResult(2, passMc2);
        return new Student(modelStudentName, modelMatricNum, new MasteryCheckStatus(resultMc1, resultMc2));
    }
}
