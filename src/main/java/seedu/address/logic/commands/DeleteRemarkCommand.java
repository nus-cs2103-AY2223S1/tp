package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameEqualsKeywordPredicate;
import seedu.address.model.remark.Remark;
import seedu.address.model.remark.UniqueRemarkList;

/**
 * Deletes a remark from a client.
 */
public class DeleteRemarkCommand extends DeleteCommand {

    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Deleted Remark: %1$s";

    public static final String MESSAGE_INVALID_USAGE = "Deletion of remark can only happen when remarks "
            + "are visible in the application!\n"
            + "Use 'view' command to view a specific client before applying this command\n";

    private final Index targetIndex;

    public DeleteRemarkCommand(Index index) {
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
        UniqueRemarkList remarkList = focusedClient.getRemarks();

        if (targetIndex.getZeroBased() >= remarkList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_REMARK_DISPLAYED_INDEX);
        }

        Remark deletedRemark = remarkList.removeByIndex(targetIndex.getZeroBased());

        model.updateFilteredClientList(new NameEqualsKeywordPredicate(focusedClient));

        return new CommandResult(String.format(MESSAGE_DELETE_REMARK_SUCCESS, deletedRemark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteRemarkCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteRemarkCommand) other).targetIndex)); // state check
    }
}
