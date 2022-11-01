package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECORD;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import seedu.address.logic.commands.FindRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.record.RecordContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindRecordCommandParser implements Parser<FindRecordCommand> {
    public static final String PREFIX_NOT_SPECIFIED = "@#$fIndREC%^&orDiNPUtVALid*()";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindRecordCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_RECORD, PREFIX_MEDICATION);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRecordCommand.MESSAGE_USAGE));
        }

        String recordDate = ParserUtil.parseDateKeyword(
                argMultimap.getValue(PREFIX_DATE).orElse(PREFIX_NOT_SPECIFIED));
        List<String> recordKeywords = ParserUtil.parseKeywords(
                argMultimap.getValue(PREFIX_RECORD).orElse(PREFIX_NOT_SPECIFIED));
        List<String> medicationKeywords = ParserUtil.parseKeywords(
                argMultimap.getValue(PREFIX_MEDICATION).orElse(PREFIX_NOT_SPECIFIED));

        if (noPrefixesPresent(recordKeywords, medicationKeywords, recordDate)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRecordCommand.MESSAGE_USAGE));
        }

        return new FindRecordCommand(
                new RecordContainsKeywordsPredicate(recordKeywords, medicationKeywords, recordDate));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean noPrefixesPresent(
            List<String> recordKeywords, List<String> medicationKeywords, String date) {
        return recordKeywords.isEmpty() && medicationKeywords.isEmpty() && date.isBlank();
    }

    /**
     * Returns true if find date is a valid month and date
     * @param date date to be validated
     */
    public static boolean isValidFindDate(String date) {
        ZoneId timeZone = ZoneOffset.UTC;
        DateTimeFormatter validateDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        try {
            LocalDateTime toFind = LocalDateTime.parse("01-" + date + " 1400", validateDate);
            YearMonth currentMonth = YearMonth.now(timeZone);
            return !YearMonth.from(toFind).isAfter(currentMonth);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

