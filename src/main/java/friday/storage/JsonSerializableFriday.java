package friday.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import friday.commons.exceptions.IllegalValueException;
import friday.model.Friday;
import friday.model.ReadOnlyFriday;
import friday.model.student.Student;

/**
 * An Immutable Friday that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableFriday {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFriday} with the given students.
     */
    @JsonCreator
    public JsonSerializableFriday(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyFriday} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFriday}.
     */
    public JsonSerializableFriday(ReadOnlyFriday source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Friday} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Friday toModelType() throws IllegalValueException {
        Friday addressBook = new Friday();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (addressBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            addressBook.addStudent(student);
        }
        return addressBook;
    }

}
