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
import seedu.address.model.remark.exceptions.DuplicateRemarkException;


/**
 * Edits the details of an existing remark in one client in the address book.
 */
public class EditRemarkCommand extends EditCommand {

    public static final String MESSAGE_EDIT_TRANSACTION_SUCCESS = "Remark edited successfully: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one character must be provided as the new remark.";
    public static final String MESSAGE_INVALID_USAGE = "Edit of remark can only happen when remark "
            + "is visible in the application!\n"
            + "Use 'view' command to view a specific client before applying this command\n";
    public static final String MESSAGE_REMARK_INVALID = "Remark cannot be created. Enter a valid Remark details:\n"
            + "INDEX "
            + "REMARK";
    public static final String MESSAGE_DUPLICATE_REMARK = "This remark has already been created!\n";

    private final Index index;
    private final Remark editedRemark;

    /**
     * Construct a new EditRemarkCommand.
     *
     * @param index of remark to be edited.
     * @param editedRemark remark used to replace the existing remark with.
     */
    public EditRemarkCommand(Index index, Remark editedRemark) {
        requireNonNull(index);
        requireNonNull(editedRemark);

        this.index = index;
        this.editedRemark = editedRemark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();
        if (lastShownList.size() != 1) {
            throw new CommandException(MESSAGE_INVALID_USAGE);
        }
        Client currentClient = lastShownList.get(0);
        UniqueRemarkList remarkList = currentClient.getRemarks();

        if (index.getZeroBased() >= remarkList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Remark remarkToEdit = remarkList.getRemark(index.getZeroBased());

        try {
            remarkList.replaceRemark(remarkToEdit, editedRemark);
        } catch (DuplicateRemarkException e) {
            throw new CommandException(MESSAGE_DUPLICATE_REMARK);
        }

        model.updateFilteredClientList(new NameEqualsKeywordPredicate(currentClient));
        return new CommandResult(String.format(MESSAGE_EDIT_TRANSACTION_SUCCESS, editedRemark));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditRemarkCommand)) {
            return false;
        }

        // state check
        EditRemarkCommand e = (EditRemarkCommand) other;
        return index.equals(e.index)
                && editedRemark.equals(e.editedRemark);
    }

}
