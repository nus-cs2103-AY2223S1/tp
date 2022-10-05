package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static seedu.address.logic.parser.CliSyntax.*;


/**
 * Adds a tutorial in the address book.
 */
public class AddTutorialCommand extends Command {

    public static final String COMMAND_WORD = "addtut";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tutorial to the address book. "
            + "Parameters: "
            + PREFIX_GROUP + "GROUP NUMBER "
            + PREFIX_CONTENT + "CONTENT "
            + PREFIX_TIME + "TIME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "T08 "
            + PREFIX_CONTENT + "UML Diagram "
            + PREFIX_TIME + "2022-10-01T08:00:00";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Addtut command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
