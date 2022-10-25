package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.FilterBuyersCommand;
import seedu.address.logic.commands.MultiFlagFilterBuyersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.buyer.*;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.property.Price;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


/**
 * Parses user input to create a {@code FilterBuyersCommand}.
 */
public class MultiFlagFilterBuyersCommandParser extends Parser<MultiFlagFilterBuyersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MultiFlagFilterBuyersCommand
     * and returns an MultiFlagFilterBuyersCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MultiFlagFilterBuyersCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CHARACTERISTICS, PREFIX_PRICE,
                PREFIX_PRIORITY);

        if (!isAnyPrefixPresent(argMultimap, PREFIX_PRICE, PREFIX_CHARACTERISTICS, PREFIX_PRIORITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MultiFlagFilterBuyersCommand.MESSAGE_USAGE));
        }

        List<Predicate<Buyer>> predicatesList = new ArrayList<>();


        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            Price price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());
            predicatesList.add(new FilterBuyerByPricePredicate(price));
        }

        if (argMultimap.getValue(PREFIX_CHARACTERISTICS).isPresent()) {
            Characteristics characteristics = ParserUtil.parseCharacteristics(
                    argMultimap.getValue(PREFIX_CHARACTERISTICS).get());
            predicatesList.add(new FilterBuyerByCharacteristicsPredicate(characteristics));
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            Priority priority = ParserUtil.parsePriority(
                    argMultimap.getValue(PREFIX_PRIORITY).get());
            predicatesList.add(new FilterBuyerByPriorityPredicate(priority));
        }

        // Get logical OR of all predicates
        for (int i = 1; i < predicatesList.size(); i++) {
            predicatesList.set(0,predicatesList.get(0).or(predicatesList.get(i)));
        }

        return new MultiFlagFilterBuyersCommand(predicatesList.get(0));
    }
}
