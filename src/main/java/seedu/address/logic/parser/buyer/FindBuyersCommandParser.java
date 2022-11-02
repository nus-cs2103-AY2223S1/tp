package seedu.address.logic.parser.buyer;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.buyer.FindBuyersCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.buyer.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindBuyersCommand object
 */
public class FindBuyersCommandParser extends Parser<FindBuyersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindBuyersCommand
     * and returns a FindBuyersCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindBuyersCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBuyersCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindBuyersCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
