package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.TaskDeadlineContainsDatePredicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;

/**
 * Filters and lists all tasks in address book with deadline that matches the argument date.
 */
public class FilterTaskDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "filterByDeadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all tasks with deadline that matches the "
            + "input date and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_DEADLINE + "DATE (YYYY-MM-DD)]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DEADLINE + "2022-12-12";

    private final TaskDeadlineContainsDatePredicate date;

    public FilterTaskDeadlineCommand(TaskDeadlineContainsDatePredicate date) {
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(date);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASK_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterTaskDeadlineCommand // instanceof handles nulls
                && date.equals(((FilterTaskDeadlineCommand) other).date)); // state check
    }
}













