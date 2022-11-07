package seedu.taassist.logic.commands;

import seedu.taassist.logic.commands.actions.UiAction;
import seedu.taassist.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting TA-Assist as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, UiAction.EXIT);
    }

}
