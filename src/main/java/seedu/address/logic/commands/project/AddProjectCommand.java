package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_CLIENT_ID;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_REPOSITORY;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_DEADLINE;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.ui.Ui;

/**
 * Adds a project to the address book.
 */
public class AddProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-a";

    public static final String MESSAGE_ADD_PROJECT_USAGE = COMMAND_WORD + ": Adds a project to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_CLIENT_ID + "CLIENT_ID "
            + PREFIX_REPOSITORY + "REPOSITORY "
            + PREFIX_DEADLINE + "DEADLINE "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_NAME + "John "
            + PREFIX_CLIENT_ID + "1 "
            + PREFIX_REPOSITORY + "JohnDoe/tp "
            + PREFIX_DEADLINE + "2022-03-05 ";

    public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in the address book";
    public static final String MESSAGE_ADD_PROJECT_SUCCESS = "New project added to the address book";

    private final Project toAddProject;

    /**
     * Creates an AddProjectCommand to add the specified {@code Project}
     */
    public AddProjectCommand(Project project) {
        requireNonNull(project);
        toAddProject = project;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);

        if (model.hasProject(toAddProject)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }

        model.addProject(toAddProject);
        return new CommandResult(String.format(MESSAGE_ADD_PROJECT_SUCCESS, toAddProject));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddProjectCommand // instanceof handles nulls
                && toAddProject.equals(((AddProjectCommand) other).toAddProject));
    }
}
