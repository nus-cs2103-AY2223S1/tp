package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an Event's start or end datetime in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {

    public static final String RECOMMENDED_DATE_FORMAT = "dd/MM/yyyy";
    public static final String RECOMMENDED_TIME_FORMAT = "HH:mm";
    public static final String MESSAGE_CONSTRAINTS =
            String.format("Dates should follow a valid format. Try %s %s.",
                    RECOMMENDED_DATE_FORMAT, RECOMMENDED_TIME_FORMAT);
    public static final String MESSAGE_INVALID_DATETIME =
            String.format("The values for date and time are invalid! Try %s %s.",
                    RECOMMENDED_DATE_FORMAT, RECOMMENDED_TIME_FORMAT);

    public static final String REGEX_YEAR = "(?<yearGroup>\\d{4})";
    public static final String REGEX_MONTH = "(?<monthGroup>" + "(\\d{1,2})" + "|" + "[A-Za-z]{3,}" + ")";
    public static final String REGEX_DAY = "(?<dayGroup>\\d{1,2})";
    public static final String REGEX_SECONDS = "(?<secondsGroup>\\d{2})";
    public static final String REGEX_MINUTES = "(?<minutesGroup>\\d{2})";
    public static final String REGEX_HOURS = "(?<hoursGroup>\\d{2})";
    public static final String REGEX_TIME_COLON =
            "(" + REGEX_HOURS + ":" + REGEX_MINUTES + "(:" + REGEX_SECONDS + ")?)";
    public static final String REGEX_TIME_NO_SPACE =
            "(" + REGEX_HOURS + REGEX_MINUTES + "(" + REGEX_SECONDS + ")?)";

    public static final HashSet<String> REGEX_DATES = new LinkedHashSet<>() {
        {
            add("((?<dateGroup>" + REGEX_DAY + "\\s" + REGEX_MONTH + "(\\s" + REGEX_YEAR + ")?))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME_COLON + "))?");
            add("((?<dateGroup>" + REGEX_DAY + "\\s" + REGEX_MONTH + "(\\s" + REGEX_YEAR + ")?))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME_NO_SPACE + "))?");
            add("((?<dateGroup>" + REGEX_YEAR + "\\s" + REGEX_MONTH + "\\s" + REGEX_DAY + "))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME_COLON + "))?");
            add("((?<dateGroup>" + REGEX_YEAR + "\\s" + REGEX_MONTH + "\\s" + REGEX_DAY + "))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME_NO_SPACE + "))?");
            add("((?<dateGroup>" + REGEX_DAY + "-" + REGEX_MONTH + "(-" + REGEX_YEAR + ")?))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME_COLON + "))?");
            add("((?<dateGroup>" + REGEX_DAY + "-" + REGEX_MONTH + "(-" + REGEX_YEAR + ")?))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME_NO_SPACE + "))?");
            add("((?<dateGroup>" + REGEX_YEAR + "-" + REGEX_MONTH + "-" + REGEX_DAY + "))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME_COLON + "))?");
            add("((?<dateGroup>" + REGEX_YEAR + "-" + REGEX_MONTH + "-" + REGEX_DAY + "))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME_NO_SPACE + "))?");
            add("((?<dateGroup>" + REGEX_DAY + "/" + REGEX_MONTH + "(/" + REGEX_YEAR + ")?))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME_COLON + "))?");
            add("((?<dateGroup>" + REGEX_DAY + "/" + REGEX_MONTH + "(/" + REGEX_YEAR + ")?))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME_NO_SPACE + "))?");
            add("((?<dateGroup>" + REGEX_YEAR + "/" + REGEX_MONTH + "/" + REGEX_DAY + "))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME_COLON + "))?");
            add("((?<dateGroup>" + REGEX_YEAR + "/" + REGEX_MONTH + "/" + REGEX_DAY + "))"
                    + "(\\s(?<timeGroup>" + REGEX_TIME_NO_SPACE + "))?");
        }
    };

    public final LocalDate date;
    public final Optional<LocalTime> time;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid datetime string.
     */
    public DateTime(String dateTime) throws DateTimeParseException {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.date = parseDate(dateTime);
        this.time = parseTime(dateTime);
    }

    /**
     * Returns a LocalTime object for an input time in the valid formats.
     */
    private static LocalTime generateLocalTime(String hours, String minutes,
                                               String seconds) throws DateTimeParseException {
        if (seconds == null) {
            return LocalTime.parse(hours + ":" + minutes, DateTimeFormatter.ofPattern("HH:mm"));
        }
        return LocalTime.parse(hours + ":" + minutes + ":" + seconds,
                DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    /**
     * Returns a LocalDate object for an input date in the valid formats.
     */
    private static LocalDate generateLocalDate(String year, String month, String day) throws DateTimeParseException {
        String formatter = "d/";
        if (month.length() >= 3) {
            month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
            if (month.length() == 3) {
                formatter += "MMM/";
            } else {
                formatter += "MMMM/";
            }
        } else {
            formatter += "M/";
        }
        formatter += "uuuu";
        if (year == null) {
            year = String.valueOf(LocalDate.now().getYear());
        }
        if (year.equals("0000")) {
            // do not parse 0000
            throw new DateTimeParseException("0000 is not a valid year!", day + month + year,
                    day.length() + month.length());
        }

        return LocalDate.parse(day + "/" + month + "/" + year,
                DateTimeFormatter.ofPattern(formatter).withResolverStyle(ResolverStyle.STRICT));
    }

    /**
     * Returns true if an input string satisfies the required format. Does not
     * validate the values.
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
     * Returns a LocalDate from a given date time string.
     */
    public static LocalDate parseDate(String dateString) throws DateTimeParseException {
        Pattern pattern = Pattern.compile("");
        for (String regex : REGEX_DATES) {
            if (dateString.toLowerCase().matches(regex)) {
                pattern = Pattern.compile(regex);
            }
        }
        Matcher matcher = pattern.matcher(dateString.toLowerCase());
        matcher.matches();
        return generateLocalDate(matcher.group("yearGroup"),
                matcher.group("monthGroup"), matcher.group("dayGroup"));
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

        String hours = matcher.group("hoursGroup");
        String minutes = matcher.group("minutesGroup");
        String seconds = matcher.group("secondsGroup");

        return Optional.ofNullable(generateLocalTime(hours, minutes, seconds));
    }

    /**
     * Returns true if a start DateTime is before or equal another DateTime.
     * Otherwise, returns false
     */
    public boolean isBeforeOrEqual(DateTime other) {
        if (this.time.isEmpty()) {
            return !this.date.isAfter(other.date);
        }
        return this.date.isEqual(other.date)
                ? !this.time.get().isAfter(other.time.get())
                : !this.date.isAfter(other.date);
    }

    public boolean hasTime() {
        return this.time.isPresent();
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern(RECOMMENDED_DATE_FORMAT))
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
