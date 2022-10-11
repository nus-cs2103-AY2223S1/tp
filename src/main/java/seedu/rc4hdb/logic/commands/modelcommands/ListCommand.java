package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.model.Model.PREDICATE_SHOW_ALL_RESIDENTS;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends ModelCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
