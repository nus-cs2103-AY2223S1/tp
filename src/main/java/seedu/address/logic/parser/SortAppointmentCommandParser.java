package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CRITERIA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import seedu.address.logic.commands.SortAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code SortAppointmentCommand} object
 */
public class SortAppointmentCommandParser implements Parser<SortAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CRITERIA, PREFIX_ORDER);

        String criteria = argMultimap.getValue(PREFIX_CRITERIA).orElse("");

        String bool = argMultimap.getValue(PREFIX_ORDER).orElse("");

        if (!bool.toLowerCase().equals("asc") && !bool.toLowerCase().equals("desc")) {
            throw new ParseException(SortAppointmentCommand.MESSAGE_USAGE);
        }

        if (criteria.equals("")) {
            throw new ParseException(SortAppointmentCommand.MESSAGE_USAGE);
        }

        if (!(criteria.toLowerCase().equals("name")|| criteria.toLowerCase().equals("test")
                || criteria.toLowerCase().equals("slot") || criteria.toLowerCase().equals("doctor"))) {
            throw new ParseException(SortAppointmentCommand.MESSAGE_USAGE);
        }

        Boolean boolValue = bool.toLowerCase().equals("asc") ? true : false;

        return new SortAppointmentCommand(criteria, boolValue);
    }
}
