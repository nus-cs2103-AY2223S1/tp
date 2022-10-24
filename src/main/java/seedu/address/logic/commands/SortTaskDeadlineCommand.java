package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Sorts tasks in address book by deadline date.
 */
public class SortTaskDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "sortByDeadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts tasks by deadline.\n"
            + "Parameters: [" + PREFIX_ORDER + "ORDER (asc/desc)]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ORDER + "asc";

    private Comparator<Task> comparator;

    public SortTaskDeadlineCommand(Comparator<Task> comparator) {
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
                || (other instanceof SortTaskDeadlineCommand // instanceof handles nulls
                && comparator.equals(((SortTaskDeadlineCommand) other).comparator)); // state check
    }
}
