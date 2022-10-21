package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Model;


/**
 * Returns a fitness/dieting tip to the user.
 */
public class TipCommand extends Command {
    public static final String COMMAND_WORD = "tip";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": displays a healthy-living tip\n"
            + "Example: " + COMMAND_WORD;

    public static final String TIP_GENERATED_SUCCESS = "Here's your tip:\n%1$s";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String randomTip = model.getTip();
        return new CommandResult(String.format(TIP_GENERATED_SUCCESS, randomTip));
    }
}
