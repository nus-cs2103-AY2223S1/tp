package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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

        String personAppointmentIndex = argMultimap.getPreamble().trim();
        String[] splitStr = personAppointmentIndex.split("\\.");
        if (splitStr.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAppointmentCommand.MESSAGE_USAGE));
        }

        String oneBasedPersonIndexStr = splitStr[0];
        String oneBasedAppointmentIndexStr = splitStr[1];
        Index personIndex;
        Index appointmentIndex;
        try {
            personIndex = ParserUtil.parsePersonIndex(oneBasedPersonIndexStr);
            appointmentIndex = ParserUtil.parseAppointmentIndex(oneBasedAppointmentIndexStr);
        } catch (NumberFormatException nfe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAppointmentCommand.MESSAGE_USAGE));
        }


        return new DeleteAppointmentCommand(personIndex, appointmentIndex);
    }
}
