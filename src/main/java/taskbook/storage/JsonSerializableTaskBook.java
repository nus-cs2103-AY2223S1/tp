package taskbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import taskbook.commons.exceptions.IllegalValueException;
import taskbook.model.ReadOnlyTaskBook;
import taskbook.model.TaskBook;
import taskbook.model.person.Person;
import taskbook.model.task.Deadline;
import taskbook.model.task.Event;
import taskbook.model.task.Task;
import taskbook.model.task.Todo;

/**
 * An Immutable TaskBook that is serializable to JSON format.
 */
@JsonRootName(value = "taskBook")
class JsonSerializableTaskBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TASK = "Task list contains duplicate task(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTaskBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                    @JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.persons.addAll(persons);
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyTaskBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskBook}.
     */
    public JsonSerializableTaskBook(ReadOnlyTaskBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        tasks.addAll(source.getTaskList().stream().map(task -> {
            Class<? extends Task> c = task.getClass();
            if (c.equals(Todo.class)) {
                return new JsonAdaptedTodo(task);
            } else if (c.equals(Event.class)) {
                return new JsonAdaptedEvent((Event) task);
            } else if (c.equals(Deadline.class)) {
                return new JsonAdaptedDeadline((Deadline) task);
            }
            return null;
        })
            .collect(Collectors.toList()));
    }

    /**
     * Converts this task book into the model's {@code TaskBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskBook toModelType() throws IllegalValueException {
        TaskBook taskBook = new TaskBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (taskBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            taskBook.addPerson(person);
        }
        for (JsonAdaptedTask jsonAdaptedTask: tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (taskBook.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            taskBook.addTask(task);
        }
        return taskBook;
    }

}
