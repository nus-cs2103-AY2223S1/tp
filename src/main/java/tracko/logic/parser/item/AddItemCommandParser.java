package tracko.logic.parser.item;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static tracko.logic.parser.CliSyntax.PREFIX_ITEM;
import static tracko.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.HashSet;
import java.util.stream.Stream;

import tracko.logic.commands.item.AddItemCommand;
import tracko.logic.parser.ArgumentMultimap;
import tracko.logic.parser.ArgumentTokenizer;
import tracko.logic.parser.Parser;
import tracko.logic.parser.ParserUtil;
import tracko.logic.parser.Prefix;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.items.Description;
import tracko.model.items.Item;
import tracko.model.items.ItemName;
import tracko.model.items.Quantity;

/**
 * Parses input arguments and creates a new/update AddItemCommand Object.
 */
public class AddItemCommandParser implements Parser<AddItemCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddItemCommand
     * and returns an AddItemCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddItemCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ITEM, PREFIX_QUANTITY, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM, PREFIX_QUANTITY, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE));
        }

        ItemName itemName = ParserUtil.parseItemName(argMultimap.getValue(PREFIX_ITEM).get());
        Quantity quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        Item item = new Item(itemName, description, quantity, new HashSet<>(), sellPrice, costPrice);

        return new AddItemCommand(item);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
