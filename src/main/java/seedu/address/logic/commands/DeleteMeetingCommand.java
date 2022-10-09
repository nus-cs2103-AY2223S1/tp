package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

/**
 * Deletes a Meeting of specified client identified using it's displayed index from MyInsuRec.
 */
public class DeleteMeetingCommand extends Command {
    public static final String COMMAND_WORD = "delMeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the meeting of the client "
            + "by the index number of the client. \n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " i/1";

    public static final String MESSAGE_ADD_DELETE_MEETING_SUCCESS = "Remove meeting from Client: %1$s";
    public static final String MESSAGE_MEETING_NOT_FOUND = "Meeting not found from Client: %1$s";

    private final Index index;

    /**
     * Creates an DeleteMeetingCommand to delete the specified client's meeting
     */
    public DeleteMeetingCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Client editedClient = new Client(
                clientToEdit.getName(), clientToEdit.getPhone(), clientToEdit.getEmail(),
                clientToEdit.getAddress(), clientToEdit.getTags()
        );
        Meeting meeting = clientToEdit.getMeeting();

        if (meeting == null) {
            throw new CommandException(String.format(MESSAGE_MEETING_NOT_FOUND, clientToEdit));
        }

        model.deleteMeeting(meeting);
        model.setClient(clientToEdit, editedClient);
        model.updateFilteredClientList(Model.PREDICATE_SHOW_ALL_CLIENTS);

        return new CommandResult(generateSuccessMessage(editedClient));
    }

    /**
     * Generates a command execution success message when the meeting
     * is removed from {@code clientToEdit}
     */
    private String generateSuccessMessage(Client clientToEdit) {
        return String.format(MESSAGE_ADD_DELETE_MEETING_SUCCESS, clientToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DeleteMeetingCommand)) {
            return false;
        }
        DeleteMeetingCommand e = (DeleteMeetingCommand) other;
        return index.equals(e.index);
    }
}
