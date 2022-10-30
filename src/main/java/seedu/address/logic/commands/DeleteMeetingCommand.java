package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the meeting of the client "
            + "identified by their index number.\n"
            + "Parameters: " + PREFIX_INDEX + "INDEX\n"
            + "Example: " + COMMAND_WORD + " i/1";

    public static final String MESSAGE_DELETE_MEETING_SUCCESS = "Remove meeting from Client: %1$s";

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
        List<Meeting> lastShownMeetingList = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastShownMeetingList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meeting = lastShownMeetingList.get(index.getZeroBased());
        Client clientToUpdate = meeting.getClient();

        clientToUpdate.removeMeeting(meeting);
        model.deleteMeeting(meeting);

        return new CommandResult(generateSuccessMessage(clientToUpdate), CommandSpecific.MEETING);
    }

    /**
     * Generates a command execution success message when the meeting
     * is removed from {@code clientToEdit}
     */
    private String generateSuccessMessage(Client clientToEdit) {
        return String.format(MESSAGE_DELETE_MEETING_SUCCESS, clientToEdit);
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
