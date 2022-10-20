package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETING;
import java.util.function.Predicate;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;

public class FindMeetingCommand extends Command {

    public static final String COMMAND_WORD = "findMeeting";

    public static final String MESSAGE_SUCCESS = "Listed all meetings satisfying the given criteria";

    private MeetingDate dateBefore;

    public FindMeetingCommand(MeetingDate meetingDate) {
        dateBefore = meetingDate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Meeting> pred = meeting -> meeting.isBeforeDate(dateBefore);
        model.updateFilteredMeetingList(pred);
        return new CommandResult(MESSAGE_SUCCESS, CommandSpecific.MEETING);
    }

}
