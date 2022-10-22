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

        MeetingDate startDate;
        MeetingDate endDate;
        LocalDate today = LocalDate.now();
        switch(dateKeyword) {
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
