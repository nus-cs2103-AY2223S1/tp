package longtimenosee.model.person;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Encapsulation for a client's birthday
 */

public class Birthday {
    public static final String MESSAGE_DATE_CONSTRAINTS = "Date of birth cannot be in the future or invalid";
    public static final String STANDARD_DATE = "yyyy-MM-dd";
    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Date of Birth must follow Format: " + STANDARD_DATE;
    private static final String BIRTHDAY_VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public final String value;


    private LocalDate birthday;
    private boolean celebrated;



    /**
     * Main constructor for Birthday
     * @param value
     */
    public Birthday(String value) {
        this.value = value;
        this.birthday = parseBirthday(value);
        this.celebrated = false;
    }


    public Birthday unspecified() {
        return new Birthday(null);
    }
    public boolean checkCelebrated() {
        return this.celebrated;
    }

    public void celebrateBirthday() {
        this.celebrated = true;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    /**
     * Utility function to check if a birthday is valid
     */

    public static boolean isValidBirthday(String date) {
        try {
            LocalDate verifiedDate = LocalDate.parse(date);
        } catch (DateTimeParseException dte) {
            return false;
        }
        LocalDate verifiedDate = LocalDate.parse(date);
        return LocalDate.now().isEqual(verifiedDate) || LocalDate.now().isAfter(ChronoLocalDate.from(verifiedDate));
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
        boolean sameCelebrate = this.checkCelebrated() == otherBirthday.checkCelebrated();
        if (this.getBirthday() == null || otherBirthday.getBirthday() == null) {
            return sameCelebrate && this.getBirthday() == otherBirthday.getBirthday();
        } else {
            return sameCelebrate && this.getBirthday().equals(otherBirthday.getBirthday());
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
