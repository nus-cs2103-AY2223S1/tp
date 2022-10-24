package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.util.CommandUtil;
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

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_BUYER_PARAMETER, userInput)) {
            return new ListCommand(ListCommand.LIST_BUYER);
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SUPPLIER_PARAMETER, userInput)) {
            return new ListCommand(ListCommand.LIST_SUPPLIER);
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_DELIVERER_PARAMETER, userInput)) {
            return new ListCommand(ListCommand.LIST_DELIVERER);
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_ORDER_PARAMETER, userInput)) {
            return new ListCommand(ListCommand.LIST_ORDER);
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_PET_PARAMETER, userInput)) {
            return new ListCommand(ListCommand.LIST_PET);
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_ALL_PARAMETER, userInput)) {
            return new ListCommand(ListCommand.LIST_ALL);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));

    }
}
