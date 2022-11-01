package seedu.nutrigoals.logic.parser;

import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.nutrigoals.logic.commands.LocateGymCommand;
import seedu.nutrigoals.logic.parser.exceptions.ParseException;
import seedu.nutrigoals.model.Location;

/**
 * Parses input arguments and creates a new LocateGymCommand object
 */
public class LocateGymCommandParser implements Parser<LocateGymCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LocateGymCommand
     * and returns an LocateGymCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public LocateGymCommand parse(String args) throws ParseException {
        try {
            Location location = ParserUtil.parseLocation(args);
            return new LocateGymCommand(location);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, LocateGymCommand.MESSAGE_USAGE), pe);
        }
    }

}
