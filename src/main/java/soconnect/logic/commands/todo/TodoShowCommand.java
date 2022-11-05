package soconnect.logic.commands.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.logic.parser.CliSyntax.PREFIX_DATE;
import static soconnect.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import soconnect.commons.core.Messages;
import soconnect.logic.commands.CommandResult;
import soconnect.model.Model;
import soconnect.model.todo.Todo;

/**
 * Filters the shown {@code TodoList}.
 */
public class TodoShowCommand extends TodoCommand {

    public static final String SUB_COMMAND_WORD = "show";

    public static final String EMPTY_CONDITION = "";

    public static final String TODAY_CONDITION = "today";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SUB_COMMAND_WORD
        + ": Filters the Todo List based on the condition set, condition can be empty.\n"
        + "Conditions (at most one condition at a time): " + TODAY_CONDITION.toLowerCase() + ", "
        + PREFIX_DATE + "DATE, " + PREFIX_DATE + "DATE to DATE, "
        + PREFIX_PRIORITY + "PRIORITY, " + PREFIX_TAG + "TAG\n"
        + "Parameters: [CONDITION]\n"
        + "Example: \n"
        + COMMAND_WORD + " " + SUB_COMMAND_WORD + "\n"
        + COMMAND_WORD + " " + SUB_COMMAND_WORD + " " + PREFIX_DATE + "21-10-2022 to 28-10-2022\n"
        + COMMAND_WORD + " " + SUB_COMMAND_WORD + " " + PREFIX_PRIORITY + "low";

    private final Predicate<Todo> predicate;

    private final String header;

    /**
     * Constructs a {@code TodoShowCommand} to filter {@code Todo}s in the {@code TodoList}
     * and updates the header of the {@code TodoListPanel}.
     */
    public TodoShowCommand(Predicate<Todo> predicate, String header) {
        requireNonNull(predicate);
        this.predicate = predicate;
        this.header = header;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTodoList(predicate);
        model.updateTodoListHeader(header);
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
