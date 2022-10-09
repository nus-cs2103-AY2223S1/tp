package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.Company;
import seedu.address.model.company.NameEqualsKeywordPredicate;

/**
 * Views the details (point-of-contacts, transactions) of an existing company in the address book.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the point-of-contact list and transactions "
            + "of the company by the index number given in the displayed company list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_VIEW_COMPANY_SUCCESS = "Viewing company: %1$s";

    private final Index index;

    /**
     * Constructor for ViewCommand.
     * @param index of company to view.
     */
    public ViewCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToView = lastShownList.get(index.getZeroBased());
        String companyName = companyToView.getName().toString();

        NameEqualsKeywordPredicate predicate = new NameEqualsKeywordPredicate(companyName);
        model.updateFilteredCompanyList(predicate);

        return new CommandResult(String.format(MESSAGE_VIEW_COMPANY_SUCCESS, companyName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && index.equals(((ViewCommand) other).index)); // state check
    }

}
