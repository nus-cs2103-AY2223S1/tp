package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameEqualsKeywordPredicate;
import seedu.address.model.company.Company;
import seedu.address.model.company.UniqueCompanyList;

/**
 * Deletes a company from a client.
 */
public class DeleteCompanyCommand extends DeleteCommand {

    public static final String MESSAGE_DELETE_COMPANY_SUCCESS = "Deleted Company: %1$s";

    public static final String MESSAGE_INVALID_USAGE = "Deletion of company can only happen when companies "
            + "are visible in the application!\n"
            + "Use 'view' command to view a specific client before applying this command\n";

    private final Index targetIndex;

    public DeleteCompanyCommand(Index index) {
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (lastShownList.size() != 1) {
            throw new CommandException(MESSAGE_INVALID_USAGE);
        }

        Client focusedClient = lastShownList.get(0);
        UniqueCompanyList companyList = focusedClient.getCompanies();

        if (targetIndex.getZeroBased() >= companyList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company deletedCompany = companyList.removeByIndex(targetIndex.getZeroBased());

        model.updateFilteredClientList(new NameEqualsKeywordPredicate(focusedClient));

        return new CommandResult(String.format(MESSAGE_DELETE_COMPANY_SUCCESS, deletedCompany));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCompanyCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCompanyCommand) other).targetIndex)); // state check
    }
}
