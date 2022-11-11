package longtimenosee.model.person;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Comparator;

/**
 * Encapsulation for a client's birthday
 */

public class Birthday {
    public static final int TWENTIETH_CENTURY_BOUNDARY = 1900;
    public static final Comparator<Person> BIRTHDAY_COMPARATOR = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getBirthday().getBirthday().compareTo(p2.getBirthday().getBirthday());
        }
    };
    public static final String MESSAGE_DATE_CONSTRAINTS = "Date of birth cannot be in the future or "
             + "before the 20th century";
    public static final String RANGE_FORMAT_CONSTRAINTS = "Ensure that Year/Month/Date values entered are valid."
            + "\n Such that it is a valid date.";
    public static final String STANDARD_DATE = "yyyy-MM-dd";
    public static final String SORT_BIRTHDAY = "birthday";
    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Date of Birth must follow Format: " + STANDARD_DATE;
    private static final String BIRTHDAY_VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public final String value;

    private LocalDate birthday;

    /**
     * Main constructor for Birthday
     * @param value
     */
    public Birthday(String value) {
        this.value = value;
        this.birthday = parseBirthday(value);
    }

    /**
     * Utility function to check if values entered are within range of an appropriate date.
     * * i.e: 0 <= Day <= 31, 0 <= Month <= 12
     * The LocalDate library is then used to parse these values.
     * To account for edge cases like Leap years.
     * @param date
     * @return
     */
    public static boolean isValidDate(String date) {
        assert(isValidFormat(date) == true);
        try {
            LocalDate attemptToParseBirthday = LocalDate.parse(date);
        } catch (DateTimeParseException invalidValueException) {
            return false;
        }
        return true;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    /**
     * Utility function to check if a birthday is reasonable
     * date parsed in must be of a valid format (i.e: YYYY-MM-DD)
     * Checks that a client's birthday is reasonable:
     * i.e: Birthday is not in the future, and not before the 20th century.
     *
     */

    public static boolean isReasonableBirthday(String date) {
        assert(isValidFormat(date) == true);
        try {
            LocalDate verifiedDate = LocalDate.parse(date);
        } catch (DateTimeParseException dte) {
            return false;
        }
        LocalDate verifiedDate = LocalDate.parse(date);
        int verifiedYear = verifiedDate.getYear();
        return verifiedYear >= TWENTIETH_CENTURY_BOUNDARY
            && (LocalDate.now().isEqual(verifiedDate) || LocalDate.now().isAfter(ChronoLocalDate.from(verifiedDate)));
    }

    /**
     * Credits to: https://stackoverflow.com/questions/2149680/regex-date-format-validation-on-java
     * For regex example for YYYY-MM-DD
     */
    public static boolean isValidFormat(String date) {
        return date.matches(BIRTHDAY_VALIDATION_REGEX);
    }

    /**
     * Parses a birthday in string form and checks for validity
     * @param  s
     * @return LocalDate
     */
    public static LocalDate parseBirthday(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STANDARD_DATE);
        try {
            LocalDate dateTime = LocalDate.parse(s);
        } catch (DateTimeParseException f) {
            return LocalDate.parse("2000-05-05"); //TODO: Change to a better default value
        }
        LocalDate dateTime = LocalDate.parse(s);
        return dateTime;
    }


    /**
     * Calculates a client's age based on his birthday input
     * @return age
     */
    public int calculateAge() {
        int currentYear = LocalDate.now().getYear();
        return currentYear - birthday.getYear();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Birthday)) {
            return false;
        }
        Birthday otherBirthday = (Birthday) other;
        if (this.getBirthday() == null || otherBirthday.getBirthday() == null) {
            return this.getBirthday() == otherBirthday.getBirthday();
        } else {
            return this.getBirthday().equals(otherBirthday.getBirthday());
        }
    }

    @Override
    public String toString() {
        return birthday.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG));
    }

    @Override
    public int hashCode() {
        return birthday.hashCode();
    }
}
