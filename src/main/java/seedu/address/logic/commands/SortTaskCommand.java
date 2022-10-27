package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.Comparator;

import seedu.address.commons.SortInfo;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Sorts tasks in address book based on user input.
 */
public class SortTaskCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts tasks based on the user input.\n"
            + "Parameters: "
            + "[" + PREFIX_PRIORITY + "ORDER (asc/desc)] "
            + "[" + PREFIX_DEADLINE + "ORDER (asc/desc)]\n"
            + "Requirement: Only one parameter to sort with should be provided.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PRIORITY + "asc ";

    private final Comparator<Task> comparator;
    private final SortInfo sortInfo;
    private final String option;

    /**
     * Constructor method for sorting by priority.
     * @param comparator
     * @param sortInfo   order of sorting to be shown in the TaskListInfo
     */
    public SortTaskCommand(Comparator<Task> comparator, SortInfo sortInfo) {
        this.comparator = comparator;
        this.sortInfo = sortInfo;
        this.option = "priority";
    }

    /**
     * Constructor method for sorting by deadline.
     * @param comparator
     * @param sortInfo   order of sorting to be shown in the TaskListInfo
     * @param deadline   String to indicate sort by deadline
     */
    public SortTaskCommand(Comparator<Task> comparator, SortInfo sortInfo, String deadline) {
        this.comparator = comparator;
        this.sortInfo = sortInfo;
        this.option = deadline;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortingCriteria(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASK_LISTED_OVERVIEW, model.getFilteredTaskList().size()), sortInfo);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortTaskCommand // instanceof handles nulls
                && comparator.equals(((SortTaskCommand) other).comparator)); // state check
    }
}
