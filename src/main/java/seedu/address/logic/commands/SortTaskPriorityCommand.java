package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Sorts tasks in address book by priority.
 */
public class SortTaskPriorityCommand extends Command {

    public static final String COMMAND_WORD = "sortByPriority";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts tasks by priority.\n"
            + "Parameters: [" + PREFIX_ORDER + "ORDER (asc/desc)]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ORDER + "desc";

    private Comparator<Task> comparator;

    public SortTaskPriorityCommand(Comparator<Task> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASK_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortTaskPriorityCommand // instanceof handles nulls
                && comparator.equals(((SortTaskPriorityCommand) other).comparator)); // state check
    }
}
