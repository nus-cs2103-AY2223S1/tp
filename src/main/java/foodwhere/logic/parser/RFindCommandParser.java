package foodwhere.logic.parser;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import foodwhere.logic.commands.RFindCommand;
import foodwhere.logic.parser.exceptions.ParseException;
import foodwhere.model.review.NameContainsStallPredicate;



/**
 * Parses input arguments and creates a new RFindCommand object
 */
public class RFindCommandParser implements Parser<RFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RFindCommand
     * and returns a RFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new RFindCommand(new NameContainsStallPredicate(Arrays.asList(nameKeywords)));
    }

}
