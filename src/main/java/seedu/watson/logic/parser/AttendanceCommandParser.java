package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_INDEX_NUMBERS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_STUDENTCLASS;

import java.util.List;

import seedu.watson.logic.commands.AttendanceCommand;
import seedu.watson.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AttendanceCommand object
 */
public class AttendanceCommandParser implements Parser<AttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AttendanceCommand
     * and returns AttendanceCommand object for execution.
     *
     * @param args Input arguments of the user.
     * @return A AttendanceCommand object.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AttendanceCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_STUDENTCLASS, PREFIX_INDEX_NUMBERS);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_STUDENTCLASS, PREFIX_INDEX_NUMBERS)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE));
        }

        String date = argMultimap.getValue(PREFIX_DATE).get();
        String studentClass = argMultimap.getValue(PREFIX_STUDENTCLASS).get();
        List<String> indexNumbers = argMultimap.getAllValues(PREFIX_INDEX_NUMBERS);

        if (date.isEmpty() || studentClass.isEmpty() || indexNumbers == null) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE));
        }

        return new AttendanceCommand(date, studentClass, indexNumbers);
    }
}
