package seedu.foodrem.commons.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Helper functions for handling validation.
 */
public class ValidationUtil {
    /**
     * Returns {@code true} if a string can be parsed into a double, {@code false} otherwise.
     *
     * @param doubleString a string to be parsed.
     */
    public static boolean isParsableDouble(String doubleString) {
        try {
            Double.parseDouble(doubleString);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns {@code true} if a string can be parsed into a date with the provided format, {@code false} otherwise.
     *
     * @param dateString a string to be parsed.
     * @param dateRegex  the format the of the date to be parsed.
     */
    public static boolean isParsableDateString(String dateString, String dateRegex) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateRegex);
        try {
            LocalDate.parse(dateString, dateTimeFormatter.withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns {@code true} if a double is positive or zero, {@code false} otherwise
     *
     * @param number a double to be checked
     */
    public static boolean isNonNegative(double number) {
        return number >= 0;
    }

    /**
     * Returns {@code true} if a string representing a double is too precise, {@code false} otherwise.
     *
     * @param string                   a string that represents a double.
     * @param maxNumberOfDecimalPlaces an int representing the number of decimal places
     */
    public static boolean isDoubleTooPrecise(String string, int maxNumberOfDecimalPlaces) {
        assert maxNumberOfDecimalPlaces >= 0;
        String decimalPoint = ".";
        if (!string.contains(decimalPoint)) {
            return false;
        }
        int numberOfDecimalPoints = string.length() - string.indexOf(decimalPoint) - 1;
        return numberOfDecimalPoints > maxNumberOfDecimalPlaces;
    }
}
