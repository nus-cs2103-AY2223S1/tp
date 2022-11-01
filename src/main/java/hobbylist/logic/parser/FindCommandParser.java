package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static hobbylist.commons.core.Messages.MESSAGE_INVALID_RATING;

import java.util.Arrays;

import hobbylist.logic.commands.FindCommand;
import hobbylist.logic.commands.RateCommand;
import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.activity.DateMatchesGivenDatePredicate;
import hobbylist.model.activity.NameOrDescContainsKeywordsPredicate;
import hobbylist.model.activity.RatingMatchesGivenValuePredicate;
import hobbylist.model.activity.StatusMatchesGivenStatus;
import hobbylist.model.date.Date;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private final static String PREFIX_DATE = "date/";
    private final static String PREFIX_RATE = "rate/";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_FIND_RATE, CliSyntax.PREFIX_DATE);

        // keywords for activities' names and descriptions
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String[] keywords = trimmedArgs.split("\\s+");

        // time for activities' dates
        if (!argMultimap.getValue(CliSyntax.PREFIX_DATE).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String time = argMultimap.getValue(CliSyntax.PREFIX_DATE).get();
        if (!Date.isValidDateString(time)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // value for activities' ratings
//        int value;
//        if (argMultimap.getValue(CliSyntax.PREFIX_RATING).isPresent()) {
//            try {
//                value = Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_RATING).get());
//            } catch (NumberFormatException nfe) {
//                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
//            }
//            if (value < 1 || value > 5) {
//                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
//            }
//        } else {
//            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
//        }

        return new FindCommand(new NameOrDescContainsKeywordsPredicate(Arrays.asList(keywords)),
                new DateMatchesGivenDatePredicate(time),
                new RatingMatchesGivenValuePredicate(Arrays.asList(keywords)));
    }

}
