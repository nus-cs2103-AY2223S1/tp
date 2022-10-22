package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindStuCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.StuNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindStuCommand object
 */
public class FindStuCommandParser implements Parser<FindStuCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindStuCommand
     * and returns a FindStuCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStuCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStuCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindStuCommand(new StuNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
