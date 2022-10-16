package soconnect.logic.commands.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import soconnect.logic.commands.CommandResult;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.model.Model;
import soconnect.model.todo.Todo;

/**
 * Adds a {@code Todo} to the TodoList.
 */
public class TodoAddCommand extends TodoCommand {

    public static final String SUB_COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SUB_COMMAND_WORD + ": Adds a todo to SoConnect. "
        + "Parameters: "
        + PREFIX_DESCRIPTION + "DESCRIPTION \n"
        + "Example: " + COMMAND_WORD + " " + SUB_COMMAND_WORD + " "
        + PREFIX_DESCRIPTION + "Watch latest math lecture ";

    public static final String MESSAGE_SUCCESS = "New todo added: %1$s";
    public static final String MESSAGE_DUPLICATE_TODO = "This todo already exists in SoConnect";

    private final Todo toAdd;

    public TodoAddCommand(Todo todo) {
         requireNonNull(todo);
         toAdd = todo;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTodo(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TODO);
        }

        model.addTodo(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TodoAddCommand // instanceof handles nulls
            && toAdd.equals(((TodoAddCommand) other).toAdd));
    }
}
