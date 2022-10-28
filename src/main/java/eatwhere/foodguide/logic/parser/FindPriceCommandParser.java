package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_HELP;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_RANDOM;
import static eatwhere.foodguide.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Arrays;
import java.util.function.Predicate;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.logic.commands.FindPriceCommand;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.PriceContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FindPriceCommand object
 */
public class FindPriceCommandParser implements Parser<FindPriceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPriceCommand
     * and returns a FindPriceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws DisplayCommandHelpException if the user input is for displaying command help
     */
    public FindPriceCommand parse(String args) throws ParseException, DisplayCommandHelpException {

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindPriceCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_HELP, PREFIX_RANDOM);

        if (arePrefixesPresent(argMultimap, PREFIX_HELP)) {
            throw new DisplayCommandHelpException(FindPriceCommand.MESSAGE_USAGE);
        }

        Predicate<Eatery> predicate;
        if (argMultimap.getPreamble().isEmpty()) {
            predicate = eatery -> true;
        } else {
            String[] nameKeywords = trimmedArgs.split("\\s+");
            predicate = new PriceContainsKeywordsPredicate(Arrays.asList(nameKeywords));
        }

        if (argMultimap.getValue(PREFIX_RANDOM).isEmpty()) {
            return new FindPriceCommand(predicate);
        }

        try {
            int numRandPicks = Integer.parseInt(argMultimap.getValue(PREFIX_RANDOM).get());
            if (numRandPicks <= 0) {
                throw new NumberFormatException();
            }
            return new FindPriceCommand(predicate, numRandPicks);
        } catch (NumberFormatException nfe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindPriceCommand.MESSAGE_USAGE),
                    nfe);
        }

    }
}
