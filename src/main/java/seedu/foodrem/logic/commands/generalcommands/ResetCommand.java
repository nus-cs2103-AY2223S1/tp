package seedu.foodrem.logic.commands.generalcommands;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.enums.CommandWord;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;

/**
 * Resets FoodRem.
 */
public class ResetCommand extends Command {

    public static final String MESSAGE_SUCCESS = "FoodRem has been reset!";

    private static final String COMMAND_WORD = CommandWord.RESET_COMMAND.getCommandWord();
    private static final String MESSAGE_USAGE = COMMAND_WORD + ": Resets all date in FoodRem."
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFoodRem(new FoodRem());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public static String getUsage() {
        return MESSAGE_USAGE;
    }
}
