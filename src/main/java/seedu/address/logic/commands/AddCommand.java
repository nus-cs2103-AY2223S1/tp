package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Adds a project to HR Pro Max++.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "addproj";

    public static final String MESSAGE_SUCCESS = "New project added: %1$s";
    public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in HR Pro Max++!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a project to HR Pro Max++.\n"
            + "Parameters: "
            + PREFIX_PROJECT_NAME + "PROJECT_NAME "
            + PREFIX_BUDGET + "PROJECT_BUDGET "
            + PREFIX_DEADLINE + "PROJECT_DEADLINE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROJECT_NAME + "GEA1000 Project "
            + PREFIX_BUDGET + "200 "
            + PREFIX_DEADLINE + "2022-01-01 "
            + PREFIX_TAG + "Important "
            + PREFIX_TAG + "busy";

    private final Project toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Project}
     */
    public AddCommand(Project project) {
        requireNonNull(project);
        toAdd = project;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProject(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }

        model.addProject(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
