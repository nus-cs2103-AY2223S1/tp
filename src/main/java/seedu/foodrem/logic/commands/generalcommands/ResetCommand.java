package seedu.foodrem.logic.commands.generalcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.RESET_COMMAND;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;

/**
 * Resets FoodRem.
 */
public class ResetCommand extends Command {
    @Override
    public CommandResult<String> execute(Model model) {
        requireNonNull(model);
        model.setFoodRem(new FoodRem());
        return CommandResult.from("FoodRem has been reset!");
    }

    public static String getUsage() {
        return RESET_COMMAND.getUsage();
    }
}
