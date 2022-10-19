package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.intrack.logic.commands.FindNCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.internship.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindNCommand object
 */
public class FindNCommandParser implements Parser<FindNCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindNCommand
     * and returns a FindNCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindNCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindNCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindNCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
