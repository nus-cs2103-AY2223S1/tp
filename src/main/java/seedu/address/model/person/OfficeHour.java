package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;

/**
 * Represents a Professor's office hour in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidOfficeHour(String)}
 */
public class OfficeHour {

    public static final String MESSAGE_CONSTRAINTS =
            "Office hour must consist of:\n"
            + "- integer representation of weekday (from monday[1]- friday[5])\n"
            + "- time in HH:mm military time format (16:00 represents 4 pm)\n"
            + "- duration specified in integer (1 - 9)";

    public static final String EMPTY_OFFICE_HOUR = "";

    /*
     * The length of the OfficeHour must be specifically 9 char.
     * Day specified must be from (1 - 5) matching (monday - friday)
     * Start time must be specified in HH:mm
     * Duration must be specified as an integer (1 - 9)
     */
    public static final String VALIDATION_REGEX =
            "^(?:MONDAY|TUESDAY|WEDNESDAY|THURSDAY|FRIDAY)+,"
                    + "+ (([0]{1}\\d{1})|([1]{1}[0-2]{1}))+:+([0-5]{1}\\d{1}) "
                    + "+(?:PM|AM) +-+ (([0]{1}\\d{1})|([1]{1}[0-2]{1}))+:+([0-5]{1}\\d{1}) +(?:PM|AM)$";
    public static final String INSTRUCTION_REGEX =
            "^(?=\\S{9}$)([1-5]{1})+-+(([0-1]{1}\\d{1})|([2]{1}[0-3]{1}))+:+([0-5]{1}\\d{1}-\\d{1})$";


    public final String value;

    /**
     * @param officeHour A valid office hour.
     * @param isPresent  Whether prefix was present in user input.
     */
    public OfficeHour(String officeHour, boolean isPresent) {
        requireNonNull(officeHour);
        if (isPresent) {
            checkArgument(isValidOfficeHour(officeHour), MESSAGE_CONSTRAINTS);
            value = officeHour.toUpperCase();
        } else {
            value = EMPTY_OFFICE_HOUR;
        }
    }

    /**
     * Returns true if a given string is a valid office hour.
     *
     * @param test target string
     * @return true if test is a valid officeHour
     */
    public static boolean isValidOfficeHour(String test) {
        test = test.toUpperCase();
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string matches instruction format.
     *
     * @param test target string
     * @return true if a given string is a correct instruction
     */
    public static boolean isValidOfficeHourInstruction(String test) {
        return test.matches(INSTRUCTION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof OfficeHour
                && value.equals(((OfficeHour) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Converts the office hour stored in value to what the user inputted.
     * This is used for searching for office hours.
     * @return The user input form of the office hour stored in value.
     * @throws ParseException This exception will not be thrown as the value will always be in the correct format.
     */
    public String getUserInput() throws ParseException {
        if (!value.isEmpty()) {
            String[] seperateDay = value.split(", ");
            int dayofWeek = DayOfWeek.valueOf(seperateDay[0]).getValue();
            String[] seperateTime = seperateDay[1].split(" - ");
            DateFormat formatter = new SimpleDateFormat("hh:mm a");
            Date date1 = formatter.parse(seperateTime[0]);
            Date date2 = formatter.parse(seperateTime[1]);
            long difference = (date2.getTime() - date1.getTime()) / 3600000;
            String startTime = new SimpleDateFormat("kk:mm").format(date1);
            return String.format("%s-%s-%o", dayofWeek, startTime, difference);
        } else {
            return "";
        }
    }
}
