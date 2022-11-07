package jeryl.fyp.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import jeryl.fyp.commons.exceptions.IllegalValueException;
import jeryl.fyp.model.FypManager;
import jeryl.fyp.model.ReadOnlyFypManager;
import jeryl.fyp.model.student.Student;

/**
 * An Immutable FypManager that is serializable to JSON format.
 */
@JsonRootName(value = "fypmanager")
class JsonSerializableFypManager {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFypManager} with the given students.
     */
    @JsonCreator
    public JsonSerializableFypManager(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyFypManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFypManager}.
     */
    public JsonSerializableFypManager(ReadOnlyFypManager source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this FYP manager into the model's {@code FypManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FypManager toModelType() throws IllegalValueException {
        FypManager fypManager = new FypManager();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (fypManager.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            fypManager.addStudent(student);
        }
        return fypManager;
    }

}
