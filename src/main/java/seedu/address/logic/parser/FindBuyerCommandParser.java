package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindBuyerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.buyer.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindBuyerCommand object
 */
public class FindBuyerCommandParser extends Parser<FindBuyerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindBuyerCommand
     * and returns a FindBuyerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindBuyerCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBuyerCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindBuyerCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
