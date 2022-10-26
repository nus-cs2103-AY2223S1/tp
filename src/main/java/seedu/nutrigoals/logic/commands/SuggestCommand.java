package seedu.nutrigoals.logic.commands;

import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.Model;

/**
 * Suggests a recommended daily calorie intake based on the user's profile
 */
public class SuggestCommand extends Command {

    public static final String COMMAND_WORD = "suggest";
    public static final String MESSAGE_SUGGEST_DETAILS = "Your suggested daily calorie intake to achieve "
            + "your ideal weight is: %1$s";
    public static final String MESSAGE_NO_PROFILE_CREATED = "Please set up your profile before proceeding!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Suggests an estimated daily calorie intake."
            + "\nExample: suggest";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.isUserCreated()) {
            throw new CommandException(MESSAGE_NO_PROFILE_CREATED);
        }

        Calorie suggestedCalorieIntake = model.calculateSuggestedCalorie();
        return new CommandResult(String.format(MESSAGE_SUGGEST_DETAILS, suggestedCalorieIntake));
    }
}
