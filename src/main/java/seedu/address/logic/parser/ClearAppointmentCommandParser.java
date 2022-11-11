package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearAppointmentCommand object.
 */
public class ClearAppointmentCommandParser implements Parser<ClearAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearAppointmentCommand
     * and returns ClearAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ClearAppointmentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ClearAppointmentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearAppointmentCommand.MESSAGE_USAGE), pe);
        }
    }
}
