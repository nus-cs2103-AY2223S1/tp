package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.logic.parser.DateKeyword;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;

/**
 * Lists all meetings according to criteria given by the user.
 */
public class FindMeetingCommand extends Command {

    public static final String COMMAND_WORD = "findMeeting";

    public static final String MESSAGE_SUCCESS = "Listed all meetings satisfying the given criteria";

    private DateKeyword dateKeyword;

    /**
     * Constructs a {@Code FindMeetingCommand}
     */
    public FindMeetingCommand(DateKeyword dateKeyword) {
        requireNonNull(dateKeyword);
        this.dateKeyword = dateKeyword;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        MeetingDate dateBefore;
        switch(dateKeyword) {
            case TOMORROW:
                dateBefore = new MeetingDate(LocalDate.now().plusDays(1));
                break;
            case THIS_MONTH:
                dateBefore = new MeetingDate(LocalDate.now().plusDays(7));
                break;
            case THIS_WEEK:
                dateBefore = new MeetingDate(LocalDate.now().plusDays(30));
                break;
            default:
                dateBefore = null;
        }

        requireNonNull(dateBefore);
        Predicate<Meeting> pred = meeting -> meeting.isBeforeDate(dateBefore);
        model.updateFilteredMeetingList(pred);
        return new CommandResult(MESSAGE_SUCCESS, CommandSpecific.MEETING);
    }

}
