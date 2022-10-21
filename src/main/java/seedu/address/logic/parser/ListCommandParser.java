package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object.
 */
public class ListCommandParser implements Parser<ListCommand> {
    @Override
    public ListCommand parse(String userInput) throws ParseException {
        if (userInput.isEmpty()) {
            return new ListCommand(ListCommand.LIST_EMPTY);
        }
        userInput = userInput.trim().toUpperCase();
        switch (userInput) {
        case ListCommand.LIST_BUYER:
            return new ListCommand(ListCommand.LIST_BUYER);
        case ListCommand.LIST_SUPPLIER:
            return new ListCommand(ListCommand.LIST_SUPPLIER);
        case ListCommand.LIST_DELIVERER:
            return new ListCommand(ListCommand.LIST_DELIVERER);
        case ListCommand.LIST_ORDER:
            return new ListCommand(ListCommand.LIST_ORDER);
        case ListCommand.LIST_PET:
            return new ListCommand(ListCommand.LIST_PET);
        case ListCommand.LIST_ALL:
            return new ListCommand(ListCommand.LIST_ALL);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }
}
