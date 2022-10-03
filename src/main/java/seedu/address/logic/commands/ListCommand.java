package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMPANIES;

import seedu.address.model.Model;

/**
 * Lists all companies in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all companies";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
