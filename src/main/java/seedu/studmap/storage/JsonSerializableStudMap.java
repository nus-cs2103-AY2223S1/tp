package seedu.studmap.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.studmap.commons.exceptions.IllegalValueException;
import seedu.studmap.model.ReadOnlyStudMap;
import seedu.studmap.model.StudMap;
import seedu.studmap.model.student.Student;

/**
 * An Immutable StudMap that is serializable to JSON format.
 */
@JsonRootName(value = "studmap")
class JsonSerializableStudMap {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Student list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStudMap} with the given students.
     */
    @JsonCreator
    public JsonSerializableStudMap(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyStudMap} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStudMap}.
     */
    public JsonSerializableStudMap(ReadOnlyStudMap source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this student map into the model's {@code StudMap} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StudMap toModelType() throws IllegalValueException {
        StudMap studMap = new StudMap();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (studMap.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            studMap.addStudent(student);
        }
        return studMap;
    }

}
