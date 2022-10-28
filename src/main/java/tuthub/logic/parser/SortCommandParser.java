package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.logic.parser.CliSyntax.PREFIX_RATING;
import static tuthub.logic.parser.CliSyntax.PREFIX_TEACHINGNOMINATION;

import tuthub.logic.commands.SortCommand;
import tuthub.logic.parser.exceptions.ParseException;
import tuthub.model.tutor.SortByRatingComparator;
import tuthub.model.tutor.SortByTeachingNominationComparator;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    public static final Integer EXP_ARGS = 2;
    public static final String SLASH = "/";
    public static final String MESSAGE_UNKNOWN_PREFIX = "Prefix %1$s is not valid for this command. "
            + "Valid prefixes are tn/ or r/.";
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        String[] strArr = trimmedArgs.split("\\s+");

        if (!(strArr.length == EXP_ARGS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String firstArg = strArr[0];
        String secondArg = strArr[1];

        String order = ParserUtil.parseOrder(findOrder(firstArg, secondArg));
        Prefix prefix = ParserUtil.parseSortPrefix(new Prefix(findPrefix(firstArg, secondArg)));

        if (isRating(prefix)) {
            return new SortCommand(order, prefix, new SortByRatingComparator(order));
        } else if (isTeachingNomination(prefix)) {
            return new SortCommand(order, prefix, new SortByTeachingNominationComparator(order));
        } else {
            throw new ParseException(String.format(MESSAGE_UNKNOWN_PREFIX, prefix));
        }
    }

    public boolean isPrefix(String s) {
        String first = s.substring(s.length() - 1);
        return first.equals(SLASH);
    }

    public String findOrder(String s1, String s2) {
        if (isPrefix(s1)) {
            return s2;
        }
        return s1;
    }

    public String findPrefix(String s1, String s2) {
        if (isPrefix(s1)) {
            return s1;
        }
        return s2;
    }

    public Boolean isRating(Prefix prefix) {
        return prefix.equals(PREFIX_RATING);
    }

    public Boolean isTeachingNomination(Prefix prefix) {
        return prefix.equals(PREFIX_TEACHINGNOMINATION);
    }
}
