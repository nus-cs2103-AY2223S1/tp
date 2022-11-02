package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindBuyersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.NameContainsSubstringPredicate;

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

        return new FindBuyersCommand(new NameContainsSubstringPredicate(trimmedArgs));
    }
}
