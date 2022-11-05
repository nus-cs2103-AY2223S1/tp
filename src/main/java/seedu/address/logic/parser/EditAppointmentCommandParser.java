package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.commons.util.StringUtil.isInteger;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;

import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.DateTime;

/**
 * Parses input arguments and creates a new EditAppointmentCommand object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditAppointmentCommand and returns an EditAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT_DATE, PREFIX_APPOINTMENT_LOCATION);

        Index personIndex;
        Index appointmentIndex;

        String personAppointmentIndex = argMultimap.getPreamble().trim();
        String[] splitStr = personAppointmentIndex.split("\\.");
        if (splitStr.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE));
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
                    EditAppointmentCommand.MESSAGE_USAGE), pe);
        }
        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();


        if (argMultimap.getValue(PREFIX_APPOINTMENT_DATE).isPresent()) {
            try {
                editAppointmentDescriptor.setDateTime(
                    ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_APPOINTMENT_DATE).get()));
            } catch (DateTimeParseException e) {
                if (e.getCause() == null) {
                    throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
                }
                String str = e.getCause().getMessage();
                throw new ParseException(str);
            }
        }

        if (argMultimap.getValue(PREFIX_APPOINTMENT_LOCATION).isPresent()) {
            editAppointmentDescriptor.setLocation(
                    ParserUtil.parseLocation(argMultimap.getValue(PREFIX_APPOINTMENT_LOCATION).get()));
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE));
        }

        return new EditAppointmentCommand(personIndex, appointmentIndex, editAppointmentDescriptor);
    }
}
