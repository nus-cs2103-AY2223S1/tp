package seedu.address.logic.commands;

import seedu.address.model.Model;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import seedu.address.logic.commands.exceptions.CommandException;

public class AttendanceCommand extends Command {

    public static final String COMMAND_WORD = "attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ATTENDANCE + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ATTENDANCE + "Likes to swim.";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "attendance command not implemented yet";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from attendance");
    }
}
