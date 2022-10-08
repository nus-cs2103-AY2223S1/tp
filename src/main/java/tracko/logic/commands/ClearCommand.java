package tracko.logic.commands;

import static java.util.Objects.requireNonNull;

import tracko.model.Model;
import tracko.model.TrackO;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "TrackO has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTrackO(new TrackO());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
