package seedu.address.logic.commands.project;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.ui.Ui;

public class PinProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-p";

    public static final String MESSAGE_SUCCESS = "Project pinned: %1$s";

    public Project toPinProject;

    public PinProjectCommand(Project toPinProject) {
        this.toPinProject = toPinProject;
    }

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
        this.toPinProject.togglePin();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toPinProject));
    }
}
