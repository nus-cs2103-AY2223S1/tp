package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.FilterBuyersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.person.AbstractFilterBuyerPredicate;
import seedu.address.model.person.FilterBuyerByCharacteristicsPredicate;
import seedu.address.model.person.FilterBuyerByPricePredicate;
import seedu.address.model.person.FilterBuyerByPriorityPredicate;
import seedu.address.model.person.Priority;
import seedu.address.model.property.Price;


/**
 * Parses user input to create a {@code FilterBuyersCommand}.
 */
public class FilterBuyersCommandParser extends Parser<FilterBuyersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterBuyersCommand
     * and returns an FilterBuyersCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterBuyersCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CHARACTERISTICS, PREFIX_PRICE,
                PREFIX_PRIORITY);

        if (areMoreThanOnePrefixesPresent(argMultimap, PREFIX_PRICE, PREFIX_CHARACTERISTICS, PREFIX_PRIORITY)
                || !isAnyPrefixPresent(argMultimap, PREFIX_PRICE, PREFIX_CHARACTERISTICS, PREFIX_PRIORITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterBuyersCommand.MESSAGE_USAGE));
        }

        AbstractFilterBuyerPredicate predicate = null;

        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            Price price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());
            predicate = new FilterBuyerByPricePredicate(price);
        }

        if (argMultimap.getValue(PREFIX_CHARACTERISTICS).isPresent()) {
            Characteristics characteristics = ParserUtil.parseCharacteristics(
                    argMultimap.getValue(PREFIX_CHARACTERISTICS).get());
            predicate = new FilterBuyerByCharacteristicsPredicate(characteristics);
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            Priority priority = ParserUtil.parsePriority(
                    argMultimap.getValue(PREFIX_PRIORITY).get());
            predicate = new FilterBuyerByPriorityPredicate(priority);
        }
        // TODO: Consider allowing filtering by multiple characteristics and tags at once

        return new FilterBuyersCommand(predicate);
    }
}
