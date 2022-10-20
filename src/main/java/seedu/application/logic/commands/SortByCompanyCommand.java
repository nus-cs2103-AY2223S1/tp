package seedu.application.logic.commands;

import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;

/**
 * Sorts the applications list by company
 */
public class SortByCompanyCommand extends SortCommand {

    public static final String ORDER_KEYWORD = "company";
    public static final String MESSAGE_SUCCESS = "Sorted application list in%1$s order of company";

    public SortByCompanyCommand(boolean shouldReverse) {
        super(shouldReverse);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortApplicationListByCompany(shouldReverse());
        return new CommandResult(String.format(MESSAGE_SUCCESS, shouldReverse() ? " reverse" : ""));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortByCompanyCommand // instanceof handles nulls
                && shouldReverse() == ((SortByCompanyCommand) other).shouldReverse()); // state check
    }
}
