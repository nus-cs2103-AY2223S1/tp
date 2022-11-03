package seedu.address.logic.parser.buyer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FUZZY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.buyer.FilterBuyersCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.FilterBuyerByPricePredicate;
import seedu.address.model.buyer.FilterBuyerByPriorityPredicate;
import seedu.address.model.buyer.FilterBuyerContainingAllCharacteristicsPredicate;
import seedu.address.model.buyer.FilterBuyerContainingAnyCharacteristicPredicate;
import seedu.address.model.buyer.Priority;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.property.Price;

/**
 * Parses user input to create a {@code FilterBuyersCommand}.
 */
public class FilterBuyersCommandParser extends Parser<FilterBuyersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterBuyersCommand
     * and returns an FilterBuyersCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterBuyersCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CHARACTERISTICS, PREFIX_PRICE,
                PREFIX_PRIORITY, PREFIX_FUZZY);

        if (!isAnyPrefixPresent(argMultimap, PREFIX_PRICE, PREFIX_CHARACTERISTICS, PREFIX_PRIORITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterBuyersCommand.MESSAGE_USAGE));
        }

        List<Predicate<Buyer>> predicatesList = new ArrayList<>();


        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            Price price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());
            predicatesList.add(new FilterBuyerByPricePredicate(price));
        }

        if (argMultimap.getValue(PREFIX_CHARACTERISTICS).isPresent()) {
            Characteristics characteristics = ParserUtil.parseCharacteristics(
                    argMultimap.getValue(PREFIX_CHARACTERISTICS).get());
            if (argMultimap.getValue(PREFIX_FUZZY).isPresent()) {
                predicatesList.add(new FilterBuyerContainingAnyCharacteristicPredicate(characteristics));
            } else {
                predicatesList.add(new FilterBuyerContainingAllCharacteristicsPredicate(characteristics));
            }
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            Priority priority = ParserUtil.parsePriority(
                    argMultimap.getValue(PREFIX_PRIORITY).get());
            predicatesList.add(new FilterBuyerByPriorityPredicate(priority));
        }

        // predicatesList must not be empty, since at least 1 prefix should be present
        assert(!predicatesList.isEmpty());

        Predicate<Buyer> combinedPredicate;
        if (arePrefixesPresent(argMultimap, PREFIX_FUZZY)) {
            combinedPredicate = predicatesList.stream().reduce(Predicate::or).get();
        } else {
            combinedPredicate = predicatesList.stream().reduce(Predicate::and).get();
        }
        return new FilterBuyersCommand(combinedPredicate);
    }
}
