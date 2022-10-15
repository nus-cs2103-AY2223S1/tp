package soconnect.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import soconnect.commons.exceptions.IllegalValueException;
import soconnect.model.ReadOnlyTodoList;
import soconnect.model.TodoList;
import soconnect.model.todo.Todo;

/**
 * An Immutable TodoList that is serializable to JSON format.
 */
@JsonRootName(value = "todolist")
class JsonSerializableTodoList {

    public static final String MESSAGE_DUPLICATE_TODO = "Todo List contains an identical todo.";

    private final List<JsonAdaptedTodo> todos = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTodoList} with the given todos.
     */
    @JsonCreator
    public JsonSerializableTodoList(@JsonProperty("todos") List<JsonAdaptedTodo> todos) {
        this.todos.addAll(todos);
    }

    /**
     * Converts a given {@code ReadOnlyTodoList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTodoList}.
     */
    public JsonSerializableTodoList(ReadOnlyTodoList source) {
        todos.addAll(source.getTodoList().stream().map(JsonAdaptedTodo::new).collect(Collectors.toList()));
    }

    /**
     * Converts this todo list into the model's {@code TodoList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TodoList toModelType() throws IllegalValueException {
        TodoList todoList = new TodoList();
        for (JsonAdaptedTodo jsonAdaptedTodo : todos) {
            Todo todo = jsonAdaptedTodo.toModelType();
            if (todoList.hasTodo(todo)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TODO);
            }
            todoList.addTodo(todo);
        }
        return todoList;
    }

}

