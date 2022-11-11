package seedu.realtime.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.realtime.model.Model;
import seedu.realtime.model.RealTime;


/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setRealTime(new RealTime());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
