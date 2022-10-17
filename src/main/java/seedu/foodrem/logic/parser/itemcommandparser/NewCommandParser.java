package seedu.foodrem.logic.parser.itemcommandparser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.foodrem.logic.commands.itemcommands.NewCommand;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.CliSyntax;
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
public class NewCommandParser implements Parser<NewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public NewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        CliSyntax.PREFIX_NAME,
                        CliSyntax.PREFIX_ITEM_QUANTITY,
                        CliSyntax.PREFIX_ITEM_UNIT,
                        CliSyntax.PREFIX_ITEM_BOUGHT_DATE,
                        CliSyntax.PREFIX_ITEM_EXPIRY_DATE);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewCommand.getUsage()));
        }

        ItemName name = ParserUtil.parseItemName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());

        String quantity = argMultimap.getValue(CliSyntax.PREFIX_ITEM_QUANTITY).orElse("");
        ItemQuantity itemQuantity = ParserUtil.parseQuantity(quantity);

        String unit = argMultimap.getValue(CliSyntax.PREFIX_ITEM_UNIT).orElse("");
        ItemUnit itemUnit = ParserUtil.parseUnit(unit);

        String boughtDate = argMultimap.getValue(CliSyntax.PREFIX_ITEM_BOUGHT_DATE).orElse("");
        ItemBoughtDate itemBoughtDate = ParserUtil.parseBoughtDate(boughtDate);

        String expiryDate = argMultimap.getValue(CliSyntax.PREFIX_ITEM_EXPIRY_DATE).orElse("");
        ItemExpiryDate itemExpiryDate = ParserUtil.parseExpiryDate(expiryDate);

        Item item = new Item(name, itemQuantity, itemUnit, itemBoughtDate, itemExpiryDate);

        return new NewCommand(item);
    }

}
