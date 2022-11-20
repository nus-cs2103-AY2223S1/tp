package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTaskBook;
import seedu.address.model.TaskBook;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;
import seedu.address.model.task.ToDo;

/**
 * An Immutable TaskBook that is serializable to JSON format.
 */
@JsonRootName(value = "taskbook")
class JsonSerializableTaskBook {

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableTaskBook(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyTaskBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskBook}.
     */
    public JsonSerializableTaskBook(ReadOnlyTaskBook source) {
        tasks.addAll(source.getTaskList().stream().map(x -> x.getClass().equals(ToDo.class)
                                    ? new JsonAdaptedToDo(x)
                                    : x.getClass().equals(Deadline.class)
                                    ? new JsonAdaptedDeadline(x)
                                    : new JsonAdaptedAssignment(x))
                .collect(Collectors.toList()));
    }

    /**
     * Converts this task book into the model's {@code TaskBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskBook toModelType() throws IllegalValueException {
        TaskBook taskBook = new TaskBook();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            taskBook.addTask(task);
        }
        return taskBook;
    }

}
