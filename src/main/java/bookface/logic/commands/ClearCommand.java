package bookface.logic.commands;

import static java.util.Objects.requireNonNull;

import bookface.model.BookFace;
import bookface.model.Model;

/**
 * Clears the two lists.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear all";
    public static final String MESSAGE_SUCCESS = "All data has been cleared!";

    public static final String MESSAGE_USAGE = Command.generateMessage(COMMAND_WORD, "Clears all "
            + "program data",
            COMMAND_WORD);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setBookFace(new BookFace());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
