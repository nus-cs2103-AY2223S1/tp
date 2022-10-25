package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Switches the theme of the program.
 */
public class SwitchCommand extends Command {

    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Switched themes!";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, true);
    }

}
