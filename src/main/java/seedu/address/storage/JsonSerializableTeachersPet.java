package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTeachersPet;
import seedu.address.model.TeachersPet;
import seedu.address.model.student.Student;

/**
 * An Immutable TeachersPet that is serializable to JSON format.
 */
@JsonRootName(value = "teacherspet")
class JsonSerializableTeachersPet {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTeachersPet} with the given students.
     */
    @JsonCreator
    public JsonSerializableTeachersPet(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyTeachersPet} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTeachersPet}.
     */
    public JsonSerializableTeachersPet(ReadOnlyTeachersPet source) {
        assert source != null;
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this teacher's pet into the model's {@code TeachersPet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TeachersPet toModelType() throws IllegalValueException {
        TeachersPet teachersPet = new TeachersPet();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (teachersPet.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            teachersPet.addStudent(student);
        }
        return teachersPet;
    }

}
