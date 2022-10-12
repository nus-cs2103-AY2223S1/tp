package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String taskName;
    private final String taskDescription;
    private final String taskDeadline;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTask} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskName") String taskName,
                           @JsonProperty("taskDescription") String taskDeadline,
                           @JsonProperty("taskDeadline") String taskDescription,
                           @JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
        if (students != null) {
            this.students.addAll(students);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.getTaskName().taskName;
        taskDescription = source.getTaskDescription().description;
        taskDeadline = source.getTaskDeadline().deadline;
        students.addAll(source.getStudents().stream()
                .map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Student> studentList = new ArrayList<>();
        for (JsonAdaptedStudent s : students) {
            studentList.add(s.toModelType());
        }

        if (taskName == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, TaskName.class.getSimpleName()
            ));
        }
        if (!TaskName.isValidName(taskName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelName = new TaskName(taskName);

        if (taskDeadline == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, TaskDeadline.class.getSimpleName()
            ));
        }
        if (!TaskDeadline.isValidDeadline(taskDeadline)) {
            throw new IllegalValueException(TaskDeadline.MESSAGE_CONSTRAINTS);
        }
        final TaskDeadline modelDeadline = new TaskDeadline(taskDeadline);

        if (taskDescription == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, TaskDescription.class.getSimpleName()
            ));
        }

        final TaskDescription modelDescription = new TaskDescription(taskDescription);

        final Set<Student> modelStudents = new HashSet<>(studentList);
        return new Task(modelName, modelDescription, modelDeadline, modelStudents);
    }

}
