package foodwhere.logic.commands;

import static java.util.Objects.requireNonNull;

import foodwhere.model.AddressBook;
import foodwhere.model.Model;

/**
 * Clears the list of stalls and reviews in FoodWhere.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "FoodWhere has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
