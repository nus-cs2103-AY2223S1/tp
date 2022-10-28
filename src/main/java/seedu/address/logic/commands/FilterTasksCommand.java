package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IS_COMPLETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IS_LINKED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.FilterPredicate;

/**
 * Filters the task list by module, completion status and link status.
 */
public class FilterTasksCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = "t " + COMMAND_WORD
            + ": Filters the task list by module and/or completion status and/or link status.\n"
            + "Parameters: "
            + "[" + PREFIX_MODULE + "MODULE]* "
            + "[" + PREFIX_IS_COMPLETE + "COMPLETED(y or n)]* "
            + "[" + PREFIX_IS_LINKED + "LINKED(y or n)]*\n"
            + "Example: t " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2103T "
            + PREFIX_IS_COMPLETE + "y "
            + PREFIX_IS_LINKED + "n";

    public static final String MESSAGE_SUCCESS = "Listed all tasks with following constraints:%1$s";

    public static final String MESSAGE_NO_RESULTS = "No tasks found with following constraints:%1$s";

    private final FilterPredicate predicate;

    /**
     * Constructor of the FilterTaskCommand class which filters the task list by the given constraints.
     *
     * @param predicate conditions to check for each task
     */
    public FilterTasksCommand(FilterPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (predicate.hasModuleToCheck() && !model.hasModule(predicate.getModuleToCheck())) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        model.updateFilteredTaskList(predicate);

        if (model.getFilteredTaskList().isEmpty()) {
            return new CommandResult(String.format(MESSAGE_NO_RESULTS, predicate));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, predicate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterTasksCommand // instanceof handles nulls
                && predicate.equals(((FilterTasksCommand) other).predicate)); // state check
    }

}
