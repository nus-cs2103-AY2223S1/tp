package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.listcommands.ListAllCommand;
import seedu.address.logic.commands.listcommands.ListBuyerCommand;
import seedu.address.logic.commands.listcommands.ListCommand;
import seedu.address.logic.commands.listcommands.ListDelivererCommand;
import seedu.address.logic.commands.listcommands.ListOrderCommand;
import seedu.address.logic.commands.listcommands.ListPetCommand;
import seedu.address.logic.commands.listcommands.ListSupplierCommand;
import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object.
 */
public class ListCommandParser implements Parser<ListCommand> {
    @Override
    public ListCommand parse(String userInput) throws ParseException {
        userInput = userInput.trim().toUpperCase();

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_BUYER_PARAMETER, userInput)) {
            return new ListBuyerCommand();
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SUPPLIER_PARAMETER, userInput)) {
            return new ListSupplierCommand();
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_DELIVERER_PARAMETER, userInput)) {
            return new ListDelivererCommand();
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_ORDER_PARAMETER, userInput)) {
            return new ListOrderCommand();
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_PET_PARAMETER, userInput)) {
            return new ListPetCommand();
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_ALL_PARAMETER, userInput)) {
            return new ListAllCommand();
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));

    }
}
