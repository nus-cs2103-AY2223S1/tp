package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_RANDOM;
import static eatwhere.foodguide.logic.parser.ParserUtil.isDisplayHelp;

import java.util.Arrays;
import java.util.function.Predicate;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.logic.commands.FindLocationCommand;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.LocationContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindLocationCommand object
 */
public class FindLocationCommandParser implements Parser<FindLocationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws DisplayCommandHelpException if the user input is for displaying command help
     */
    public FindLocationCommand parse(String args) throws ParseException, DisplayCommandHelpException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindLocationCommand.MESSAGE_USAGE));
        }
        if (isDisplayHelp(args)) {
            throw new DisplayCommandHelpException(FindLocationCommand.MESSAGE_USAGE);
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RANDOM);

        Predicate<Eatery> predicate;
        if (argMultimap.getPreamble().isEmpty()) {
            predicate = eatery -> true;
        } else {
            String[] nameKeywords = argMultimap.getPreamble().split("\\s+");
            predicate = new LocationContainsKeywordsPredicate(Arrays.asList(nameKeywords));
        }

        if (argMultimap.getValue(PREFIX_RANDOM).isEmpty()) {
            return new FindLocationCommand(predicate);
        }

        try {
            int numRandPicks = Integer.parseInt(argMultimap.getValue(PREFIX_RANDOM).get());
            if (numRandPicks <= 0) {
                throw new NumberFormatException();
            }
            return new FindLocationCommand(predicate, numRandPicks);
        } catch (NumberFormatException nfe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindLocationCommand.MESSAGE_USAGE),
                    nfe);
        }
    }

}
