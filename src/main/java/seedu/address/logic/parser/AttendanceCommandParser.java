package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AttendanceCommand object
 */
public class AttendanceCommandParser implements Parser<AttendanceCommand> {

    /**
     * Parses {@code userInput} in the context of the AttendanceCommand
     * and returns a AttendanceCommand object for execution.
     *
     * @param userInput to parse.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public AttendanceCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new AttendanceCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE), pe);
        }
    }
}
