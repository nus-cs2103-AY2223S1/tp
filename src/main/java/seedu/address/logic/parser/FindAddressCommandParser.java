package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindAddressCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindAddressCommand object
 */
public class FindAddressCommandParser implements Parser<FindAddressCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAddressCommand
     * and returns a FindAddressCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAddressCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAddressCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindAddressCommand(new AddressContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
