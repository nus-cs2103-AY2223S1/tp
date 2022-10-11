package seedu.foodrem.logic.parser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_SORT_BY_BOUGHT_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_SORT_BY_EXPIRY_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_SORT_BY_NAME;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_SORT_BY_QTY;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_SORT_BY_UNIT;

import java.util.ArrayList;
import java.util.List;

import seedu.foodrem.logic.commands.SortCommand;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.itemcomparators.ItemBoughtDateComparator;
import seedu.foodrem.model.item.itemcomparators.ItemComparator;
import seedu.foodrem.model.item.itemcomparators.ItemExpiryDateComparator;
import seedu.foodrem.model.item.itemcomparators.ItemNameComparator;
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
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        List<ItemComparator> comparators = new ArrayList<>();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_SORT_BY_NAME,
                PREFIX_SORT_BY_QTY,
                PREFIX_SORT_BY_BOUGHT_DATE,
                PREFIX_SORT_BY_EXPIRY_DATE,
                PREFIX_SORT_BY_UNIT);

        if (argMultimap.getValue(PREFIX_SORT_BY_NAME).isPresent()) {
            comparators.add(new ItemNameComparator());
        }
        if (argMultimap.getValue(PREFIX_SORT_BY_QTY).isPresent()) {
            comparators.add(new ItemQuantityComparator());
        }
        if (argMultimap.getValue(PREFIX_SORT_BY_BOUGHT_DATE).isPresent()) {
            comparators.add(new ItemBoughtDateComparator());
        }
        if (argMultimap.getValue(PREFIX_SORT_BY_EXPIRY_DATE).isPresent()) {
            comparators.add(new ItemExpiryDateComparator());
        }
        if (argMultimap.getValue(PREFIX_SORT_BY_UNIT).isPresent()) {
            comparators.add(new ItemUnitComparator());
        }
        if (comparators.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return new SortCommand(new ChainComparator<Item>(comparators));
    }
}
