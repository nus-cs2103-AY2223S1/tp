package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.getcommands.GetWardNumberCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.WardNumberPredicate;

/**
 * Parses input arguments and creates a new GetWardNumberCommand object
 */
public class GetWardNumberCommandParser implements Parser<GetWardNumberCommand> {

    @Override
    public GetWardNumberCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetWardNumberCommand.MESSAGE_USAGE));
        }

        String[] wardNumbers = trimmedArgs.split("\\s+");

        return new GetWardNumberCommand(new WardNumberPredicate(Arrays.asList(wardNumbers)));
    }

}
