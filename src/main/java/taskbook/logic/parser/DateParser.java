package taskbook.logic.parser;

import static taskbook.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import taskbook.logic.parser.exceptions.ParseException;

/**
 * Parses user input's date into internal Date object.
 * Accepts multiple Date formats.
 */
public class DateParser {

    private static final DateTimeFormatter PARSER_OPTIONAL_FORMATS = new DateTimeFormatterBuilder()
        .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
        .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        .appendOptional(DateTimeFormatter.ofPattern("MMM dd yyyy"))
        .appendOptional(DateTimeFormatter.ofPattern("MM dd yyyy"))
        .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy"))
        .appendOptional(DateTimeFormatter.ofPattern("dd MM yyyy"))
        .toFormatter();

    /**
     * Parses {@code userInput} into a Date object and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected formats.
     */
    public static LocalDate parse(String userInput) throws ParseException {
        try {
            return LocalDate.parse(userInput, PARSER_OPTIONAL_FORMATS);
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DATE_FORMAT);
        }
    }
}
