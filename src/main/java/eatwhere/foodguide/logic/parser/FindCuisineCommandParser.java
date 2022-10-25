package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_HELP;
import static eatwhere.foodguide.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Arrays;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.logic.commands.FindCuisineCommand;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;
import eatwhere.foodguide.model.eatery.CuisineContainsKeywordsPredicate;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_HELP);

        if (arePrefixesPresent(argMultimap, PREFIX_HELP)) {
            throw new DisplayCommandHelpException(FindCuisineCommand.MESSAGE_USAGE);
        }

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCuisineCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCuisineCommand(new CuisineContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
