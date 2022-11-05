package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CRITERIA;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Criteria;
import seedu.address.model.Model;

/**
 * SortTaskCommand represents a command which sorts the tasks in the task list.
 */
public class SortTaskCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = "t " + COMMAND_WORD + ": sorts the task list.\n"
            + "Parameters: " + PREFIX_CRITERIA + "CRITERIA\n"
            + "Example: " + "t " + COMMAND_WORD + " " + PREFIX_CRITERIA + "priority";
    public static final String TASK_SORTED_SUCCESSFULLY =
            "Task list has been successfully sorted";
    public static final String NO_TASK_TO_SORT =
            "There are no tasks in the task list to sort!";
    private final Criteria criteria;

    /**
     * The constructor of the SortTaskCommand. Sets the criteria
     * that is used for sorting.
     *
     * @param criteria The criteria used for sorting.
     */
    public SortTaskCommand(Criteria criteria) {
        requireNonNull(criteria);
        this.criteria = criteria;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortTaskList(criteria);
        if (model.getFilteredTaskList().size() == 0) {
            throw new CommandException(NO_TASK_TO_SORT);
        }
        return new CommandResult(TASK_SORTED_SUCCESSFULLY);
    }

    @Override
    public boolean equals(Object otherSortTaskCommand) {
        return otherSortTaskCommand == this
                || (otherSortTaskCommand instanceof SortTaskCommand
                && criteria.equals(((SortTaskCommand) otherSortTaskCommand).criteria));
    }
}
