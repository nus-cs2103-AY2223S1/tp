package seedu.foodrem.logic.parser.itemcommandparser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

import seedu.foodrem.logic.commands.itemcommands.SortCommand;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.CliSyntax;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.item.itemcomparators.ItemBoughtDateComparator;
import seedu.foodrem.model.item.itemcomparators.ItemComparator;
import seedu.foodrem.model.item.itemcomparators.ItemExpiryDateComparator;
import seedu.foodrem.model.item.itemcomparators.ItemNameComparator;
import seedu.foodrem.model.item.itemcomparators.ItemPriceComparator;
import seedu.foodrem.model.item.itemcomparators.ItemQuantityComparator;
import seedu.foodrem.model.item.itemcomparators.ItemUnitComparator;
import seedu.foodrem.model.util.ChainComparator;

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
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.getUsage()));
        }
        ArrayList<ItemComparator> comparators = new ArrayList<>();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                CliSyntax.PREFIX_NAME,
                CliSyntax.PREFIX_ITEM_QUANTITY,
                CliSyntax.PREFIX_ITEM_BOUGHT_DATE,
                CliSyntax.PREFIX_ITEM_EXPIRY_DATE,
                CliSyntax.PREFIX_ITEM_UNIT,
                CliSyntax.PREFIX_ITEM_PRICE);

        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            comparators.add(new ItemNameComparator());
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ITEM_QUANTITY).isPresent()) {
            comparators.add(new ItemQuantityComparator());
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ITEM_BOUGHT_DATE).isPresent()) {
            comparators.add(new ItemBoughtDateComparator());
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ITEM_EXPIRY_DATE).isPresent()) {
            comparators.add(new ItemExpiryDateComparator());
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ITEM_UNIT).isPresent()) {
            comparators.add(new ItemUnitComparator());
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ITEM_PRICE).isPresent()) {
            comparators.add(new ItemPriceComparator());
        }
        if (comparators.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.getUsage()));
        }

        return new SortCommand(new ChainComparator<>(comparators));
    }
}
