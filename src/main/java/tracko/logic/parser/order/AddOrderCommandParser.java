package tracko.logic.parser.order;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static tracko.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tracko.logic.parser.CliSyntax.PREFIX_ITEM;
import static tracko.logic.parser.CliSyntax.PREFIX_NAME;
import static tracko.logic.parser.CliSyntax.PREFIX_PHONE;
import static tracko.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.ArrayList;
import java.util.stream.Stream;

import tracko.logic.commands.order.AddOrderCommand;
import tracko.logic.parser.ArgumentMultimap;
import tracko.logic.parser.ArgumentTokenizer;
import tracko.logic.parser.Parser;
import tracko.logic.parser.ParserUtil;
import tracko.logic.parser.Prefix;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.order.Address;
import tracko.model.order.Email;
import tracko.model.order.ItemQuantityPair;
import tracko.model.order.Name;
import tracko.model.order.Order;
import tracko.model.order.Phone;

/**
 * Parses input arguments and creates a new/update AddOrderCommand object
 */
public class AddOrderCommandParser implements Parser<AddOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddOrderCommand
     * and returns an AddOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrderCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());

        Order order = new Order(name, phone, email, address, new ArrayList<>());

        return new AddOrderCommand(order);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the given AddOrderCommand that is awaiting
     * further input of order items and quantities.
     * @param args The user input
     * @param command The given AddOrderCommand
     * @return The updated command.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrderCommand parseStageTwo(String args, AddOrderCommand command) throws ParseException {
        if (args.equals("done")) {
            command.setAwaitingInput(false);
            return command;
        } else if (args.equals("cancel")) {
            command.setAwaitingInput(false);
            command.cancel();
            return command;
        }

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(" " + args, PREFIX_ITEM, PREFIX_QUANTITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM, PREFIX_QUANTITY)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE_2));
        }

        String item = argMultimap.getValue(PREFIX_ITEM).get();
        Integer quantity = Integer.parseInt(argMultimap.getValue(PREFIX_QUANTITY).get());
        command.addToItemList(new ItemQuantityPair(item, quantity));

        return command;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
