package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindPersonsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPersonsCommand object.
 */
public class FindPersonsCommandParser implements Parser<FindPersonsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPersonsCommand
     * and returns a FindPersonsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPersonsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonsCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindPersonsCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
