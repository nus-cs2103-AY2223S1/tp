package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.storage.Storage;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting ArtBuddy as requested ...";

    @Override
    public CommandResult execute(Model model, Storage storage) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
