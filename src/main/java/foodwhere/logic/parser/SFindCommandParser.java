package foodwhere.logic.parser;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import foodwhere.logic.commands.SFindCommand;
import foodwhere.logic.parser.exceptions.ParseException;
import foodwhere.model.stall.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new SFindCommand object
 */
public class SFindCommandParser implements Parser<SFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SFindCommand
     * and returns a SFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new SFindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
