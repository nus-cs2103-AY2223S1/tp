package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.InternshipHasApplicationStatusPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedLowerCaseArgs = args.trim().toLowerCase();
        if (trimmedLowerCaseArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        return new FilterCommand(
                new InternshipHasApplicationStatusPredicate(ApplicationStatus.parse(trimmedLowerCaseArgs)));
    }
}
