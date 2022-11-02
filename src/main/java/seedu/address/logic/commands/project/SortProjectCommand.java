package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_ISSUE_COUNT;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_PROJECT_ID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Sort projects in project book.
 */
public class SortProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-s";

    public static final String MESSAGE_SUCCESS = "Sorted projects";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_FLAG
            + ": Sort projects in project book. \n"
            + "Sort by project id: "
            + PREFIX_PROJECT_ID + "0 (ascending) or "
            + PREFIX_PROJECT_ID + "1 (descending). "
            + "Sort by deadline: "
            + PREFIX_DEADLINE + "0 (chronological) or "
            + PREFIX_DEADLINE + "1 (reverse chronological). "
            + "Sort by issue count: "
            + PREFIX_ISSUE_COUNT + "0 (incomplete) or "
            + PREFIX_ISSUE_COUNT + "1 (completed). "
            + "Sort by name: "
            + PREFIX_NAME + "0 (alphabetical) or "
            + PREFIX_NAME + "1 (reverse alphabetical). \n"
            + "Example: "
            + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_DEADLINE + "0";

    private final Prefix sortKey;
    private final int sortOrder;

    /**
     * Specifies a sorting project command which has a key and an order to sort by.
     *
     * @param order is the element to sort by
     * @param key is the variant of the element to sort by
     */
    public SortProjectCommand(Prefix key, int order) {
        this.sortKey = key;
        this.sortOrder = order;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        String sortKeyString = "";

        if (sortKey.equals(PREFIX_DEADLINE)) {
            model.sortProjectsByDeadline(sortOrder);
            sortKeyString = "deadline.";
        }

        if (sortKey.equals(PREFIX_ISSUE_COUNT)) {
            model.sortProjectsByIssueCount(sortOrder);
            sortKeyString = "issue count.";
        }

        if (sortKey.equals(PREFIX_NAME)) {
            model.sortProjectsByName(sortOrder);
            sortKeyString = "names.";
        }

        if (sortKey.equals(PREFIX_PROJECT_ID)) {
            model.sortProjectsById(sortOrder);
            sortKeyString = "project id.";
        }

        model.sortProjectsByPin();

        ui.showProjects();
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(MESSAGE_SUCCESS + " according to " + sortKeyString);
    }
}
