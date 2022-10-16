package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.uninurse.model.Model;
import seedu.uninurse.model.UninurseBook;

/**
 * Clears the uninurse book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "UniNurse Book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setUninurseBook(new UninurseBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
