package seedu.address.model.pet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.exceptions.IllegalValueException;

public class DateOfBirth {
    //@@author Hongyi6328-reused
    //Reused from https://github.com/RussellDash332/ip/blob/master/src/main/java/stashy/parser/Parser.java
    //with minor modification, it is a pretty good way to organise and extend the acceptable date formats.
    private static final String[] ACCEPTABLE_DATE_FORMATS = new String[]{
        "MMM dd yyyy",
        "dd/MM/yyyy",
        "yyyy/MM/dd",
        "yyyy-MM-dd",
        "dd MMM yyyy",
        "dd MMM yyyy",
        "MMM dd, yyyy",
        "MMM dd, yyyy"
    };

    private static final String PREFERRED_DATE_FORMAT = "yyyy-MM-dd";

    private static final DateTimeFormatter PREFERRED_FORMATTER = DateTimeFormatter.ofPattern(PREFERRED_DATE_FORMAT);

    private final LocalDate date;

    DateOfBirth(LocalDate date) {
        this.date = date;
    }

    public static DateOfBirth parseString(String toParse) throws IllegalValueException {
        LocalDate output;
        for (String format: ACCEPTABLE_DATE_FORMATS) {
            try {
                output = LocalDate.parse(toParse, DateTimeFormatter.ofPattern(format));
                return new DateOfBirth(output);
            } catch (DateTimeParseException exception) {
                //Do nothing because it will eventually throw an exception if no formats match
            }
        }
        throw new IllegalValueException("The date of birth should be in format: " + PREFERRED_DATE_FORMAT);
    }

    @Override
    public String toString() {
        return date.format(PREFERRED_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateOfBirth // instanceof handles nulls
                && date.equals(((DateOfBirth) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    public LocalDate getDate() {
        return date;
    }
}
