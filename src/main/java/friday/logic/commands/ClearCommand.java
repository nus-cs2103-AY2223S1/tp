package friday.logic.commands;

import static java.util.Objects.requireNonNull;

import friday.model.Friday;
import friday.model.Model;

/**
 * Clears FRIDAY.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "FRIDAY has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFriday(new Friday());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
