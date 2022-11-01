package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.Optional;

import hobbylist.logic.commands.FindCommand;
import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.activity.DateMatchesGivenDatePredicate;
import hobbylist.model.activity.NameOrDescContainsKeywordsPredicate;
import hobbylist.model.activity.RatingMatchesGivenValuePredicate;
import hobbylist.model.date.Date;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final String DEFAULT_DATE = "";
    private static final int DEFAULT_RATING = -1;

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        String time = DEFAULT_DATE;
        int value = DEFAULT_RATING;

        if (isValidKeywords(keywords)) {
            time = getDate(keywords);
            value = getRating(keywords);
        }

        return new FindCommand(new NameOrDescContainsKeywordsPredicate(Arrays.asList(keywords)),
                new DateMatchesGivenDatePredicate(time),
                new RatingMatchesGivenValuePredicate(value));
    }

    private static boolean isValidKeywords(String[] keywords) throws ParseException {
        if (hasDateKeyword(keywords)) {
            if (Arrays.stream(keywords).filter(FindCommandParser::isDateKeyword).count() > 1) {
                throw new ParseException(FindCommand.MESSAGE_INVALID_DATE_VALUE);
            }
            if (Arrays.stream(keywords)
                    .filter(FindCommandParser::isDateKeyword)
                    .noneMatch(FindCommandParser::isDateValid)) {
                throw new ParseException(FindCommand.MESSAGE_INVALID_DATE_VALUE);
            }
        }
        if (hasRatingKeyword(keywords)) {
            if (Arrays.stream(keywords).filter(FindCommandParser::isRatingKeyword).count() > 1) {
                throw new ParseException(FindCommand.MESSAGE_INVALID_RATING_VALUE);
            }
            if (Arrays.stream(keywords)
                    .filter(FindCommandParser::isRatingKeyword)
                    .noneMatch(FindCommandParser::isRatingValid)) {
                throw new ParseException(FindCommand.MESSAGE_INVALID_RATING_VALUE);
            }
        }
        return true;
    }

    private static boolean hasDateKeyword(String[] keywords) {
        return Arrays.stream(keywords).anyMatch(FindCommandParser::isDateKeyword);
    }

    private static boolean isDateKeyword(String keyword) {
        return keyword.contains(FindCommand.DATE_KEYWORD_PREFIX);
    }

    private static boolean isDateValid(String dateKeyword) {
        switch(dateKeyword.length()) {
        case 9: // date/yyyy
            if (Date.isValidYearString(dateKeyword.substring(5, 9))) {
                return true;
            }
            break;
        case 12: // date/yyyy-mm
            if (Date.isValidMonthString(dateKeyword.substring(5, 12))) {
                return true;
            }
            break;
        case 15: // date/yyyy-mm-dd
            if (Date.isValidDateString(dateKeyword.substring(5, 15))) {
                return true;
            }
            break;
        default:
            break;
        }
        return false;
    }

    private static String getDate(String[] keywords) {
        Optional<String> date = Arrays.stream(keywords)
                                    .filter(FindCommandParser::isDateKeyword)
                                    .filter(FindCommandParser::isDateValid)
                                    .findFirst();
        if (date.isEmpty()) {
            return DEFAULT_DATE;
        }
        String time = date.get();
        switch(time.length()) {
        case 9: // year
            return time.substring(5, 9);
        case 12: // month
            return time.substring(5, 12);
        case 15: // date/yyyy-mm-d
            return time.substring(5, 15);
        default:
            break;
        }
        return DEFAULT_DATE;
    }

    private static boolean hasRatingKeyword(String[] keywords) {
        return Arrays.stream(keywords).anyMatch(FindCommandParser::isRatingKeyword);
    }

    private static boolean isRatingKeyword(String keyword) {
        return keyword.contains(FindCommand.RATING_KEYWORD_PREFIX);
    }

    private static boolean isRatingValid(String rateKeyword) {
        // rate/VALUE
        if (rateKeyword.length() != 6) {
            return false;
        }
        try {
            String rating = rateKeyword.substring(5);
            int value = Integer.parseInt(rating);
            if (!isRatingInBound(value)) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean isRatingInBound(int rating) {
        return 1 <= rating && rating <= 5;
    }

    private static int getRating(String[] keywords) {
        Optional<String> ratingString = Arrays.stream(keywords)
                                            .filter(FindCommandParser::isRatingKeyword)
                                            .filter(FindCommandParser::isRatingValid)
                                            .findFirst();
        if (ratingString.isEmpty()) {
            return DEFAULT_RATING;
        }
        return Integer.parseInt(ratingString.get().substring(5));
    }
}

