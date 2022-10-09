package seedu.foodrem.logic.parser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_BOUGHT_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_EXPIRY_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_UNIT;

import java.util.stream.Stream;

import seedu.foodrem.logic.commands.AddCommand;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.ItemBoughtDate;
import seedu.foodrem.model.item.ItemExpiryDate;
import seedu.foodrem.model.item.ItemName;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.model.item.ItemUnit;

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

        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM_NAME)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        ItemName name = ParserUtil.parseName(argMultimap.getValue(PREFIX_ITEM_NAME).get());

        // TODO: Refactor this to have default values / copy edit command.
        String quantity = "";
        if (argMultimap.getValue(PREFIX_ITEM_QUANTITY).isPresent()) {
            quantity = argMultimap.getValue(PREFIX_ITEM_QUANTITY).get();
        }
        ItemQuantity itemQuantity = ParserUtil.parseQuantity(quantity);

        String unit = "";
        if (argMultimap.getValue(PREFIX_ITEM_UNIT).isPresent()) {
            unit = argMultimap.getValue(PREFIX_ITEM_UNIT).get();
        }
        ItemUnit itemUnit = ParserUtil.parseUnit(unit);

        String boughtDate = "";
        if (argMultimap.getValue(PREFIX_ITEM_BOUGHT_DATE).isPresent()) {
            boughtDate = argMultimap.getValue(PREFIX_ITEM_BOUGHT_DATE).get();
        }
        ItemBoughtDate itemBoughtDate = ParserUtil.parseBoughtDate(boughtDate);

        String expiryDate = "";
        if (argMultimap.getValue(PREFIX_ITEM_EXPIRY_DATE).isPresent()) {
            expiryDate = argMultimap.getValue(PREFIX_ITEM_EXPIRY_DATE).get();
        }
        ItemExpiryDate itemExpiryDate = ParserUtil.parseExpiryDate(expiryDate);

        Item item = new Item(name, itemQuantity, itemUnit, itemBoughtDate, itemExpiryDate);

        return new AddCommand(item);
    }

}
