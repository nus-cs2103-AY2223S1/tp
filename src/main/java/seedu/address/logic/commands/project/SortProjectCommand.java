package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_DEADLINE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Sort projects in address book.
 */
public class SortProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-s";

    public static final String MESSAGE_SUCCESS = "Sorted projects by deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort projects in address book by deadline. "
            + "Parameters: "
            + PREFIX_DEADLINE + "0 (chronological) or "
            + PREFIX_DEADLINE + "1 (reverse chronological). "
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_DEADLINE + "0";

    private final int sortByDeadlineKey;

    public SortProjectCommand(int key) {
        this.sortByDeadlineKey = key;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        model.sortProjectsByDeadline(sortByDeadlineKey);
        ui.showSortedProjects();
        model.updateFilteredSortedProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
