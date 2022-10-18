package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_DEADLINE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

public class SortProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-s";

    public static final String MESSAGE_SUCCESS = "Sorted projects by deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort projects in address book by deadline."
            + "Parameters: "
            + PREFIX_DEADLINE + " "
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_DEADLINE;

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        model.sortProjectsByDeadline();
        ui.showSortedProjects();
        model.updateFilteredSortedProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
