package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROFILES;

import seedu.address.model.Model;

/**
 * Lists all profiles in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all profiles";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredProfileList(PREDICATE_SHOW_ALL_PROFILES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
