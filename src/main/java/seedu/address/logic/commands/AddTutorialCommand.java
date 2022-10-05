package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 *  Adds a tutorial in the address book.
 */
public class AddTutorialCommand extends Command{

    public static final String COMMAND_WORD = "addtut";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from addtut");
    }
}
