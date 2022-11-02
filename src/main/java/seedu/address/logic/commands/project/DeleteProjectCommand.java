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
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;
import seedu.address.ui.Ui;

/**
 * Deletes a project in the project book.
 */
public class DeleteProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-d";

    public static final String MESSAGE_SUCCESS = "Deleted Project";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Deletes the project by its id. Id must be positive and valid \n"
            + "Parameters: PROJECT ID \n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_FLAG + " 1";

    public static final String MESSAGE_DELETE_PROJECT_SUCCESS = "Deleted Project: %1$s";

    public final Index targetIndex;

    public DeleteProjectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        ui.showProjects();
        List<Project> lastShownList = model.getFilteredProjectList();

        for (Project p : lastShownList) {
            if (p.getProjectIdInInt() == targetIndex.getOneBased()) {
                List<Issue> listOfIssuesToDelete = p.getIssueList();
                for (Issue i : listOfIssuesToDelete) {
                    model.deleteIssue(i);
                }

                Client projectClient = p.getClient();
                if (!projectClient.isEmpty()) {
                    Client clientInList = model.getClientById(projectClient.getClientIdInInt());
                    if (!clientInList.isEmpty()) {
                        clientInList.removeProject(p);
                        if (clientInList.getProjectListSize() == 0) {
                            model.deleteClient(clientInList);
                        }
                    }
                }

                model.deleteProject(p);
                model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
                return new CommandResult(String.format(MESSAGE_DELETE_PROJECT_SUCCESS, p));
            }
        }

        throw new CommandException(Messages.MESSAGE_PROJECT_NOT_FOUND);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteProjectCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteProjectCommand) other).targetIndex)); // state check
    }
}
