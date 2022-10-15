package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.Command.MESSAGE_ADDRESS_BOOK_COMMAND_PREREQUISITE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Contains utility methods for the Command classes.
 */
public class CommandUtil {
    /**
     * Prepares the model for index based commands.
     *
     * @param model Model to be used.
     * @param index Index of the patient.
     * @throws CommandException If command input is invalid.
     */
    public static List<Person> prepareFilteredList(Model model, Index index) throws CommandException {
        requireNonNull(model);

        if (model.isRecordListDisplayed()) {
            throw new CommandException(MESSAGE_ADDRESS_BOOK_COMMAND_PREREQUISITE);
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        // Check if index given is out of bounds
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return lastShownList;
    }
}
