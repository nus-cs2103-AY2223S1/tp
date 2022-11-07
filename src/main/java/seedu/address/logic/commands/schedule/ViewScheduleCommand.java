package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Views all schedules which satisfies selection requirements.
 */
public class ViewScheduleCommand extends Command {

    public static final String COMMAND_WORD = "sview";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views all schedules which satisfy all selection "
            + "requirements.\n"
            + "Format: sview\n"
            + "Example: " + COMMAND_WORD + " \n";

    public static final String MESSAGE_MODULE_NOT_EXIST = "The module you are looking for doesn't exist.";

    /**
     * Creates a view schedule command without predicate
     */
    public ViewScheduleCommand() {
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);

        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);

        return new CommandResult(String.format(
                String.format(Messages.MESSAGE_SCHEDULES_LISTED_OVERVIEW, model.getFilteredScheduleList().size())),
                false, false, false, false,
                false, false, true, false, false);
    }


}
