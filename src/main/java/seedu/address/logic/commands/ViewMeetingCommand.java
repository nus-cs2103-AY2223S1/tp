package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

/**
 * Views a meeting.
 */
public class ViewMeetingCommand extends Command {

    public static final String COMMAND_WORD = "viewMeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the meeting identified by their index number.\n"
            + "Parameters: " + PREFIX_INDEX + "INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "Viewed meeting: %1$s";

    private final Index targetIndex;

    /**
     * Creates a ViewMeetingCommand to view the specified {@code Meeting}
     */
    public ViewMeetingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToView = lastShownList.get(targetIndex.getZeroBased());

        model.setDetailedMeeting(meetingToView);

        return new CommandResult(String.format(MESSAGE_SUCCESS, meetingToView), CommandSpecific.DETAILED_MEETING);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewMeetingCommand // instanceof handles nulls
                && targetIndex.equals(((ViewMeetingCommand) other).targetIndex)); // state check
    }
}
