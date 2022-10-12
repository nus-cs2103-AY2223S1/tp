package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FilterAppointmentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AppointmentContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterPatientCommand object
 */
public class FilterAppointmentsCommandParser implements Parser<FilterAppointmentsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterPatientCommand
     * and returns a FilterPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterAppointmentsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] keywords = trimmedArgs.split("\\s+");
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterAppointmentsCommand.MESSAGE_USAGE));
        }



        return new FilterAppointmentsCommand(new AppointmentContainsKeywordsPredicate(Arrays.asList(keywords)));
    }

}
