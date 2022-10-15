package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Appointment;

/**
 *  Parses input arguments and creates a new EditAppointmentCommand object.
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    @Override
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REASON, PREFIX_DATE);
        Index appointmentIndex;
        try {
            appointmentIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        addReason(editAppointmentDescriptor, argMultimap);
        addDate(editAppointmentDescriptor, argMultimap);

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAppointmentCommand(appointmentIndex, editAppointmentDescriptor);
    }

    private void addReason(EditAppointmentDescriptor descriptor, ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_REASON).isPresent()) {
            String reason = argMultimap.getValue(PREFIX_REASON).get().trim();
            if (Appointment.isValidReason(reason)) {
                descriptor.setReason(reason);
            } else {
                throw new ParseException(Appointment.REASON_MESSAGE_CONSTRAINTS);
            }
        }
    }

    private void addDate(EditAppointmentDescriptor descriptor, ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            String dateTime = argMultimap.getValue(PREFIX_DATE).get().trim();
            if (Appointment.isValidDateTime(dateTime)) {
                String temp = String.join(" ", dateTime.split("\\s+", 2));
                descriptor.setDateTime(LocalDateTime.parse(temp, Appointment.DATE_FORMATTER));
            } else {
                throw new ParseException(Appointment.DATE_MESSAGE_CONSTRAINTS);
            }
        }
    }
}
