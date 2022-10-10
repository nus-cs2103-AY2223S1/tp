package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ISSUES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Lists all persons in the address book to the user.
 */
public class ListIssueCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_FLAG = "-i";

    public static final String MESSAGE_SUCCESS = "Listed all issues";


    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        ui.showIssues();
        model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
