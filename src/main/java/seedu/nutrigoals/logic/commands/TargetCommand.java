package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.Model;

/**
 * Adds a calorie target for the user in the application
 */
public class TargetCommand extends Command {
    public static final String COMMAND_WORD = "target";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a daily calorie target intake\n"
        + "Parameters: INTEGER (must be non-negative and not too large)\n"
        + "Example: " + COMMAND_WORD + " 5000";
    public static final String MESSAGE_TARGET_SET_SUCCESS = "Your calorie target set for today: %1$s";

    private final Calorie calorieTarget;

    public TargetCommand(Calorie calorie) {
        this.calorieTarget = calorie;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setCalorieTarget(this.calorieTarget);
        return new CommandResult(
            String.format(MESSAGE_TARGET_SET_SUCCESS, model.getCalorieTarget())
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof TargetCommand
            && this.calorieTarget.equals(((TargetCommand) other).calorieTarget));
    }
}
