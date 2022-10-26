package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * A class to support the correct and accurate formatting of date in Deadline class.
 */
public class FormatDate {
    public static final String MESSAGE_CONSTRAINTS =
            "The date given should be in the format of YYYY-MM-DD. Leading zeros are required as well.";

    public static final String VALIDATION_REGEX = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    public final LocalDate formatDate;

    /**
     * @param str pass as string in the format of yyyy-mm-dd.
     */
    public FormatDate(String str) {
        requireNonNull(str);
        checkArgument(isValidDate(str), MESSAGE_CONSTRAINTS);
        this.formatDate = LocalDate.parse(str);
    }

    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * @param str "MMM dd yyyy".
     * @return the correct String format "yyyy-MM-dd" which can be interpreted by the parser.
     */
    public static String correctDateFormat(String str) {
        SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat from = new SimpleDateFormat("MMM dd yyyy");
        String result = null;
        try {
            Date date = from.parse(str);
            result = to.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(MESSAGE_CONSTRAINTS);
        }
        return result;
    }

    /**
     * @return converts string format from yyyy-mm-dd to MMM d yyyy.
     */
    @Override
    public String toString() {
        return this.formatDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FormatDate // instanceof handles nulls
                && formatDate.equals(((FormatDate) other).formatDate)); // state check
    }
}
