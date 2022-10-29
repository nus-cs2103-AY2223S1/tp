package seedu.pennywise.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.pennywise.model.Model;
import seedu.pennywise.model.PennyWise;

/**
 * Clears the PennyWise application
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "PennyWise has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPennyWise(new PennyWise());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
