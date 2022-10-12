package seedu.foodrem.logic.parser.itemcommandparser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_BOUGHT_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_EXPIRY_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_UNIT;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.foodrem.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.foodrem.logic.commands.itemcommands.AddCommand;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.ParserUtil;
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
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME,
                        PREFIX_ITEM_QUANTITY,
                        PREFIX_ITEM_UNIT,
                        PREFIX_ITEM_BOUGHT_DATE,
                        PREFIX_ITEM_EXPIRY_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        ItemName name = ParserUtil.parseItemName(argMultimap.getValue(PREFIX_NAME).get());

        String quantity = argMultimap.getValue(PREFIX_ITEM_QUANTITY).orElse("");
        ItemQuantity itemQuantity = ParserUtil.parseQuantity(quantity);

        String unit = argMultimap.getValue(PREFIX_ITEM_UNIT).orElse("");
        ItemUnit itemUnit = ParserUtil.parseUnit(unit);

        String boughtDate = argMultimap.getValue(PREFIX_ITEM_BOUGHT_DATE).orElse("");
        ItemBoughtDate itemBoughtDate = ParserUtil.parseBoughtDate(boughtDate);

        String expiryDate = argMultimap.getValue(PREFIX_ITEM_EXPIRY_DATE).orElse("");
        ItemExpiryDate itemExpiryDate = ParserUtil.parseExpiryDate(expiryDate);

        Item item = new Item(name, itemQuantity, itemUnit, itemBoughtDate, itemExpiryDate);

        return new AddCommand(item);
    }

}
