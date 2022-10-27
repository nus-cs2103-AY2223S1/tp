package soconnect.logic.commands.todo;

import static java.util.Objects.requireNonNull;

import soconnect.logic.commands.CommandResult;
import soconnect.model.Model;
import soconnect.model.TodoList;
import soconnect.ui.TodoListPanel;

/**
 * Clears the {@code TodoList}.
 */
public class TodoClearCommand extends TodoCommand {

    public static final String SUB_COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Todo List has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTodoList(new TodoList());
        model.updateTodoListHeader(TodoListPanel.ALL_HEADER);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
