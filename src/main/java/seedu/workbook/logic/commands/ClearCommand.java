package seedu.workbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.workbook.model.Model;
import seedu.workbook.model.WorkBook;

/**
 * Clears the WorkBook.
 */
public class ClearCommand extends Command {

    /** Command word to execute the clear command */
    public static final String COMMAND_WORD = "clear";

    /** Message string displaying successful execution of the clear command */
    public static final String MESSAGE_SUCCESS = "Work book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setWorkBook(new WorkBook());
        model.commitWorkBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
