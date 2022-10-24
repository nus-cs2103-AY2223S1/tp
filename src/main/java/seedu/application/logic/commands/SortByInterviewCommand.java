package seedu.application.logic.commands;

import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;

/**
 * Sorts the applications list by interview date
 */
public class SortByInterviewCommand extends SortCommand {

    public static final String ORDER_KEYWORD = "interview";
    public static final String MESSAGE_SUCCESS = "Sorted application list in%1$s chronological order of interview date";

    public SortByInterviewCommand(boolean shouldReverse) {
        super(shouldReverse);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortApplicationListByInterview(shouldReverse());
        return new CommandResult(String.format(MESSAGE_SUCCESS, shouldReverse() ? " reverse" : ""));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortByInterviewCommand // instanceof handles nulls
                && shouldReverse() == ((SortByInterviewCommand) other).shouldReverse()); // state check
    }
}
