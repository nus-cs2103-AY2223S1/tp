package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;


/**
 * Views all schedules which satisfies selection requirements.
 */
public class ViewScheduleCommand extends Command {
    public static final String COMMAND_WORD = "sview";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views all schedules"
            + "requirements.\n"
            + "Format: sview\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Showed all schedules";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, false, false, false, true, false, false);
    }
}

