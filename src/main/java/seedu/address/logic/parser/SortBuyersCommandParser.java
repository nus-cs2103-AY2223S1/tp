package seedu.address.logic.parser;

import seedu.address.logic.SortComparators.BuyerComparator;
import seedu.address.logic.SortComparators.NameComparator;
import seedu.address.logic.SortComparators.Order;
import seedu.address.logic.commands.SortBuyersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.Name;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;

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

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE_RANGE);

        if (areMoreThanOnePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PRICE_RANGE)
                || !isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_PRICE_RANGE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortBuyersCommand.MESSAGE_USAGE));
        }

        Comparator<Buyer> buyerComparator = null;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_NAME).get());
            Comparator<Name> nameComparator = new NameComparator(order);
            buyerComparator = new BuyerComparator(nameComparator, null);
        }
        
        return new SortBuyersCommand(buyerComparator);
    }
}

