package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Appointment;

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
                ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        if (argMultimap.getPreamble().isEmpty() || !arePrefixesPresent(argMultimap, PREFIX_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE));
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        try {
            parseAppointmentsForEdit(argMultimap.getAllValues(PREFIX_DATE))
                    .ifPresent(editPersonDescriptor::setAppointments);
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        return new EditAppointmentCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> datesAndTimes} into a
     * {@code Set<Appointment>} if {@code datesAndTimes} is non-empty.
     */
    private Optional<Set<Appointment>> parseAppointmentsForEdit(Collection<String> datesAndTimes)
            throws ParseException {
        assert datesAndTimes != null;

        if (datesAndTimes.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseAppointments(datesAndTimes));
    }
}
