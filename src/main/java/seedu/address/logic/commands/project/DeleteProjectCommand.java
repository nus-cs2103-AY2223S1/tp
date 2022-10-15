package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.UniqueClientList;
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;
import seedu.address.ui.Ui;

/**
 * Deletes a project in the address book.
 */
public class DeleteProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-d";

    public static final String MESSAGE_SUCCESS = "Deleted Project";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the project identified by the index number used in the displayed project list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PROJECT_SUCCESS = "Deleted Project: %1$s";

    public final Index targetIndex;

    public DeleteProjectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToDelete = lastShownList.get(targetIndex.getZeroBased());
        List<Issue> listOfIssuesToDelete = projectToDelete.getIssueList();
        for (Issue i : listOfIssuesToDelete) {
            model.deleteIssue(i);
        }
        model.deleteProject(projectToDelete);

        Client projectClient = projectToDelete.getClient();

        if (!projectClient.isEmpty()) {
            projectClient.removeProject(projectToDelete);
            if (projectClient.getProjectListSize() == 0) {
                UniqueClientList.remove(projectClient);
            }
        }

        ui.showProjects();
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);


        return new CommandResult(String.format(MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteProjectCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteProjectCommand) other).targetIndex)); // state check
    }
}
