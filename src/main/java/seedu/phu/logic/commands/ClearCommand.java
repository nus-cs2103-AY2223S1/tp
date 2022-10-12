package seedu.phu.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.phu.model.InternshipBook;
import seedu.phu.model.Model;

/**
 * Clears the internship book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Internship book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setInternshipBook(new InternshipBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
