package seedu.workbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.workbook.model.Model;
import seedu.workbook.model.WorkBook;

/**
 * Clears the work book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Work book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setWorkBook(new WorkBook());
        model.commitWorkBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
