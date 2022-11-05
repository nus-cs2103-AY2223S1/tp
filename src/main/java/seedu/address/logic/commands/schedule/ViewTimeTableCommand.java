package seedu.address.logic.commands.schedule;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Views all schedules which satisfies selection requirements.
 */
public class ViewTimeTableCommand extends Command {
    public static final String COMMAND_WORD = "tview";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the Horizontal/Vertical Timetable. \n"
            + "Format: tview h (View Horizontal Timetable)\n"
            + "Format: tview v (View Vertical Timetable)\n";

    public static final String SHOWING_HORIZONTAL_TIMETABLE_MESSAGE = "Show the Horizontal Timetable!";
    public static final String SHOWING_VERTICAL_TIMETABLE_MESSAGE = "Show the Vertical Timetable!";

    private int timetableModel = 0;

    /**
     * Constructor of ViewTimeTableCommand with default MODEL
     */
    public ViewTimeTableCommand() {
        this.timetableModel = 0;
    }

    /**
     * Constructor of ViewTimeTableCommand with input MODEL "0" or "1"
     * @param timetableModel
     */
    public ViewTimeTableCommand(int timetableModel) {
        this.timetableModel = timetableModel;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (this.timetableModel == 0) { // Horizontal
            return new CommandResult(String.format(SHOWING_HORIZONTAL_TIMETABLE_MESSAGE),
                    false, false, false, false,
                    false, false, false, true, false, false, false);
        } else if (this.timetableModel == 1) { // Vertical
            return new CommandResult(String.format(SHOWING_VERTICAL_TIMETABLE_MESSAGE),
                    false, false, false, false,
                    false, false, false, false, true, false, false);
        }

        throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTimeTableCommand.MESSAGE_USAGE));

    }

}
