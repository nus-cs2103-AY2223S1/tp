package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.waddle.model.Model;
import seedu.waddle.model.Waddle;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Waddle has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setWaddle(new Waddle());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
