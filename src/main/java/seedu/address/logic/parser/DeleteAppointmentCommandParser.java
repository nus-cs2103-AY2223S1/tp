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
        Index personIndex;
        Index appointmentIndex;

        try {
            String personAppointmentIndex = argMultimap.getPreamble();
            personIndex = ParserUtil.parsePersonIndex(personAppointmentIndex);
            appointmentIndex = ParserUtil.parseAppointmentIndex(personAppointmentIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAppointmentCommand.MESSAGE_USAGE), pe);
        }


        return new DeleteAppointmentCommand(personIndex, appointmentIndex);
    }
}
