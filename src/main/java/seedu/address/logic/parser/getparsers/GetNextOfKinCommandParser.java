package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.getcommands.GetNextOfKinCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NextOfKinPredicate;

/**
 * Parses input arguments and creates a new GetNextOfKinCommand object
 */
public class GetNextOfKinCommandParser implements Parser<GetNextOfKinCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetNextOfKinCommand
     * and returns a GetNextOfKinCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetNextOfKinCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetNextOfKinCommand.MESSAGE_USAGE));
        }

        String[] nextOfKinKeywords = trimmedArgs.split("\\s+");

        return new GetNextOfKinCommand(new NextOfKinPredicate(Arrays.asList(nextOfKinKeywords)));
    }

}
