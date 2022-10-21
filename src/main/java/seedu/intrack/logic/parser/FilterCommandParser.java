package seedu.intrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.intrack.logic.commands.FilterCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.internship.StatusIsKeywordPredicate;


/**
 * Parses input arguments and creates a new {@code FilterCommand} object
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    /**
     * Parses the given {@code String} argument in the context of the {@code FilterCommand}
     * and returns a {@code FilterCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();

        switch (trimmedArgs.toUpperCase()) {
        case "":
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterCommand.MESSAGE_USAGE));
        case "O":
            return new FilterCommand(new StatusIsKeywordPredicate("Offered"));
        case "P":
            return new FilterCommand(new StatusIsKeywordPredicate("Progress"));
        case "R":
            return new FilterCommand(new StatusIsKeywordPredicate("Rejected"));
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterCommand.FILTER_COMMAND_CONSTRAINTS));
        }
    }
}
