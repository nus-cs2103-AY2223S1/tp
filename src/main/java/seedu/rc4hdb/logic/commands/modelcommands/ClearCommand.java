package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ResidentBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends ModelCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Resident book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setResidentBook(new ResidentBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
