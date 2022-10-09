package seedu.foodrem.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;

/**
 * Clears the FoodRem.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new FoodRem());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
