package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_CLIENT_ID;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_REPOSITORY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectWithoutModel;
import seedu.address.ui.Ui;

/**
 * Adds a project to the project book.
 */
public class AddProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-a";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Adds a project to the project book. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_CLIENT_ID + "CLIENT_ID "
            + PREFIX_REPOSITORY + "REPOSITORY "
            + PREFIX_DEADLINE + "DEADLINE \n"
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_NAME + "John "
            + PREFIX_CLIENT_ID + "1 "
            + PREFIX_REPOSITORY + "JohnDoe/tp "
            + PREFIX_DEADLINE + "2022-03-05 ";

    public static final String MESSAGE_SUCCESS = "New project added: %1$s";
    public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in the project book";
    public static final String MESSAGE_CLIENT_NOT_FOUND = "This client id does not exist in the project book";

    private final ProjectWithoutModel toAddProjectWithoutModel;

    /**
     * Creates an AddProjectCommand to add the specified {@code Project}
     */
    public AddProjectCommand(ProjectWithoutModel projectWithoutModel) {
        requireNonNull(projectWithoutModel);
        toAddProjectWithoutModel = projectWithoutModel;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        ui.showProjects();

        Project toAddProject = toAddProjectWithoutModel.apply(model);

        if (model.hasProject(toAddProject)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }

        Client projectClient = toAddProject.getClient();

        if (projectClient == null) {
            throw new CommandException(MESSAGE_CLIENT_NOT_FOUND);
        }

        if (!projectClient.isEmpty()) {
            projectClient.addProjects(toAddProject);
            model.setClient(projectClient, projectClient);
        }

        model.addProject(toAddProject);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAddProject));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof AddProjectCommand)) {
            return false;
        }

        return this.toAddProjectWithoutModel.equals(((AddProjectCommand) other).toAddProjectWithoutModel);
    }
}
