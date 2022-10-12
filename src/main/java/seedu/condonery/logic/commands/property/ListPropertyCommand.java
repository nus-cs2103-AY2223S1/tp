package seedu.condonery.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;

import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.model.Model;

/**
 * Lists all properties in the address book to the user.
 */
public class ListPropertyCommand extends Command {

    public static final String COMMAND_WORD = "list -p";

    public static final String MESSAGE_SUCCESS = "Listed all properties";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
