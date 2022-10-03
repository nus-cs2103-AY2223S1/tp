package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Calorie;
import seedu.address.model.Model;

/**
 * Adds a calorie target for the user in the application
 */
public class TargetCommand extends Command {
    public static final String COMMAND_WORD = "target";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Sets a daily calorie target intake\n"
        + "Parameters: [Integer]...\n"
        + "Example: " + COMMAND_WORD + " 5000";
    public static final String MESSAGE_TARGET_SET = "Your calorie target set is %1$s";

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
            String.format(MESSAGE_TARGET_SET, model.getCalorieTarget())
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof TargetCommand
            && this.calorieTarget.equals(((TargetCommand) other).calorieTarget));
    }
}
