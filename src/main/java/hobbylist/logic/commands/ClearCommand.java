package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import hobbylist.model.HobbyList;
import hobbylist.model.Model;

/**
 * Clears the HobbyList.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "HobbyList has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setHobbyList(new HobbyList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
