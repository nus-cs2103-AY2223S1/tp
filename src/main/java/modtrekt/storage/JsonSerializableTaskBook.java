package modtrekt.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import modtrekt.commons.exceptions.IllegalValueException;
import modtrekt.model.TaskBook;
import modtrekt.model.ReadOnlyTaskBook;
import modtrekt.model.task.Task;

/**
 * An Immutable TaskBook that is serializable to JSON format.
 */
@JsonRootName(value = "taskbook")
class JsonSerializableTaskBook {

//    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskBook} with the given tasks.
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
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this task book into the model's {@code TaskBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskBook toModelType() throws IllegalValueException {
        TaskBook taskBook = new TaskBook();
        for (JsonAdaptedTask jsonAdaptedPerson : tasks) {
            Task t = jsonAdaptedPerson.toModelType();
            taskBook.addTask(t);
        }
        System.out.println(taskBook);
        return taskBook;
    }

}
