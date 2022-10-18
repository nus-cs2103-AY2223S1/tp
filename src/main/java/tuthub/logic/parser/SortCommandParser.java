package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.logic.parser.CliSyntax.PREFIX_RATING;
import static tuthub.logic.parser.CliSyntax.PREFIX_TEACHINGNOMINATION;

import tuthub.commons.core.index.Index;
import tuthub.logic.commands.SortCommand;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.logic.parser.exceptions.ParseException;
import tuthub.model.tutor.SortByRatingComparator;
import tuthub.model.tutor.SortByTeachingNominationComparator;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    public static final String SPACE = " ";
    public static final Integer MAX_ARGS = 3;
    public static final String MESSAGE_UNKNOWN_PREFIX = "Prefix %1$s is not valid for this command. "
            + "Valid prefixes are tn/ or r/.";
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String[] strArr = args.split(SPACE);

        if (strArr.length > MAX_ARGS) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String order = ParserUtil.parseOrder(strArr[1]);
        Prefix prefix = ParserUtil.parseSortPrefix(new Prefix(strArr[2]));

        if (isRating(prefix)) {
            return new SortCommand(order, prefix, new SortByRatingComparator(order));
        } else if (isTeachingNomination(prefix)) {
            return new SortCommand(order, prefix, new SortByTeachingNominationComparator(order));
        } else {
            throw new ParseException(String.format(MESSAGE_UNKNOWN_PREFIX, prefix));
        }
    }

    public Boolean isRating(Prefix prefix) {
        return prefix.equals(PREFIX_RATING);
    }

    public Boolean isTeachingNomination(Prefix prefix) {
        return prefix.equals(PREFIX_TEACHINGNOMINATION);
    }
}
