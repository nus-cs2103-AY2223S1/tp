package seedu.masslinkers.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.masslinkers.commons.exceptions.IllegalValueException;
import seedu.masslinkers.model.MassLinkers;
import seedu.masslinkers.model.ReadOnlyMassLinkers;
import seedu.masslinkers.model.student.Student;
//@@author
/**
 * An Immutable MassLinkers that is serializable to JSON format.
 */
@JsonRootName(value = "massLinkers")
class JsonSerializableMassLinkers {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMassLinkers} with the given students.
     */
    @JsonCreator
    public JsonSerializableMassLinkers(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyMassLinkers} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMassLinkers}.
     */
    public JsonSerializableMassLinkers(ReadOnlyMassLinkers source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this mass linkers into the model's {@code MassLinkers} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MassLinkers toModelType() throws IllegalValueException {
        MassLinkers massLinkers = new MassLinkers();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (massLinkers.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            massLinkers.addStudent(student);
        }
        return massLinkers;
    }

}
