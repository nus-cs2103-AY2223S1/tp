package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.remark.Remark;

/**
 * Creates a new remark to be added to the specified client.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    //Update here
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a remark and links to Client. \n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + "REMARK "
            + "Example: " + COMMAND_WORD + " 2 ";

    public static final String MESSAGE_SUCCESS = "New Remark created: %1$s\nLinked to Client: %2$s; Email: %3$s";
    public static final String MESSAGE_DUPLICATE_REMARK = "This client already has %1$s as Remark";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the client in the client list to add the Remark to.
     * @param remark to be added.
     */
    public RemarkCommand(Index index, Remark remark) {
        requireNonNull(index);
        requireNonNull(remark);

        this.index = index;
        this.remark = remark;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        // if index of client not valid
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());

        if (clientToEdit.hasRemark(remark)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_REMARK, remark.getText()));
        }

        Client editedClient = clientToEdit;
        editedClient.addRemark(remark);
        model.setClient(clientToEdit, editedClient);

        return new CommandResult(String.format(MESSAGE_SUCCESS, remark.getText(), editedClient.getName(),
                editedClient.getEmail()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemarkCommand // instanceof handles nulls
                && index.equals(((RemarkCommand) other).index)
                && remark.equals(((RemarkCommand) other).remark));
    }

}
