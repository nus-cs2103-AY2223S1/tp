package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CRITERIA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import seedu.address.logic.commands.SortPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code SortPatientCommand} object
 */
public class SortPatientCommandParser implements Parser<SortPatientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortPatientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CRITERIA, PREFIX_ORDER);

        String criteria = argMultimap.getValue(PREFIX_CRITERIA).orElse("");
        String bool = argMultimap.getValue(PREFIX_ORDER).orElse("");

        if (!bool.toLowerCase().equals("asc") && !bool.toLowerCase().equals("desc")) {
            throw new ParseException(SortPatientCommand.MESSAGE_USAGE);
        }

        if (criteria.equals("")) {
            throw new ParseException(SortPatientCommand.MESSAGE_USAGE);
        }

        if (!(criteria.toLowerCase().equals("name")|| criteria.toLowerCase().equals("phone")
                || criteria.toLowerCase().equals("email") || criteria.toLowerCase().equals("address"))) {
            throw new ParseException(SortPatientCommand.MESSAGE_USAGE);
        }

        Boolean boolValue = bool.toLowerCase().equals("asc") ? true : false;

        return new SortPatientCommand(criteria, boolValue);
    }
}
