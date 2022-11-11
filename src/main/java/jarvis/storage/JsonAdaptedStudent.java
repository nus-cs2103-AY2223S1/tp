package jarvis.storage;

import static jarvis.commons.util.JsonUtil.checkNullArgument;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
     * @throws IllegalArgumentException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalArgumentException {
        checkNullArgument(StudentName.class, MISSING_FIELD_MESSAGE_FORMAT, name);
        final StudentName modelStudentName = new StudentName(name);

        checkNullArgument(MatricNum.class, MISSING_FIELD_MESSAGE_FORMAT, matricNum);
        final MatricNum modelMatricNum = new MatricNum(matricNum);

        return new Student(modelStudentName, modelMatricNum, gradeProfile);
    }

    /**
     * Converts a list of {@code JsonAdaptedStudent} into a list of the model's {@code Student} objects.
     *
     * @param jsonList The list of Jackson-friendly adapted student objects.
     * @return The list of the model's {@code Student} objects.
     * @throws IllegalArgumentException If there were any data constraints violated in the adapted student.
     */
    public static List<Student> toModelList(List<JsonAdaptedStudent> jsonList) throws IllegalArgumentException {
        List<Student> modelStudentList = new ArrayList<>();
        for (JsonAdaptedStudent jsonAdaptedStudent : jsonList) {
            modelStudentList.add(jsonAdaptedStudent.toModelType());
        }
        return modelStudentList;
    }
}
