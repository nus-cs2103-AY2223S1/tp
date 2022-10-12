package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_POLICIES;
import static seedu.address.model.Model.PREDICATE_SHOW_NO_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class PolicyCommand extends Command {

    public static final String COMMAND_WORD = "showPolicy";

    public static final String MESSAGE_SUCCESS = "Listed all policies";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPolicyList(PREDICATE_SHOW_ALL_POLICIES);
        model.updateFilteredPersonList(PREDICATE_SHOW_NO_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
