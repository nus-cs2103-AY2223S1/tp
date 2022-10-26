package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_RANDOM;

import java.util.Arrays;
import java.util.function.Predicate;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.logic.commands.FindCommand;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RANDOM);
        Predicate<Eatery> predicate;
        if (argMultimap.getValue(PREFIX_RANDOM).isPresent()) {
            predicate = eatery -> true;
            return new FindCommand(predicate, Integer.parseInt(argMultimap.getValue(PREFIX_RANDOM).get()));
        } else {
            String[] nameKeywords = trimmedArgs.split("\\s+");
            predicate = new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
        }

        return new FindCommand(predicate);
    }

}
