package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeletePastAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePastAppointmentCommand object.
 */
public class DeletePastAppointmentCommandParser implements Parser<DeletePastAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeletePastAppointmentCommand
     * and returns a DeletePastAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeletePastAppointmentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeletePastAppointmentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePastAppointmentCommand.MESSAGE_USAGE), pe);
        }
    }
}
