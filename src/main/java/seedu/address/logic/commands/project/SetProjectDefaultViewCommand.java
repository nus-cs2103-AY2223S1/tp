package seedu.address.logic.commands.project;

import seedu.address.commons.core.DefaultView;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Encapsulates a command to set the default view to project.
 */
public class SetProjectDefaultViewCommand extends ProjectCommand {
    public static final String COMMAND_FLAG = "-v";

    public static final String MESSAGE_SUCCESS = "Default view successfully set to projects.";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @param ui
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        model.setDefaultView(DefaultView.PROJECT);
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
