package seedu.studmap.logic.commands;

import seedu.studmap.model.Model;

/**
 * Imports file using browser.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = "Opening Browser...";


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_USAGE, false, true, false);
    }

}
