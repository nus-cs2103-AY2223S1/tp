package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

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

        List<Integer> wardNumbers = new ArrayList<>();
        for (String arg : trimmedArgs.split("\\s+")) {
            try {
                int ward = Integer.parseInt(arg);
                if (ward < 1) { // Throw exception if input is invalid ward number.
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetWardNumberCommand.MESSAGE_USAGE));
                }
                wardNumbers.add(ward);
            } catch (NumberFormatException e) { // Catches both String input and negative number errors.
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetWardNumberCommand.MESSAGE_USAGE));
            }
        }

        return new GetWardNumberCommand(new WardNumberPredicate(wardNumbers));
    }

}
