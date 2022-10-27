package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;

import seedu.address.commons.FilterInfo;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.TaskDeadlineBeforeDatePredicate;

/**
 * Filters and lists all tasks in address book with deadline that matches the argument date.
 */
public class FilterTaskDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "filterByDeadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all tasks with deadline that matches the "
            + "input date and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_DEADLINE + "DATE (YYYY-MM-DD)]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DEADLINE + "2022-12-12";

    private final TaskDeadlineBeforeDatePredicate date;
    private final FilterInfo filterInfo;

    /**
     * @param date       tasks with deadlines on this date or before will be shown
     * @param filterInfo to show in the TaskListInfo
     */
    public FilterTaskDeadlineCommand(TaskDeadlineBeforeDatePredicate date, FilterInfo filterInfo) {
        this.date = date;
        this.filterInfo = filterInfo;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(date);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASK_LISTED_OVERVIEW, model.getFilteredTaskList().size()), filterInfo);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterTaskDeadlineCommand // instanceof handles nulls
                && date.equals(((FilterTaskDeadlineCommand) other).date)); // state check
    }
}
