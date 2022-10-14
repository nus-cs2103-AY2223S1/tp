package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.getcommands.GetHospitalWingCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.HospitalWingContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new GetHospitalWingCommand object
 */
public class GetHospitalWingCommandParser implements Parser<GetHospitalWingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetHospitalWingCommand
     * and returns a GetHospitalWingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetHospitalWingCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetHospitalWingCommand.MESSAGE_USAGE));
        }

        String[] hospitalWings = trimmedArgs.split("\\s+");

        return new GetHospitalWingCommand(new HospitalWingContainsKeywordsPredicate(Arrays.asList(hospitalWings)));
    }

}
