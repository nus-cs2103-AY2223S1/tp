package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_INDEX_NUMBERS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_STUDENTCLASS;

import java.util.List;

import seedu.watson.logic.commands.MarkAttendanceCommand;
import seedu.watson.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkAttendanceCommand object
 */
public class MarkAttendanceCommandParser implements Parser<MarkAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkAttendanceCommand
     * and returns MarkAttendanceCommand object for execution.
     *
     * @param args Input arguments of the user.
     * @return A MarkAttendanceCommand object.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkAttendanceCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAttendanceCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_STUDENTCLASS, PREFIX_INDEX_NUMBERS);

        String date = argMultimap.getValue(PREFIX_DATE).get();
        String studentClass = argMultimap.getValue(PREFIX_STUDENTCLASS).get();
        List<String> indexNumbers = argMultimap.getAllValues(PREFIX_INDEX_NUMBERS);

        if (date.isEmpty() || studentClass.isEmpty() || indexNumbers == null) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAttendanceCommand.MESSAGE_USAGE));
        }

        return new MarkAttendanceCommand(date, studentClass, indexNumbers);
    }
}
