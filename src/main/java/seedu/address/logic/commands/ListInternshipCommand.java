package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import seedu.address.model.Model;

/**
 * Lists all internships in InterNUS to the user.
 */
public class ListInternshipCommand extends Command {

    public static final String COMMAND_WORD = "list -i";

    public static final String MESSAGE_SUCCESS = "Listed all internships";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
