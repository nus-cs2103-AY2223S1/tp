package seedu.address.logic.parser.buyer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDateTime;
import java.util.Comparator;

import seedu.address.logic.commands.buyer.SortBuyersCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.sortcomparators.BuyerComparator;
import seedu.address.logic.sortcomparators.NameComparator;
import seedu.address.logic.sortcomparators.Order;
import seedu.address.logic.sortcomparators.PriceRangeComparator;
import seedu.address.logic.sortcomparators.PriorityComparator;
import seedu.address.logic.sortcomparators.TimeComparator;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Priority;
import seedu.address.model.pricerange.PriceRange;

/**
 * Parses user input to create a {@code SortBuyersCommand}.
 */
public class SortBuyersCommandParser extends Parser<SortBuyersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortBuyersCommand
     * and returns a SortBuyersCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortBuyersCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_PRICE_RANGE, PREFIX_PRIORITY, PREFIX_TIME);

        if (areMoreThanOnePrefixesPresent(argMultimap,
                PREFIX_NAME, PREFIX_PRICE_RANGE, PREFIX_PRIORITY, PREFIX_TIME)
                || !isAnyPrefixPresent(argMultimap,
                PREFIX_NAME, PREFIX_PRICE_RANGE, PREFIX_PRIORITY, PREFIX_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortBuyersCommand.MESSAGE_USAGE));
        }

        Comparator<Buyer> buyerComparator = null;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_NAME).get());
            Comparator<Name> nameComparator = new NameComparator(order);
            buyerComparator = new BuyerComparator(nameComparator, null, null, null);
        }

        if (argMultimap.getValue(PREFIX_PRICE_RANGE).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_PRICE_RANGE).get());
            Comparator<PriceRange> priceRangeComparator = new PriceRangeComparator(order);
            buyerComparator = new BuyerComparator(null, priceRangeComparator, null, null);
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_PRIORITY).get());
            Comparator<Priority> priorityComparator = new PriorityComparator(order);
            buyerComparator = new BuyerComparator(null, null, priorityComparator, null);
        }

        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_TIME).get());
            Comparator<LocalDateTime> timeComparator = new TimeComparator(order);
            buyerComparator = new BuyerComparator(null, null, null, timeComparator);
        }


        return new SortBuyersCommand(buyerComparator);
    }
}

