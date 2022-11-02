package seedu.address.logic.parser.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FUZZY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.property.FilterPropertiesCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.buyer.Name;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.pricerange.PriceRange;
import seedu.address.model.property.FilterPropsByOwnerNamePredicate;
import seedu.address.model.property.FilterPropsByPricePredicate;
import seedu.address.model.property.FilterPropsContainingAllCharacteristicsPredicate;
import seedu.address.model.property.FilterPropsContainingAnyCharacteristicPredicate;
import seedu.address.model.property.Property;

/**
 * Parses user input to create a {@code FilterPropertiesCommand}.
 */
public class FilterPropertiesCommandParser extends Parser<FilterPropertiesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterPropertiesCommand
     * and returns a FilterPropertiesCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterPropertiesCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PRICE_RANGE,
                PREFIX_CHARACTERISTICS, PREFIX_OWNER_NAME, PREFIX_FUZZY);

        if (!isAnyPrefixPresent(argMultimap, PREFIX_PRICE_RANGE, PREFIX_CHARACTERISTICS, PREFIX_OWNER_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPropertiesCommand.MESSAGE_USAGE));
        }

        List<Predicate<Property>> predicatesList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_PRICE_RANGE).isPresent()) {
            PriceRange priceRange = ParserUtil.parsePriceRange(argMultimap.getValue(PREFIX_PRICE_RANGE).get());
            predicatesList.add(new FilterPropsByPricePredicate(priceRange));
        }

        if (argMultimap.getValue(PREFIX_CHARACTERISTICS).isPresent()) {
            Characteristics characteristics = ParserUtil.parseCharacteristics(
                    argMultimap.getValue(PREFIX_CHARACTERISTICS).get());
            if (argMultimap.getValue(PREFIX_FUZZY).isPresent()) {
                predicatesList.add(new FilterPropsContainingAnyCharacteristicPredicate(characteristics));
            } else {
                predicatesList.add(new FilterPropsContainingAllCharacteristicsPredicate(characteristics));
            }
        }

        if (argMultimap.getValue(PREFIX_OWNER_NAME).isPresent()) {
            Name ownerName = ParserUtil.parseName(argMultimap.getValue(PREFIX_OWNER_NAME).get());
            predicatesList.add(new FilterPropsByOwnerNamePredicate(ownerName));
        }

        // predicatesList must not be empty, since at least 1 prefix should be present
        assert(!predicatesList.isEmpty());

        Predicate<Property> combinedPredicate;
        if (arePrefixesPresent(argMultimap, PREFIX_FUZZY)) {
            combinedPredicate = predicatesList.stream().reduce(Predicate::or).get();
        } else {
            combinedPredicate = predicatesList.stream().reduce(Predicate::and).get();
        }
        return new FilterPropertiesCommand(combinedPredicate);
    }
}
