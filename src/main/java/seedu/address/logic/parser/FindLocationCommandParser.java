package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindLocationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.LocationContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindLocationCommandParser implements Parser<FindLocationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindLocationCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindLocationCommand.MESSAGE_USAGE));
        }

        String[] locationKeywords = trimmedArgs.split("\\s+");

        return new FindLocationCommand(new LocationContainsKeywordsPredicate(Arrays.asList(locationKeywords)));
    }


}
