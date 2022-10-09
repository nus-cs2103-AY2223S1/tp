package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTaskPanel;
import seedu.address.model.TaskPanel;
import seedu.address.model.task.Task;

/**
 * An Immutable TaskPanel that is serializable to JSON format.
 */
@JsonRootName(value = "taskpanel")
class JsonSerializableTaskPanel {

    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskPanel} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableTaskPanel(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyTaskPanel} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskPanel}.
     */
    public JsonSerializableTaskPanel(ReadOnlyTaskPanel source) {
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this task panel into the model's {@code TaskPanel} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskPanel toModelType() throws IllegalValueException {
        TaskPanel taskPanel = new TaskPanel();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (taskPanel.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            taskPanel.addTask(task);
        }
        return taskPanel;
    }

}
