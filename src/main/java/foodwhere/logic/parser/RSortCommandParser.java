package foodwhere.logic.parser;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import foodwhere.logic.commands.RSortCommand;
import foodwhere.logic.parser.exceptions.ParseException;
import foodwhere.model.review.comparator.ReviewsComparatorList;

/**
 * Parses input arguments and creates a new RSortCommand object
 */
public class RSortCommandParser implements Parser<RSortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RSortCommand
     * and returns a RSortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RSortCommand parse(String args) throws ParseException {
        try {
            ReviewsComparatorList reviewsComparator = ParserUtil.parseReviewCriteria(args);
            return new RSortCommand(reviewsComparator);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RSortCommand.MESSAGE_USAGE), pe);
        }
    }
}
