package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.logic.parser.DateKeyword;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

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
        Predicate<Meeting> pred = meeting -> meeting.isInPeriod(dateKeyword);
        model.updateFilteredMeetingList(pred);
        return new CommandResult(MESSAGE_SUCCESS, CommandSpecific.MEETING);
    }

}
