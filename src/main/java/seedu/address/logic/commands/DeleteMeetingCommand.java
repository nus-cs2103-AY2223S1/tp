package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;


/**
 * Deletes a meeting identified using it's displayed index from the address book.
 */
public class DeleteMeetingCommand extends Command {

    public static final String COMMAND_WORD = "delM";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the meeting identified by the index number used in the displayed meeting list.\n"
            + "Parameters INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_MEETING_SUCCESS = "Deleted Meeting: %1$s";

    private final Index targetIndex;

    public DeleteMeetingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastshownlist = model.getFilteredMeetingList();

        if (targetIndex.getZeroBased() >= lastshownlist.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting toDelete = lastshownlist.get(targetIndex.getZeroBased());
        model.deleteMeeting(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MEETING_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteMeetingCommand
                && targetIndex.equals(((DeleteMeetingCommand) other).targetIndex));
    }
}
