package seedu.address.logic.commands.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_ISSUE_ID;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_URGENCY;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_DEADLINE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ISSUES;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Sort issues in address book.
 */
public class SortIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-s";

    public static final String MESSAGE_SUCCESS = "Sorted issues";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_FLAG
            + ": Sort issues in address book. \n"
            + "Sort by issue id: "
            + PREFIX_ISSUE_ID + "0 (ascending) or "
            + PREFIX_ISSUE_ID + "1 (descending). "
            + "Sort by deadline: "
            + PREFIX_DEADLINE + "0 (chronological) or "
            + PREFIX_DEADLINE + "1 (reverse chronological). "
            + "Sort by urgency: "
            + PREFIX_URGENCY + "0 (ascending) or "
            + PREFIX_URGENCY + "1 (descending). "
            + "Example: "
            + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_URGENCY + "0";

    private final Prefix sortKey;
    private final int sortOrder;

    /**
     * Specifies a sorting issue command which has a key and an order to sort by.
     *
     * @param order is the element to sort by
     * @param key is the variant of the element to sort by
     */
    public SortIssueCommand(Prefix key, int order) {
        this.sortKey = key;
        this.sortOrder = order;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        ui.showIssues();
        String sortKeyString = "";

        if (sortKey.equals(PREFIX_ISSUE_ID)) {
            model.sortIssuesById(sortOrder);
            sortKeyString = "issue id.";
        }

        if (sortKey.equals(PREFIX_DEADLINE)) {
            model.sortIssuesByDeadline(sortOrder);
            sortKeyString = "deadline.";
        }

        if (sortKey.equals(PREFIX_URGENCY)) {
            model.sortIssuesByUrgency(sortOrder);
            sortKeyString = "urgency.";
        }

        model.sortIssuesByPin();

        model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
        return new CommandResult(MESSAGE_SUCCESS + " according to " + sortKeyString);
    }
}
