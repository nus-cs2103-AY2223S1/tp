package modtrekt.logic.parser.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

/**
 * Converts an input String into LocalDate, for use with JCommander annotations.
 */
public class DeadlineConverter implements IStringConverter<LocalDate> {
    @Override
    public LocalDate convert(String value) {
        String trimmedDate = value.trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            return LocalDate.parse(trimmedDate, formatter);
        } catch (DateTimeParseException exception) {
            throw new ParameterException("Invalid date, date must be in YYYY-MM-DD format");
        }
    }
}
