package soconnect.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import soconnect.commons.exceptions.IllegalValueException;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.ReadOnlyTodoList;
import soconnect.model.TodoList;
import soconnect.model.tag.Tag;
import soconnect.model.todo.Todo;

/**
 * An Immutable {@code TodoList} that is serializable to JSON format.
 */
@JsonRootName(value = "todolist")
class JsonSerializableTodoList {

    public static final String MESSAGE_DUPLICATE_TODO = "Todo List contains an identical todo.";

    private final List<JsonAdaptedTodo> todos = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTodoList} with the given {@code todos}.
     */
    @JsonCreator
    public JsonSerializableTodoList(@JsonProperty("todos") List<JsonAdaptedTodo> todos) {
        this.todos.addAll(todos);
    }

    /**
     * Converts a given {@code ReadOnlyTodoList} into this class for Jackson use.
     *
     * @param source Future changes to this will not affect the created {@code JsonSerializableTodoList}.
     */
    public JsonSerializableTodoList(ReadOnlyTodoList source) {
        todos.addAll(source.getTodoList().stream().map(JsonAdaptedTodo::new).collect(Collectors.toList()));
    }

    /**
     * Converts this todo list into the model's {@code TodoList} object.
     *
     * @throws IllegalValueException If there were any data constraints violated.
     */
    public TodoList toModelType(ReadOnlySoConnect readOnlySoConnect) throws IllegalValueException {
        TodoList todoList = new TodoList();

        for (JsonAdaptedTodo jsonAdaptedTodo : todos) {
            Todo todo = jsonAdaptedTodo.toModelType();
            if (todoList.hasTodo(todo)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TODO);
            }

            List<Tag> todoTags = new ArrayList<>(todo.getTags());
            for (int i = 0; i < todoTags.size(); i++) {
                if (!readOnlySoConnect.hasTag(todoTags.get(i))) {
                    readOnlySoConnect.addTag(todoTags.get(i));
                }
                int index = readOnlySoConnect.getTagReference(todoTags.get(i));
                todoTags.set(i, readOnlySoConnect.getTagFromList(index));

            }
            Set<Tag> updatedTags = new HashSet<>(todoTags);
            Todo newTodo = new Todo(todo.getDescription(), todo.getDate(), todo.getPriority(), updatedTags);
            todoList.addTodo(newTodo);
        }
        return todoList;
    }

}

