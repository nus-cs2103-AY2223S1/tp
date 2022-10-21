package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    private final String taskName;
    private final List<JsonAdaptedPerson> assignees = new ArrayList<>();
    private boolean isComplete;
    private final String deadline;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName}.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskName") String taskName,
                           @JsonProperty("assignees") List<JsonAdaptedPerson> assignees,
                           @JsonProperty("isComplete") boolean isComplete,
                           @JsonProperty("deadline") String deadline) {
        this.taskName = taskName;
        if (assignees != null) {
            this.assignees.addAll(assignees);
        }
        this.isComplete = isComplete;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.getName();
        assignees.addAll(source.getAssigneesList().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
        isComplete = source.isComplete();
        deadline = source.getDeadlineStorage();
    }

    @JsonValue
    public String getTaskName() {
        return taskName;
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Person> assigneeList = new ArrayList<>();
        for (JsonAdaptedPerson assignee : assignees) {
            assigneeList.add(assignee.toModelType());
        }
        if (!Task.isValidName(taskName)) {
            throw new IllegalValueException(Task.MESSAGE_CONSTRAINTS);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime deadline = LocalDateTime.parse(this.deadline, formatter);
        return new Task(taskName, assigneeList, isComplete, deadline);
    }

}
