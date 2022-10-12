package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;

import jeryl.fyp.model.FypManager;
import jeryl.fyp.model.Model;

/**
 * Clears the FYP manager.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFypManager(new FypManager());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
