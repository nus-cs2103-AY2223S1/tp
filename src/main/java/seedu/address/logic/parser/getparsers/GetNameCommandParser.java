package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.getcommands.GetNameCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new GetNameCommand object
 */
public class GetNameCommandParser implements Parser<GetNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetNameCommand
     * and returns a GetNameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetNameCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetNameCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new GetNameCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
