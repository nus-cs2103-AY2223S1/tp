package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Patient list has been cleared!";

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isRecordListDisplayed()) {
            logger.warning("Patient List View not currently displayed.");
            throw new CommandException(MESSAGE_ADDRESS_BOOK_COMMAND_PREREQUISITE);
        }

        model.setAddressBook(new AddressBook());

        logger.info("Patient List has been cleared successfully.");

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
