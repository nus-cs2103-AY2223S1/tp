package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;

import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Location;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddAppointmentCommand and returns an AddAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT_DATE, PREFIX_APPOINTMENT_LOCATION);

        if (argMultimap.getPreamble().isEmpty()
            || !arePrefixesPresent(argMultimap, PREFIX_APPOINTMENT_DATE, PREFIX_APPOINTMENT_LOCATION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        Index personIndex;

        try {
            personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE), pe);
        }

        Appointment appointment;
        DateTime appointmentDateTime;
        Location appointmentLocation;

        try {
            appointmentDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_APPOINTMENT_DATE).get());
            appointmentLocation = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_APPOINTMENT_LOCATION).get());
            appointment = ParserUtil.parseAppointment(appointmentDateTime.toString(), appointmentLocation.toString());
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        return new AddAppointmentCommand(personIndex, appointment);
    }
}
