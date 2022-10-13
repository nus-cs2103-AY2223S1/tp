package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.ui.MainPanelName;

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

    /**
     * Returns true because back command can be run on every panel
     *
     * @param name of the given panel.
     * @return true
     */
    @Override
    public boolean canExecuteAt(MainPanelName name) {
        return true;
    }

}
