package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.commons.util.StringUtil.isInteger;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input argument and creates a new DeleteAppointmentCommand object
 */
public class DeleteAppointmentCommandParser implements Parser<DeleteAppointmentCommand> {

    /**
     * Parses the given {@code String} of Index argument
     * and returns an DeleteAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        Index personIndex;
        Index appointmentIndex;

        String personAppointmentIndex = argMultimap.getPreamble().trim();
        String[] splitStr = personAppointmentIndex.split("\\.");
        if (splitStr.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAppointmentCommand.MESSAGE_USAGE));
        }

        String oneBasedPersonIndexStr = splitStr[0];
        if (isInteger(oneBasedPersonIndexStr) && Integer.parseInt(oneBasedPersonIndexStr) <= 0) {
            throw new ParseException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        String oneBasedAppointmentIndexStr = splitStr[1];
        if (isInteger(oneBasedAppointmentIndexStr) && Integer.parseInt(oneBasedAppointmentIndexStr) <= 0) {
            throw new ParseException(MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }
        try {
            personIndex = ParserUtil.parseIndex(oneBasedPersonIndexStr);
            appointmentIndex = ParserUtil.parseIndex(oneBasedAppointmentIndexStr);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAppointmentCommand.MESSAGE_USAGE), pe);
        }


        return new DeleteAppointmentCommand(personIndex, appointmentIndex);
    }
}
