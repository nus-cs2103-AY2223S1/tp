package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;

import tuthub.model.Tuthub;
import tuthub.model.Model;

/**
 * Clears tuthub.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Tuthub has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTuthub(new Tuthub());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
