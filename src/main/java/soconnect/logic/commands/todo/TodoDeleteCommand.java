package soconnect.logic.commands.todo;

import static java.util.Objects.requireNonNull;

import java.util.List;

import soconnect.commons.core.Messages;
import soconnect.commons.core.index.Index;
import soconnect.logic.commands.CommandResult;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.model.Model;
import soconnect.model.todo.Todo;

/**
 * Deletes a {@code Todo} identified using it's displayed index from the {@code TodoList}.
 */
public class TodoDeleteCommand extends TodoCommand {

    public static final String SUB_COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SUB_COMMAND_WORD
        + ": Deletes the Todo identified by the index number used in the displayed Todo List.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " " + SUB_COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TODO_SUCCESS = "Deleted Todo: %1$s";

    private final Index targetIndex;

    /**
     * Constructs an {@code TodoDeleteCommand} to delete an existing {@code Todo}.
     *
     * @param targetIndex The index of the {@code Todo} to delete.
     */
    public TodoDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Todo> lastShownList = model.getFilteredTodoList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
        }

        Todo todoToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTodo(todoToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TODO_SUCCESS, todoToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TodoDeleteCommand // instanceof handles nulls
            && targetIndex.equals(((TodoDeleteCommand) other).targetIndex)); // state check
    }
}
