package soconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import soconnect.model.Model;
import soconnect.model.SoConnect;

/**
 * Clears the SoConnect.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "SoConnect has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSoConnect(new SoConnect());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
