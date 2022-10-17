package soconnect.logic.commands.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import soconnect.commons.core.Messages;
import soconnect.logic.commands.CommandResult;
import soconnect.model.Model;
import soconnect.model.todo.Todo;

/**
 * Filters the shown todo list.
 */
public class TodoShowCommand extends TodoCommand {

    public static final String SUB_COMMAND_WORD = "show";
    public static final String NO_CONDITION = "all";
    public static final String PREFIX_CONDITION = "";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SUB_COMMAND_WORD
        + ": Filters the shown Todo List based on the condition set.\n"
        + "Conditions (only able to use one at a time): all, " + PREFIX_PRIORITY + "PRIORITY, " + PREFIX_TAG + "TAG\n"
        + "Parameters: CONDITION\n"
        + "Example: " + COMMAND_WORD + " " + SUB_COMMAND_WORD + " " + NO_CONDITION
        + "\n" + COMMAND_WORD + " " + SUB_COMMAND_WORD + " " + PREFIX_PRIORITY + "low";

    private final Predicate<Todo> predicate;

    /**
     * Constructs a {@code TodoShowCommand} to filter todos in the TodoList.
     */
    public TodoShowCommand(Predicate<Todo> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTodoList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_TODOS_LISTED_OVERVIEW, model.getFilteredTodoList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TodoShowCommand // instanceof handles nulls
            && predicate.equals(((TodoShowCommand) other).predicate)); // state check
    }

}
