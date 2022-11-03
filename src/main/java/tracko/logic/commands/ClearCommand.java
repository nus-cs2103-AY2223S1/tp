package tracko.logic.commands;

import static java.util.Objects.requireNonNull;

import tracko.logic.commands.exceptions.CommandException;
import tracko.model.Model;
import tracko.model.TrackO;

/**
 * Clears the address book.
 */
public class ClearCommand extends MultiLevelCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "TrackO has been cleared!";
    public static final String CLEAR_CONFIRMATION_MESSAGE = "This command will clear all data stored in TrackO.\n"
            + "Please enter 'confirm' to confirm deletion of all data stored in TrackO.\n"
            + "Otherwise, enter 'cancel' to abort clearing your data.";
    static final String MESSAGE_COMMAND_ABORTED = "Clear TrackO command aborted";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.isCancelled) {
            // User has cancelled the command
            return new CommandResult(MESSAGE_COMMAND_ABORTED);
        }

        if (!this.isAwaitingInput()) {
            model.setTrackO(new TrackO());
            return new CommandResult(MESSAGE_SUCCESS);
        }

        return new CommandResult(CLEAR_CONFIRMATION_MESSAGE);

    }
}
