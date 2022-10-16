package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_CLIENT_ID;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_PROJECT_ID;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_REPOSITORY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.ui.Ui;

/**
 * Lists all persons in the address book to the user.
 */
public class EditProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-e";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a project to the address book. "
            + "Parameters: "
            + PREFIX_PROJECT_ID + "PROJECT_ID "
            + PREFIX_NAME + "NAME "
            + PREFIX_CLIENT_ID + "CLIENT_ID "
            + PREFIX_REPOSITORY + "REPOSITORY "
            + PREFIX_DEADLINE + "DEADLINE "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_PROJECT_ID + "1 "
            + PREFIX_NAME + "John "
            + PREFIX_CLIENT_ID + "1 "
            + PREFIX_REPOSITORY + "JohnDoe/tp "
            + PREFIX_DEADLINE + "2022-03-05 ";

    public static final String MESSAGE_SUCCESS = "Project %1$s has been edited";

    private final Project toEditProject;

    /**
     * Creates an EditProjectCommand to edit the specified {@code Project}
     */
    public EditProjectCommand(Project project) {
        requireNonNull(project);
        toEditProject = project;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        ui.showProjects();
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toEditProject));
    }
}
