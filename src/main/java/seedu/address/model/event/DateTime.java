package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an Event's start or end datetime in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {

    public static final String RECOMMENDED_FORMAT = "dd/MM/yyyy";
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should follow a valid format. Try " + RECOMMENDED_FORMAT;
    public static final String MESSAGE_INVALID_DATETIME =
            "The values for date and time are invalid! Try " + RECOMMENDED_FORMAT;

    public static final String REGEX_YEAR = "(?<yearGroup>[0-9]{4})";
    public static final String REGEX_MONTH_DIGITS = "(((0)?[0-9])|((1)[0-2]))";
    public static final String REGEX_MONTH_LETTERS =
            "((jan)|(feb)|(mar)|(apr)|(may)|(jun)|(jul)|(aug)|(sep)|(oct)|(nov)|(dec))";
    public static final String REGEX_MONTH = "(?<monthGroup>" + REGEX_MONTH_LETTERS + "|" + REGEX_MONTH_DIGITS + ")";
    public static final String REGEX_DAY = "(?<dayGroup>[1-9]|[0-2][0-9]|(3)[0-1])";
    public static final String REGEX_SECONDS = "([0-5]?[0-9])";
    public static final String REGEX_MINUTES = "(([0-5][0-9]))";
    public static final String REGEX_HOURS = "([01][0-9]|2[0-3])";
    public static final String REGEX_TIME_COLON =
            "(" + REGEX_HOURS + ":" + REGEX_MINUTES + "(:" + REGEX_SECONDS + ")?)";
    public static final String REGEX_TIME_NO_SPACE =
            "(" + REGEX_HOURS + REGEX_MINUTES + "(" + REGEX_SECONDS + ")?)";
    public static final String REGEX_TIME = REGEX_TIME_COLON + "|" + REGEX_TIME_NO_SPACE;

    public static final HashSet<String> REGEX_DATES = new HashSet<>() {
        {
            add("((?<dateGroup>" + REGEX_DAY + "\\s" + REGEX_MONTH + "\\s" + REGEX_YEAR + "))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME + "))?");
            add("((?<dateGroup>" + REGEX_YEAR + "\\s" + REGEX_MONTH + "\\s" + REGEX_DAY + "))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME + "))?");
            add("((?<dateGroup>" + REGEX_DAY + "-" + REGEX_MONTH + "-" + REGEX_YEAR + "))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME + "))?");
            add("((?<dateGroup>" + REGEX_YEAR + "-" + REGEX_MONTH + "-" + REGEX_DAY + "))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME + "))?");
            add("((?<dateGroup>" + REGEX_DAY + "/" + REGEX_MONTH + "/" + REGEX_YEAR + "))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME + "))?");
            add("((?<dateGroup>" + REGEX_YEAR + "/" + REGEX_MONTH + "/" + REGEX_DAY + "))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME + "))?");
        }
    };

    private static final HashSet<String> REGEX_FORMAT = new HashSet<>() {
        {
            add("(\\S+/\\S+/\\S+)\\s((\\d+{1,2}:\\d+{1,2}(:\\d+)?)|(\\d+{4}))?"); // checks for DATE_SLASH and any time format
            add("(\\S+\\s\\S+\\s\\S+)\\s((\\d+{1,2}:\\d+{2}(:\\d+{2})?)|(\\d+{4}))?"); // checks for DATE_SPACE and any time format
            add("(\\S+-\\S+-\\S+)\\s((\\d+{1,2}:\\d+{2}(:\\d+{2})?)|(\\d+{4}))?"); // checks for DATE_DASH and any time format
        }
    };

    private static final Pattern REGEX_TIME_GENERATOR = Pattern.compile(
            "((?<hoursGroup>" + REGEX_HOURS + ")(:?)"
                    + "(?<minutesGroup>" + REGEX_MINUTES + ")"
                    + "((:?)(?<secondsGroup>" + REGEX_SECONDS + "))?)");

    public final LocalDate date;
    public final Optional<LocalTime> time;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid datetime string.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.date = parseDate(dateTime);
        this.time = parseTime(dateTime);
    }

    /**
     * Returns a LocalTime object for an input time in the valid formats.
     */
    public static LocalTime generateLocalTime(String time) {
        Matcher matcher = REGEX_TIME_GENERATOR.matcher(time);
        matcher.matches();
        String hours = matcher.group("hoursGroup");
        String minutes = matcher.group("minutesGroup");
        String seconds = matcher.group("secondsGroup");
        String formatterString = "HHmm";
        if (seconds != null) {
            formatterString += "ss";
            return LocalTime.parse(hours + minutes + seconds, DateTimeFormatter.ofPattern(formatterString));
        }
        return LocalTime.parse(hours + minutes, DateTimeFormatter.ofPattern(formatterString));
    }

    /**
     * Returns a LocalDate object for an input date in the valid formats.
     */
    public static LocalDate generateLocalDate(String date) {
        Pattern pattern = Pattern.compile("");
        for (String regex : REGEX_DATES) {
            if (date.toLowerCase().matches(regex)) {
                pattern = Pattern.compile(regex);
            }
        }
        Matcher matcher = pattern.matcher(date);
        matcher.matches();
        String day = matcher.group("dayGroup");
        String month = matcher.group("monthGroup");
        String year = matcher.group("yearGroup");

        if (month.length() == 3) {
            return LocalDate.parse(day + "/" + month + "/" + year, DateTimeFormatter.ofPattern("d/MMM/yyyy"));
        }
        return LocalDate.parse(day + "/" + month + "/" + year, DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    /**
     * Returns true if an input string satisfies the required format and time
     * period, false otherwise.
     */
    public static boolean isValidDateTime(String dateString) {
        for (String regex : REGEX_DATES) {
            if (dateString.toLowerCase().matches(regex)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if an input date string follows any of the allowed formats.
     */
    public static boolean isValidFormat(String dateString) {
        for (String regex : REGEX_FORMAT) {
            if (dateString.matches(regex)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a LocalDate from a given date time string.
     */
    public static LocalDate parseDate(String dateString) {
        Pattern pattern = Pattern.compile("");
        for (String regex : REGEX_DATES) {
            if (dateString.toLowerCase().matches(regex)) {
                pattern = Pattern.compile(regex);
            }
        }
        Matcher matcher = pattern.matcher(dateString.toLowerCase());
        matcher.matches();
        String date = matcher.group("dateGroup");
        return generateLocalDate(date);
    }

    /**
     * Returns an Optional containing a LocalTime object if a
     * time is present in a given date time string. Otherwise,
     * return a empty Optional.
     */
    public static Optional<LocalTime> parseTime(String dateString) {
        Pattern pattern = Pattern.compile("");
        for (String regex : REGEX_DATES) {
            if (dateString.toLowerCase().matches(regex)) {
                pattern = Pattern.compile(regex);
            }
        }
        Matcher matcher = pattern.matcher(dateString.toLowerCase());
        matcher.matches();
        String time = matcher.group("timeGroup");
        if (time == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(generateLocalTime(time));
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern(RECOMMENDED_FORMAT))
                + time.map(t -> " " + t).orElse("");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && date.equals(((DateTime) other).date) // state check
                && time.equals(((DateTime) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode() ^ time.hashCode();
    }

}
