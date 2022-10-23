package seedu.address.logic.commands.schedule;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Views all schedules which satisfies selection requirements.
 */
public class ViewTimeTableCommand extends Command {

    public static final String COMMAND_WORD = "tview";

    public static final String MODEL = String.valueOf('v');

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the timetable. \n"
            + "Format: tview \n";

    public static final String SHOWING_TIMETABLE_MESSAGE = "Show the Timetable!";

    @Override
    public CommandResult execute(Model model) {
        if (MODEL.equals('v')) {
            return new CommandResult(String.format(SHOWING_TIMETABLE_MESSAGE),
                    false, false, false, false,
                    false, false, true);
        } else {
            return new CommandResult(String.format(SHOWING_TIMETABLE_MESSAGE),
                    false, false, false, false,
                    false, false, true);
        }

    }

}
