package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.ui.Ui;

/**
 * Encapsulates a command to pin a project entity.
 */
public class PinProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-p";

    public static final String MESSAGE_PIN_SUCCESS = "Project pinned: %1$s";
    public static final String MESSAGE_UNPIN_SUCCESS = "Project unpinned: %1$s";
    public static final String MESSAGE_PROJECT_NOT_FOUND = "This project id does not exist.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Pins the project identified by the project id \n"
            + "Parameters: PROJECT_ID (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " 1";

    private ProjectId toPinProjectId;

    /**
     * Constructor for a command to pin a project to the list.
     * @param toPinProjectId The ID of the project to be pinned.
     */
    public PinProjectCommand(ProjectId toPinProjectId) {
        requireNonNull(toPinProjectId);
        this.toPinProjectId = toPinProjectId;
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
        if (!model.hasProjectId(this.toPinProjectId.getIdInt())) {
            throw new CommandException(MESSAGE_PROJECT_NOT_FOUND);
        }
        Project toPinProject = model.getProjectById(this.toPinProjectId.getIdInt());
        toPinProject.togglePin();
        model.sortProjectsByPin();
        ui.showProjects();
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(
                toPinProject.isPinned() ? MESSAGE_PIN_SUCCESS : MESSAGE_UNPIN_SUCCESS,
                toPinProject));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PinProjectCommand)) {
            return false;
        }

        return this.toPinProjectId.equals(((PinProjectCommand) other).toPinProjectId);
    }
}
