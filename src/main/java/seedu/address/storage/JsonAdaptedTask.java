package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    private final String taskName;
    private final List<JsonAdaptedPerson> assignees = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName}.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskName") String taskName,
                           @JsonProperty("assignees") List<JsonAdaptedPerson> assignees) {
        this.taskName = taskName;
        if (assignees != null) {
            this.assignees.addAll(assignees);
        }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.getName();
        assignees.addAll(source.getAssigneesList().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
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
        return new Task(taskName, assigneeList);
    }

}
