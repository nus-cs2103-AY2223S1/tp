package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindInternshipCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindInternshipCommand object
 */
public class FindInternshipCommandParser implements Parser<FindInternshipCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindInternshipCommand
     * and returns a FindInternshipCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindInternshipCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInternshipCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindInternshipCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
