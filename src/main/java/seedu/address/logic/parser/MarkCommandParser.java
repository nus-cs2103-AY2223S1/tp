package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser extends SelectAppointmentCommandParser<MarkCommand> {
    @Override
    public MarkCommand parse(String args) throws ParseException {
        try {
            Index appointmentIndex = super.getAppointmentIndex(args);

            return new MarkCommand(appointmentIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), pe);
        }
    }
}
