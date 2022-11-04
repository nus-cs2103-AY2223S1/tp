package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;

/**
 * Adds a meeting to MyInsuRec.
 */
public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "addMeeting";
    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to a client identified "
            + "by their index number.\n"
            + "Parameters: "
            + PREFIX_INDEX + "INDEX "
            + PREFIX_DATE + "DATE "
            + PREFIX_START_TIME + "START TIME "
            + PREFIX_END_TIME + "END TIME "
            + PREFIX_DESCRIPTION + "DESCRIPTION ";
    public static final String MESSAGE_CONFLICTING_MEETING =
            "This meeting conflicts with another that exists in MyInsuRec";
    public static final String MESSAGE_MEETING_NOT_FOUND = "A meeting %s could not be found";

    private final MeetingDate meetingDate;
    private final MeetingTime meetingStartTime;
    private final MeetingTime meetingEndTime;
    private final Index linkedClientIndex;
    private final Description description;

    /**
     * Creates an AddMeetingCommand to add the specified meeting.
     */
    public AddMeetingCommand(Index clientIndex, MeetingDate date,
                             MeetingTime startTime, MeetingTime endTime, Description desc) {
        requireNonNull(clientIndex);
        requireNonNull(date);
        requireNonNull(startTime);
        requireNonNull(endTime);
        requireNonNull(desc);
        meetingDate = date;
        meetingStartTime = startTime;
        meetingEndTime = endTime;
        linkedClientIndex = clientIndex;
        description = desc;
    }

    /**
     * Creates an AddMeetingCommand to add the specified meeting.
     */
    public AddMeetingCommand(Index index, Meeting meeting) {
        requireNonNull(meeting);
        meetingDate = meeting.getMeetingDate();
        meetingStartTime = meeting.getMeetingStartTime();
        meetingEndTime = meeting.getMeetingEndTime();
        linkedClientIndex = index;
        description = meeting.getDescription();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Client> clientList = model.getFilteredClientList();
        if (linkedClientIndex.getZeroBased() > clientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        if (MeetingDate.isBeforeToday(meetingDate)) {
            throw new CommandException(MeetingDate.MESSAGE_INVALID_DATE);
        }

        Client clientToUpdate = clientList.get(linkedClientIndex.getZeroBased());
        Meeting meetingToAdd = new Meeting(clientToUpdate, description, meetingDate, meetingStartTime, meetingEndTime);

        if (model.hasMeeting(meetingToAdd)) {
            throw new CommandException(MESSAGE_CONFLICTING_MEETING);
        }

        clientToUpdate.addMeeting(meetingToAdd);
        model.addMeeting(meetingToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, meetingToAdd), CommandSpecific.MEETING);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMeetingCommand // instanceof handles nulls
                && linkedClientIndex.equals(((AddMeetingCommand) other).linkedClientIndex)
                && meetingDate.equals(((AddMeetingCommand) other).meetingDate)
                && meetingStartTime.equals(((AddMeetingCommand) other).meetingStartTime)
                && meetingEndTime.equals(((AddMeetingCommand) other).meetingEndTime)
                && description.equals(((AddMeetingCommand) other).description));
    }
}
