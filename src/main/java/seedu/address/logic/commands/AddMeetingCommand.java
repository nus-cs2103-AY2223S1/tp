package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMyInsuRec;
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
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in MyInsuRec";
    public static final String MESSAGE_CLIENT_NOT_FOUND = "A client named %s could not be found";

    private final MeetingDate meetingDate;
    private final MeetingTime meetingTime;
    private final String linkedClientName;

    /**
     * Creates an AddMeetingCommand to add the specified meeting.
     */
    public AddMeetingCommand(String clientName, MeetingDate date, MeetingTime time) {
        meetingDate = date;
        meetingTime = time;
        linkedClientName = clientName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ReadOnlyMyInsuRec clientBook = model.getMyInsuRec();
        List<Client> filteredClientList = clientBook.getClientList().stream().filter(
                client -> client.getName().toString().equals(linkedClientName)
        ).collect(Collectors.toList());
        if (filteredClientList.size() != 1) {
            throw new CommandException(String.format(MESSAGE_CLIENT_NOT_FOUND, linkedClientName));
        }
        Client oldClient = filteredClientList.get(0);
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
