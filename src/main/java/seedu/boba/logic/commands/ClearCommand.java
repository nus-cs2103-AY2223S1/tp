package seedu.boba.logic.commands;

import seedu.boba.model.BobaBot;
import seedu.boba.model.BobaBotModel;

import static java.util.Objects.requireNonNull;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "bobaBot has been cleared!";


    @Override
    public CommandResult execute(BobaBotModel bobaBotModel) {
        requireNonNull(bobaBotModel);
        bobaBotModel.setBobaBot(new BobaBot());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
