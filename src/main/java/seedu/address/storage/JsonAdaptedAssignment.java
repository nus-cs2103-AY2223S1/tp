package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Assignment;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
public class JsonAdaptedAssignment extends JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment's %s field is missing!";
    private final String title;
    private final String description;
    private final List<String> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty ("title") String title,
                                 @JsonProperty ("description") String description,
                                 @JsonProperty ("students") List<String> students) {
        this.title = title;
        this.description = description;
        if (students != null) {
            this.students.addAll(students);
        }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Task source) {
        Assignment assignment = (Assignment) source;
        title = assignment.getTitle().title;
        description = assignment.getDescription().description;
        students.addAll(assignment.getStudents());
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    @Override
    public Task toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskTitle.class.getSimpleName()));
        }
        if (!TaskTitle.isValidTitle(title)) {
            throw new IllegalValueException(TaskTitle.MESSAGE_CONSTRAINTS);
        }
        final TaskTitle modelTitle = new TaskTitle(title);

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDescription.class.getSimpleName()));
        }
        if (!TaskDescription.isValidDescription(description)) {
            throw new IllegalValueException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        final TaskDescription modelDescription = new TaskDescription(description);

        final List<String> modelStudents = students;

        return new Assignment(modelTitle, modelDescription, modelStudents);
    }
}
