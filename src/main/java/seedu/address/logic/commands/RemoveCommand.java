package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Removes lesson from user or contact.
 */
public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a lesson "
            + "identified by the index number used in the displayed person list or user. "
            + "Parameters: user / INDEX (must be a positive integer) "
            + "[" + PREFIX_LESSON + "TYPE] "
            + "[" + PREFIX_MOD + "MODULE] "
            + "[" + PREFIX_DAY + "DAY] "
            + "[" + PREFIX_START + "START TIME] "
            + "[" + PREFIX_END + "END TIME]\n"
            + "Example: " + COMMAND_WORD + " user "
            + PREFIX_LESSON + "tut "
            + PREFIX_MOD + "CS2103T "
            + PREFIX_DAY + "thu "
            + PREFIX_START + "12:00 "
            + PREFIX_END + "13:00";

    public static final String MESSAGE_INVALID_TIME = "Format of time is invalid! Use HH:mm";

    public static final String MESSAGE_INVALID_DAY = "Format of day is invalid! Use 1 to 7, "
            + "where 1 is Monday and 7 is Sunday!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.commitAddressBook();
        return new CommandResult("");
    }
}
