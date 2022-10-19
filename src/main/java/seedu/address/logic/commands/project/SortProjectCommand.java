package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_ISSUE_COUNT;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Sort projects in address book.
 */
public class SortProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-s";

    public static final String MESSAGE_SUCCESS = "Sorted projects";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort projects in address book. "
            + "Sort by deadline: "
            + PREFIX_DEADLINE + "0 (chronological) or "
            + PREFIX_DEADLINE + "1 (reverse chronological). "
            + "Sort by issue count: "
            + PREFIX_ISSUE_COUNT + "0 (ascending) or "
            + PREFIX_ISSUE_COUNT + "1 (descending). "
            + "Sort by name count: "
            + PREFIX_NAME + "0 (alphabetical) or "
            + PREFIX_NAME + "1 (reverse alphabetical). "
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_DEADLINE + "0";

    private final int sortKey;
    private final Prefix sortPrefix;

    public SortProjectCommand(Prefix prefix, int key) {
        this.sortPrefix = prefix;
        this.sortKey = key;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        String sortPrefixString = "";

        if (sortPrefix.equals(PREFIX_DEADLINE)) {
            model.sortProjectsByDeadline(sortKey);
            sortPrefixString = "deadline.";
        }

        if (sortPrefix.equals(PREFIX_ISSUE_COUNT)) {
            model.sortProjectsByIssueCount(sortKey);
            sortPrefixString = "issue count.";
        }

        if (sortPrefix.equals(PREFIX_NAME)) {
            model.sortProjectsByName(sortKey);
            sortPrefixString = "names.";
        }

        ui.showSortedProjects();
        model.updateFilteredSortedProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(MESSAGE_SUCCESS + " according to " + sortPrefixString);
    }
}
