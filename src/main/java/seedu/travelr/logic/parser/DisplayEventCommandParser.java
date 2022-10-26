package seedu.travelr.logic.parser;

import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.DisplayEventCommand;
import seedu.travelr.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DisplayEventCommand object
 */
public class DisplayEventCommandParser implements Parser<DisplayEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DisplayEventCommand
     * and returns a DisplayEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DisplayEventCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DisplayEventCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DisplayEventCommand.MESSAGE_USAGE), pe);
        }
    }
}
