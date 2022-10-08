package seedu.address.logic.commands.profile;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROFILES;

/**
 * Shows a list of all the profiles in the address book.
 */
public class ViewProfilesCommand extends ProfileCommand {

    public static final String COMMAND_OPTION = "v";

    public static final String MESSAGE_SUCCESS = "Listed all profiles";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredProfileList(PREDICATE_SHOW_ALL_PROFILES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
