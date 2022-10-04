package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.GetHospitalWingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.HospitalWingContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class GetHospitalWingCommandParser implements Parser<GetHospitalWingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetCommand
     * and returns a GetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetHospitalWingCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetHospitalWingCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new GetHospitalWingCommand(new HospitalWingContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}


