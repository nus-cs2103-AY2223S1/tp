package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.application.model.ApplicationBook;
import seedu.application.model.Model;

/**
 * Clears the application book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Application book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setApplicationBook(new ApplicationBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
