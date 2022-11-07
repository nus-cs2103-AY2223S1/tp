package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_RANDOM;
import static eatwhere.foodguide.logic.parser.ParserUtil.isDisplayHelp;

import java.util.Arrays;
import java.util.function.Predicate;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.logic.commands.FindCuisineCommand;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;
import eatwhere.foodguide.model.eatery.CuisineContainsKeywordsPredicate;
import eatwhere.foodguide.model.eatery.Eatery;


/**
 * Parses input arguments and creates a new FindLocationCommand object
 */
public class FindCuisineCommandParser implements Parser<FindCuisineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws DisplayCommandHelpException if the user input is for displaying command help
     */
    public FindCuisineCommand parse(String args) throws ParseException, DisplayCommandHelpException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCuisineCommand.MESSAGE_USAGE));
        }
        if (isDisplayHelp(args)) {
            throw new DisplayCommandHelpException(FindCuisineCommand.MESSAGE_USAGE);
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RANDOM);

        Predicate<Eatery> predicate;
        if (argMultimap.getPreamble().isEmpty()) {
            predicate = eatery -> true;
        } else {
            String[] nameKeywords = argMultimap.getPreamble().split("\\s+");
            predicate = new CuisineContainsKeywordsPredicate(Arrays.asList(nameKeywords));
        }

        if (argMultimap.getValue(PREFIX_RANDOM).isEmpty()) {
            return new FindCuisineCommand(predicate);
        }

        try {
            int numRandPicks = Integer.parseInt(argMultimap.getValue(PREFIX_RANDOM).get());
            if (numRandPicks <= 0) {
                throw new NumberFormatException();
            }
            return new FindCuisineCommand(predicate, numRandPicks);
        } catch (NumberFormatException nfe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCuisineCommand.MESSAGE_USAGE),
                    nfe);
        }

    }

}
