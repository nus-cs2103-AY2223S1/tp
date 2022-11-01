package seedu.address.logic.commands.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ISSUES;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.ui.Ui;

/**
 * Encapsulates a command to pin a issue entity.
 */
public class PinIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-p";

    public static final String MESSAGE_PIN_SUCCESS = "Issue pinned: %1$s";
    public static final String MESSAGE_UNPIN_SUCCESS = "Issue unpinned: %1$s";
    public static final String MESSAGE_ISSUE_NOT_FOUND = "This issue id does not exist.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Pins the issue identified by the issue id \n"
            + "Parameters: ISSUE_ID (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " 1";

    private IssueId toPinIssueId;

    /**
     * Constructor for a command to pin an issue to the list.
     * @param toPinIssueId The ID of the issue to be pinned.
     */
    public PinIssueCommand(IssueId toPinIssueId) {
        requireNonNull(toPinIssueId);
        this.toPinIssueId = toPinIssueId;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @param ui
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        if (!model.hasIssueId(this.toPinIssueId.getIdInt())) {
            throw new CommandException(MESSAGE_ISSUE_NOT_FOUND);
        }
        Issue toPinIssue = model.getIssueById(this.toPinIssueId.getIdInt());
        toPinIssue.togglePin();
        model.sortIssuesByPin();
        ui.showIssues();
        model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
        return new CommandResult(String.format(
                toPinIssue.isPinned() ? MESSAGE_PIN_SUCCESS : MESSAGE_UNPIN_SUCCESS,
                toPinIssue));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PinIssueCommand)) {
            return false;
        }

        return this.toPinIssueId.equals(((PinIssueCommand) other).toPinIssueId);
    }
}
