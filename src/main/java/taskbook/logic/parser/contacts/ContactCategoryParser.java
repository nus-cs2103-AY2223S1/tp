package taskbook.logic.parser.contacts;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.Command;
import taskbook.logic.commands.contacts.ContactAddCommand;
import taskbook.logic.commands.contacts.ContactDeleteCommand;
import taskbook.logic.commands.contacts.ContactEditCommand;
import taskbook.logic.commands.contacts.ContactFindCommand;
import taskbook.logic.commands.contacts.ContactListCommand;
import taskbook.logic.commands.contacts.ContactSortCommand;
import taskbook.logic.parser.exceptions.ParseException;

/**
 * Parses user input of contact category.
 */
public class ContactCategoryParser {
    public static final String CATEGORY_WORD = "contact";
    public static final String CATEGORY_WORD_SHORTCUT = "c";

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
        case ContactEditCommand.COMMAND_WORD:
            return new ContactEditCommandParser().parse(arguments);
        case ContactDeleteCommand.COMMAND_WORD:
            return new ContactDeleteCommandParser().parse(arguments);
        case ContactListCommand.COMMAND_WORD:
            return new ContactListCommand();
        case ContactFindCommand.COMMAND_WORD:
            return new ContactFindCommandParser().parse(arguments);
        case ContactSortCommand.COMMAND_WORD:
            return new ContactSortCommandParser().parse(arguments);
        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
