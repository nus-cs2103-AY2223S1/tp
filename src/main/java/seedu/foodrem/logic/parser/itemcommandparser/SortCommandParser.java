package seedu.foodrem.logic.parser.itemcommandparser;

import java.util.Comparator;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.logic.commands.itemcommands.SortCommand;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.CliSyntax;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.itemcomparators.ItemBoughtDateComparator;
import seedu.foodrem.model.item.itemcomparators.ItemExpiryDateComparator;
import seedu.foodrem.model.item.itemcomparators.ItemNameComparator;
import seedu.foodrem.model.item.itemcomparators.ItemPriceComparator;
import seedu.foodrem.model.item.itemcomparators.ItemQuantityComparator;
import seedu.foodrem.model.item.itemcomparators.ItemRemarkComparator;
import seedu.foodrem.model.item.itemcomparators.ItemUnitComparator;

/**
 * Parses input arguments and creates a new
 * SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(trimmedArgs);

        String preamble = argMultimap.getPreamble().trim();

        if (preamble.isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.getUsage()));
        }

        Comparator<Item> comparator = null;

        if (preamble.equals(CliSyntax.PREFIX_NAME.toString())) {
            comparator = new ItemNameComparator();
        }
        if (preamble.equals(CliSyntax.PREFIX_ITEM_QUANTITY.toString())) {
            comparator = new ItemQuantityComparator();
        }
        if (preamble.equals(CliSyntax.PREFIX_ITEM_BOUGHT_DATE.toString())) {
            comparator = new ItemBoughtDateComparator();
        }
        if (preamble.equals(CliSyntax.PREFIX_ITEM_EXPIRY_DATE.toString())) {
            comparator = new ItemExpiryDateComparator();
        }
        if (preamble.equals(CliSyntax.PREFIX_ITEM_UNIT.toString())) {
            comparator = new ItemUnitComparator();
        }
        if (preamble.equals(CliSyntax.PREFIX_ITEM_PRICE.toString())) {
            comparator = new ItemPriceComparator();
        }
        if (preamble.equals(CliSyntax.PREFIX_ITEM_REMARKS.toString())) {
            comparator = new ItemRemarkComparator();
        }

        if (comparator == null) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.getUsage()));
        }

        return new SortCommand(comparator);
    }
}
