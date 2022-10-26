package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.MassLinkers;
import seedu.address.model.Model;

/**
 * Clears the mass linkers.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Mass Linkers has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setMassLinkers(new MassLinkers());
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false);
    }
}
