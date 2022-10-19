package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.intrack.logic.commands.FindPCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.internship.PositionContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindNCommand object
 */
public class FindPCommandParser implements Parser<FindPCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPCommand
     * and returns a FindPCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPCommand.MESSAGE_USAGE));
        }

        String[] posKeywords = trimmedArgs.split("\\s+");

        return new FindPCommand(new PositionContainsKeywordsPredicate(Arrays.asList(posKeywords)));
    }

}
