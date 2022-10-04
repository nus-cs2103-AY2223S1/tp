package tracko.logic.parser.order;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import tracko.logic.commands.order.FindOrderCommand;
import tracko.logic.parser.Parser;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.order.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindOrderCommandParser implements Parser<FindOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindOrderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindOrderCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
