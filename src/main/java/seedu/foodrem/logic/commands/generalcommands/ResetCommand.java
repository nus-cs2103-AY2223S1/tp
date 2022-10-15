package seedu.foodrem.logic.commands.generalcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.enums.CommandWord.RESET_COMMAND;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;

/**
 * Resets FoodRem.
 */
public class ResetCommand extends Command {
    private static final String MESSAGE_SUCCESS = "FoodRem has been reset!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFoodRem(new FoodRem());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public static String getUsage() {
        return RESET_COMMAND.getUsage();
    }
}
