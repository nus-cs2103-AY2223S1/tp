package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

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
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to MyInsuRec.\n"
            + "Parameters: "
            + PREFIX_INDEX + "INDEX "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in MyInsuRec";
    public static final String MESSAGE_CLIENT_NOT_FOUND = "A client named %s could not be found";

    private final MeetingDate meetingDate;
    private final MeetingTime meetingTime;
    private final Index linkedClientIndex;

    /**
     * Creates an AddMeetingCommand to add the specified meeting.
     */
    public AddMeetingCommand(Index clientIndex, MeetingDate date, MeetingTime time) {
        meetingDate = date;
        meetingTime = time;
        linkedClientIndex = clientIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Client> clientList = model.getFilteredClientList();
        if (linkedClientIndex.getZeroBased() > clientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }
        Client oldClient = clientList.get(linkedClientIndex.getZeroBased());
        Client newClient = new Client(
                oldClient.getName(),
                oldClient.getPhone(),
                oldClient.getEmail(),
                oldClient.getAddress(),
                oldClient.getTags());
        Meeting meetingToAdd = new Meeting(newClient, new Description("desc"), meetingDate, meetingTime);

        if (model.hasMeeting(meetingToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        newClient.setMeeting(meetingToAdd);
        model.addMeeting(meetingToAdd);
        model.setClient(oldClient, newClient);
        return new CommandResult(String.format(MESSAGE_SUCCESS, meetingToAdd),
                false, false, false, true);
    }

}
