package tracko.logic.parser.item;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import tracko.logic.commands.item.FindItemCommand;
import tracko.logic.parser.Parser;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.item.ItemContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a FindItemCommand object.
 */
public class FindItemCommandParser implements Parser<FindItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindItemCommand
     * and returns an FindItemCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindItemCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindItemCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindItemCommand(new ItemContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
