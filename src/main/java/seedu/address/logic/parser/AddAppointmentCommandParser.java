package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Appointment;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object.
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE), pe);
        }

        if (argumentMultimap.getValue(PREFIX_DATE).isEmpty()) {
            throw new ParseException(AddAppointmentCommand.DATE_MISSING);
        }

        String appointmentDate = argumentMultimap.getValue(PREFIX_DATE).orElse("");

        if (!Appointment.isValidDate(appointmentDate)) {
            throw new ParseException(AddAppointmentCommand.DATE_MISSING);
        }

        return new AddAppointmentCommand(index, appointmentDate);
    }
}
