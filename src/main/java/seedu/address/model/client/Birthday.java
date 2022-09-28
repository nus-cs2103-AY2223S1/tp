package seedu.address.model.client;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class Birthday {
    private static final String STANDARD_DATE = "yyyy-MM-dd";

    private static final String BIRTHDAY_VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public static final String MESSAGE_DATE_CONSTRAINTS = "Date of birth cannot be in the future or invalid";

    public static final String MESSAGE_FORMAT_CONSTRAINTS = "String entered does not follow format: " + STANDARD_DATE;


    private LocalDate birthday;
    private boolean celebrated;

    public final String value;

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

    /**Add a reminder to celebrate if not true?
     *
     */
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

    /**TODO: Think about how to addbirthday
     *
     */

    public static LocalDate parseBirthday(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STANDARD_DATE);
        try {
            LocalDate dateTime = LocalDate.parse(s);
        } catch (DateTimeParseException f) {
            return LocalDate.parse("2020-05-05"); //TODO: Change to a better default value
        }
        LocalDate dateTime = LocalDate.parse(s);
        return dateTime;
    }



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
    public int hashCode() {
        return birthday.hashCode();
    }
    
}
