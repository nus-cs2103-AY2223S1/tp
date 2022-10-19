package seedu.application.logic.commands;

import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;

/**
 * Sorts the applications list by position
 */
public class SortByPositionCommand extends SortCommand {

    public static final String ORDER_KEYWORD = "position";
    public static final String MESSAGE_SUCCESS = "Sorted application list in%1$s order of position";

    public SortByPositionCommand(boolean shouldReverse) {
        super(shouldReverse);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortApplicationListByPosition(shouldReverse());
        return new CommandResult(String.format(MESSAGE_SUCCESS, shouldReverse() ? " reverse" : ""));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortByPositionCommand // instanceof handles nulls
                && shouldReverse() == ((SortByPositionCommand) other).shouldReverse()); // state check
    }
}
