package gim.model.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Singleton class that contains the formatters needed to parse dates.
 */
public class FormatterList {
    private static FormatterList formatterListInstance = null;
    private static List<DateTimeFormatter> formatterList = null;

    private static final String[] validDateFormats = {
        "uuuu/MM/dd", "uuuu/M/dd", "uuuu/MM/d", "uuuu/M/d",
        "dd/MM/uuuu", "dd/M/uuuu", "d/MM/uuuu", "d/M/uuuu",

        "dd-MM-uuuu", "dd-M-uuuu", "d-MM-uuuu", "d-M-uuuu",
        "uuuu-MM-dd", "uuuu-M-dd", "uuuu-MM-d", "uuuu-M-d",

        "dd MM uuuu", "dd M uuuu", "d MM uuuu", "d M uuuu",
        "uuuu MM dd", "uuuu M dd", "uuuu MM d", "uuuu M d",

    };

    private FormatterList() {
        formatterList = Arrays.stream(validDateFormats)
                .map(pattern -> DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT))
                .collect(Collectors.toList());
    }

    /**
     * Factory method to obtain singleton instance of FormatterList.
     * @return FormatterList object
     */
    public static FormatterList getFormatterList() {
        if (formatterListInstance == null) {
            formatterListInstance = new FormatterList();
        }
        return formatterListInstance;
    }

    /**
     * If date string does not match any formatter, return null.
     * @param date date string
     * @return LocalDate object that has been formatted using a valid formatter
     */
    public LocalDate validateDateWithFormatters(String date) {
        for (DateTimeFormatter formatter : formatterList) {
            LocalDate parsedDate = parseDateByFormatter(date, formatter);
            if (parsedDate != null) {
                return parsedDate;
            }
        }
        return null;
    }

    /**
     * Parses the date string using the specified formatter.
     *
     * LocalDate.parse will throw DateTimeParseException if the date string does not conform to the
     * date format of the formatter. In this case, null is returned to signify that the date string
     * does not match the formatter.
     *
     * If there is a valid match between the date and the formatter, a LocalDate object created from
     * the date string parsed using the formatter will be returned.
     *
     * Justification for usage of try-catch block:
     *
     * The try-catch block in this method is necessary as the Java API for LocalDate does not provide
     * a method that returns a boolean to check for the validity of the date, but the method parse
     * instead returns an exception which makes if-else statements to work for this case.
     *
     * @param date string representation of the date
     * @param formatter DateTimeFormatter object
     * @return null if date does not conform to formatter and LocalDate if it does
     */
    private LocalDate parseDateByFormatter(String date, DateTimeFormatter formatter) {
        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
        return parsedDate;
    }
}
