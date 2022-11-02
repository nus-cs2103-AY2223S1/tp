package seedu.address.logic.commands.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ISSUES;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Lists all persons in the project book to the user.
 */
public class ListIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-l";

    public static final String MESSAGE_SUCCESS = "Listed all issues in the project book";

    // TODO: implement
    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        ui.showIssues();
        model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
