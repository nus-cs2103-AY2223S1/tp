package seedu.travelr.logic.parser;

import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.DisplayTripCommand;
import seedu.travelr.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DisplayTripCommand object.
 */
public class DisplayTripCommandParser implements Parser<DisplayTripCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DisplayTripCommand
     * and returns a DisplayTripCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DisplayTripCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DisplayTripCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DisplayTripCommand.MESSAGE_USAGE), pe);
        }
    }
}
