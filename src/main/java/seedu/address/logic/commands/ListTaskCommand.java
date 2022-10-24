package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    public static final String DEADLINE_MESSAGE_SUCCESS = "Sorted all tasks by deadline";

    private final Comparator comparator;

    public ListTaskCommand(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (comparator != null) {
            model.updateSortedTaskList(comparator);
            return new CommandResult(DEADLINE_MESSAGE_SUCCESS, true);
        } else {
            model.updateSortedTaskList(null);
            return new CommandResult(MESSAGE_SUCCESS, true);
        }
    }
}
