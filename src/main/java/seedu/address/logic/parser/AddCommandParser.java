package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_BOUGHT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_UNIT;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemBoughtDate;
import seedu.address.model.item.ItemExpiryDate;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.ItemQuantity;
import seedu.address.model.item.ItemUnit;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_ITEM_NAME,
                        PREFIX_ITEM_QUANTITY,
                        PREFIX_ITEM_UNIT,
                        PREFIX_ITEM_BOUGHT_DATE,
                        PREFIX_ITEM_EXPIRY_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM_NAME,
                PREFIX_ITEM_QUANTITY,
                PREFIX_ITEM_UNIT,
                PREFIX_ITEM_BOUGHT_DATE,
                PREFIX_ITEM_EXPIRY_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        ItemName name = ParserUtil.parseName(argMultimap.getValue(PREFIX_ITEM_NAME).get());
        ItemQuantity quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_ITEM_QUANTITY).get());
        ItemUnit unit = ParserUtil.parseUnit(argMultimap.getValue(PREFIX_ITEM_UNIT).get());
        ItemBoughtDate boughtDate = ParserUtil.parseBoughtDate(argMultimap.getValue(PREFIX_ITEM_BOUGHT_DATE).get());
        ItemExpiryDate expiryDate = ParserUtil.parseExpiryDate(argMultimap.getValue(PREFIX_ITEM_EXPIRY_DATE).get());

        Item item = new Item(name, quantity, unit, boughtDate, expiryDate);

        return new AddCommand(item);
    }

}
