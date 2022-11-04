package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.getcommands.GetAppointmentByDateCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AppointmentByDatePredicate;

/**
 * Parses input arguments and creates a new GetAppointmentByDateCommand object
 */
public class GetAppointmentByDateCommandParser implements Parser<GetAppointmentByDateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetAppointmentByDate
     * and returns a GetAppointmentByDateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public GetAppointmentByDateCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetAppointmentByDateCommand.MESSAGE_USAGE));
        }

        List<LocalDate> appointments = new ArrayList<>();
        for (String arg : trimmedArgs.split("\\s+")) {
            try {
                LocalDate appointment = LocalDate.parse(arg, DateTimeFormatter.ofPattern("dd-MM-uuuu"));
                appointments.add(appointment);
            } catch (DateTimeParseException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetAppointmentByDateCommand.MESSAGE_USAGE));
            }
        }

        return new GetAppointmentByDateCommand(new AppointmentByDatePredicate(appointments));
    }

}
