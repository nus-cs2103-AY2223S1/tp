package seedu.address.logic.parser.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDateTime;
import java.util.Comparator;

import seedu.address.logic.commands.property.SortPropertiesCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.sortcomparators.Order;
import seedu.address.logic.sortcomparators.PriceComparator;
import seedu.address.logic.sortcomparators.PropertyComparator;
import seedu.address.logic.sortcomparators.PropertyNameComparator;
import seedu.address.logic.sortcomparators.TimeComparator;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;

/**
 * Parses user input to create a {@code SortBuyersCommand}.
 */
public class SortPropertiesCommandParser extends Parser<SortPropertiesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortBuyersCommand
     * and returns a SortBuyersCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortPropertiesCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE, PREFIX_TIME);

        if (areMoreThanOnePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PRICE, PREFIX_TIME)
                || !isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_PRICE, PREFIX_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortPropertiesCommand.MESSAGE_USAGE));
        }

        Comparator<Property> propertyComparator = null;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_NAME).get());
            Comparator<PropertyName> propertyNameComparator = new PropertyNameComparator(order);
            propertyComparator = new PropertyComparator(propertyNameComparator, null, null);
        }

        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_PRICE).get());
            Comparator<Price> priceComparator = new PriceComparator(order);
            propertyComparator = new PropertyComparator(null, priceComparator, null);
        }

        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_TIME).get());
            Comparator<LocalDateTime> timeComparator = new TimeComparator(order);
            propertyComparator = new PropertyComparator(null, null, timeComparator);
        }

        return new SortPropertiesCommand(propertyComparator);
    }
}

