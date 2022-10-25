package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;

import seedu.address.logic.commands.FilterPropsCommand;
import seedu.address.logic.commands.MultiFlagFilterBuyersCommand;
import seedu.address.logic.commands.MultiFlagFilterPropsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.buyer.Name;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.pricerange.PriceRange;
import seedu.address.model.property.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


/**
 * Parses user input to create a {@code FilterPropsCommand}.
 */
public class MultiFlagFilterPropsCommandParser extends Parser<MultiFlagFilterPropsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MultiFlagFilterPropsCommand
     * and returns a MultiFlagFilterPropsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MultiFlagFilterPropsCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PRICE_RANGE,
                PREFIX_CHARACTERISTICS, PREFIX_OWNER_NAME);

        if (!isAnyPrefixPresent(argMultimap, PREFIX_PRICE_RANGE, PREFIX_CHARACTERISTICS, PREFIX_OWNER_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPropsCommand.MESSAGE_USAGE));
        }

        List<Predicate<Property>> predicatesList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_PRICE_RANGE).isPresent()) {
            PriceRange priceRange = ParserUtil.parsePriceRange(argMultimap.getValue(PREFIX_PRICE_RANGE).get());
            predicatesList.add(new FilterPropsByPricePredicate(priceRange));
        }

        if (argMultimap.getValue(PREFIX_CHARACTERISTICS).isPresent()) {
            Characteristics characteristics = ParserUtil.parseCharacteristics(
                    argMultimap.getValue(PREFIX_CHARACTERISTICS).get());
            predicatesList.add(new FilterPropsByCharacteristicsPredicate(characteristics));
        }

        if (argMultimap.getValue(PREFIX_OWNER_NAME).isPresent()) {
            Name ownerName = ParserUtil.parseName(argMultimap.getValue(PREFIX_OWNER_NAME).get());
            predicatesList.add(new FilterPropsByOwnerNamePredicate(ownerName));
        }

        // Get logical OR of all predicates
        for (int i = 1; i < predicatesList.size(); i++) {
            predicatesList.set(0,predicatesList.get(0).or(predicatesList.get(i)));
        }

        return new MultiFlagFilterPropsCommand(predicatesList.get(0));
    }
}
