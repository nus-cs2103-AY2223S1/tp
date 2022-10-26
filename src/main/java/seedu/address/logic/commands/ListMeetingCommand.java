package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETING;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.logic.parser.DateKeyword;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;

/**
 * Lists all meetings in the client book to the user.
 */
public class ListMeetingCommand extends Command {

    public static final String COMMAND_WORD = "listMeeting";

    public static final String MESSAGE_SUCCESS = "Listed all meetings";

    public static final String MESSAGE_USAGE = "Usage: listMeeting [d/PERIOD]";

    private DateKeyword dateKeyword;

    public ListMeetingCommand() {
        dateKeyword = DateKeyword.ALL_TIME;
    }

    public ListMeetingCommand(DateKeyword keyword) {
        dateKeyword = keyword;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        MeetingDate startDate;
        MeetingDate endDate;
        LocalDate today = LocalDate.now();
        switch(dateKeyword) {
        case ALL_TIME:
            model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETING);
            return new CommandResult(MESSAGE_SUCCESS, CommandSpecific.MEETING);
        case TOMORROW:
            startDate = new MeetingDate(today.plusDays(1));
            endDate = new MeetingDate(today.plusDays(1));
            break;
        case THIS_MONTH:
            startDate = new MeetingDate(today.withDayOfMonth(1));
            endDate = new MeetingDate(today.withDayOfMonth(today.getMonth().length(today.isLeapYear())));
            break;
        case THIS_WEEK:
            startDate = new MeetingDate(today);
            endDate = new MeetingDate(today.plusDays(7));
            break;
        default:
            startDate = null;
            endDate = null;
        }

        requireNonNull(startDate);
        requireNonNull(endDate);
        Predicate<Meeting> pred = meeting ->
                meeting.isAfterDate(startDate) && meeting.isBeforeDate(endDate);
        model.updateFilteredMeetingList(pred);
        return new CommandResult(MESSAGE_SUCCESS, CommandSpecific.MEETING);
    }

}
