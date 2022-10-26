package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX_NUMBERS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTCLASS;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindNameCommand;
import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FindCommandPredicate;

public class MarkAttendanceCommandParser {

    public MarkAttendanceCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        //String[] findCommandKeywords = new String[3];

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAttendanceCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_STUDENTCLASS, PREFIX_INDEX_NUMBERS);

        String date = String.valueOf(argMultimap.getValue(PREFIX_DATE));
        String studentClass = String.valueOf(argMultimap.getValue(PREFIX_STUDENTCLASS));
        List<String> indexNumbers = argMultimap.getAllValues(PREFIX_INDEX_NUMBERS);

        if (date.isEmpty() || studentClass.isEmpty() || indexNumbers == null) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAttendanceCommand.MESSAGE_USAGE));
        }

        return new MarkAttendanceCommand(date, studentClass, indexNumbers);
    }
}
