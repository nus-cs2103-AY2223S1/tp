package seedu.address.logic.parser.contacts;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.contacts.ContactAddCommand;
import seedu.address.logic.commands.contacts.ContactDeleteCommand;
import seedu.address.logic.commands.contacts.ContactListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input of contact category.
 */
public class ContactCategoryParser {
    public static final String CATEGORY_WORD = "contact";

    /**
     * Parses user input into command for execution.
     *
     * @param commandWord Command word provided by the user.
     * @param arguments   Arguments provided by the user.
     * @return The command based on the user command word and arguments.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public static Command parseCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case ContactAddCommand.COMMAND_WORD:
            return new ContactAddCommandParser().parse(arguments);
        case ContactDeleteCommand.COMMAND_WORD:
            return new ContactDeleteCommandParser().parse(arguments);
        case ContactListCommand.COMMAND_WORD:
            return new ContactListCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
