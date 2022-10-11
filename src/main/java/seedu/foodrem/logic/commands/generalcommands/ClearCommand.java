package seedu.foodrem.logic.commands.generalcommands;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;

/**
 * Clears FoodRem.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "FoodRem has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFoodRem(new FoodRem());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
