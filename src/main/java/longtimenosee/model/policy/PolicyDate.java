package longtimenosee.model.policy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Comparator;

import longtimenosee.model.person.Person;


/**
 * Encapsulation for a policy's date
 */

public class PolicyDate {
    public static final Comparator<Person> POLICY_DATE_COMPARATOR = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getBirthday().getBirthday().compareTo(p2.getBirthday().getBirthday());
        }
    };
    public static final String STANDARD_DATE = "yyyy-MM-dd";
    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Years must be between (including) 1900 "
            + "to 2100 and follow Format: "
            + STANDARD_DATE;
    private static final String DATE_VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public final String value;

    private LocalDate date;

    /**
     * Main constructor for PolicyDate
     * @param value
     */
    public PolicyDate(String value) {
        this.value = value;

        this.date = parsePolicyDate(value);
    }

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Utility function to check if a birthday is valid
     */

    public static boolean isValidDate(String date) {
        try {
            LocalDate verifiedDate = LocalDate.parse(date);
        } catch (DateTimeParseException dte) {
            return false;
        }
        LocalDate verifiedDate = LocalDate.parse(date);
        LocalDate lowerLimit = LocalDate.of(1899, 12, 31);
        LocalDate upperLimit = LocalDate.of(2101, 1, 1);
        return verifiedDate.isAfter(lowerLimit) && verifiedDate.isBefore(upperLimit);
    }

    /**
     * Credits to: https://stackoverflow.com/questions/2149680/regex-date-format-validation-on-java
     * For regex example for YYYY-MM-DD
     */
    public static boolean isValidFormat(String date) {
        return date.matches(DATE_VALIDATION_REGEX);
    }

    /**
     * Parses a policy date in string form and checks for validity
     * @param  s
     * @return LocalDate
     */
    public static LocalDate parsePolicyDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STANDARD_DATE);
        LocalDate dateTime = LocalDate.parse(s, formatter);
        return dateTime;
    }



    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PolicyDate)) {
            return false;
        }
        PolicyDate otherPolicyDate = (PolicyDate) other;
        return this.getDate().equals(otherPolicyDate.getDate());
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG));
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
