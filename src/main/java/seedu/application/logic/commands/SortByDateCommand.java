package seedu.application.logic.commands;

import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;

/**
 * Sorts the applications list by date
 */
public class SortByDateCommand extends SortCommand {

    public static final String ORDER_KEYWORD = "date";
    public static final String MESSAGE_SUCCESS = "Sorted application list in%1$s chronological order";

    public SortByDateCommand(boolean shouldReverse) {
        super(shouldReverse);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortApplicationListByDate(shouldReverse());
        return new CommandResult(String.format(MESSAGE_SUCCESS, shouldReverse() ? " reverse" : ""));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortByDateCommand // instanceof handles nulls
                && shouldReverse() == ((SortByDateCommand) other).shouldReverse()); // state check
    }
}
