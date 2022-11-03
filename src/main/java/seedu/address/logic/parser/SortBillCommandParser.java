package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CRITERIA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import seedu.address.logic.commands.SortBillCommand;
import seedu.address.logic.commands.SortPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code SortBillCommand} object
 */
public class SortBillCommandParser implements Parser<SortBillCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortBillCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CRITERIA, PREFIX_ORDER);

        String criteria = argMultimap.getValue(PREFIX_CRITERIA).orElse("");
        String bool = argMultimap.getValue(PREFIX_ORDER).orElse("");

        if (!bool.toLowerCase().equals("asc") && !bool.toLowerCase().equals("desc")) {
            throw new ParseException(SortBillCommand.MESSAGE_USAGE);
        }

        if (criteria.equals("")) {
            throw new ParseException(SortBillCommand.MESSAGE_USAGE);
        }

        if (!(criteria.toLowerCase().equals("name")|| criteria.toLowerCase().equals("amount")
                || criteria.toLowerCase().equals("date") || criteria.toLowerCase().equals("status"))) {
            throw new ParseException(SortBillCommand.MESSAGE_USAGE);
        }

        Boolean boolValue = bool.toLowerCase().equals("asc") ? true : false;

        return new SortBillCommand(criteria, boolValue);
    }
}
