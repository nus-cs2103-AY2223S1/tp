package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Back to the last MainPanel.
 */
public class BackCommand extends Command {

    public static final String COMMAND_WORD = "back";

    public static final String MESSAGE_BACK_ACKNOWLEDGEMENT = "Back to the previous page...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_BACK_ACKNOWLEDGEMENT, false, false, true);
    }

}
