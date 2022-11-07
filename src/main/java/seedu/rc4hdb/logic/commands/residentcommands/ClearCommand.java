package seedu.rc4hdb.logic.commands.residentcommands;

import static java.util.Objects.requireNonNull;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.ModelCommand;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.VenueBook;

/**
 * Clears the address book.
 */
public class ClearCommand implements ModelCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All data has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setResidentBook(new ResidentBook());
        model.setVenueBook(new VenueBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
