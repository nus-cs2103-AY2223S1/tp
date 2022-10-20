package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Profile's start or end datetime in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {

    public static final String RECOMMENDED_FORMAT = "dd/MM/yyyy";
    public static final String RECOMMENDED_FORMAT_WITH_TIME = "dd/MM/yyyy HH:mm";
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should follow a valid format. Try " + RECOMMENDED_FORMAT;

    // Not an exhaustive list
    private static final Map<String, String> DATE_VALIDATION_REGEXS = new HashMap<>() {{
            put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
            put("^\\d{1,2}-\\d{1,2}-\\d{2}$", "dd-MM-yy");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
            put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "dd/MM/yyyy");
            put("^\\d{1,2}/\\d{1,2}/\\d{2}$", "dd/MM/yy");
            put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
            put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
            put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
        }};

    private static final Map<String, String> TIME_VALIDATION_REGEXS = new HashMap<>() {{
            put("^\\d{12}$", "yyyyMMddHHmm");
            put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
            put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
            put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "dd/MM/yyyy HH:mm");
            put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
            put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
            put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
            put("^\\d{14}$", "yyyyMMddHHmmss");
            put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
            put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
            put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd/MM/yyyy HH:mm:ss");
            put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
            put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
            put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
        }};

    private static final Map<String, String> TIME_REGEXES = new HashMap<>() {{
        put("^\\d{1,2}:\\d{2}$", "HH:mm");
        put("^\\d{1,2}:\\d{2}:\\d{2}$", "HH:mm:ss");
        put("^\\d{1,2}\\d{2}$", "HHmm");
        put("^\\d{1,2}\\d{2}\\d{2}$", "HHmmss");
    }};

    public static final String REGEX_DAY_SLASH = "(([1-9]|[0-2][0-9]|(3)[0-1])/(((0)?[0-9])|((1)[0-2]))/[0-9]{4})";
    public static final String REGEX_DAY_SPACE = "(([1-9]|[0-2][0-9]|(3)[0-1])\\s(((0)?[0-9])|((1)[0-2]))\\s[0-9]{4})";
    public static final String REGEX_DAY_DASH = "(([1-9]|[0-2][0-9]|(3)[0-1])-(((0)?[0-9])|((1)[0-2]))-[0-9]{4})";
    public static final String REGEX_TIME_COLON = "(([01][0-9]|2[0-3]):([0-5][0-9])(:[0-5]?[0-9])?)";
    public static final String REGEX_TIME_NO_SPACE = "(([01][0-9]|2[0-3])([0-5][0-9])([0-5]?[0-9])?)";
    public static final String MONTHS_REGEX =
            "((jan)|(feb)|(mar)|(apr)|(may)|(jun)|(jul)|(aug)|(sep)|(oct)|(nov)|(dec))";
    public static final String REGEX_DAY_LETTER =
            "(([1-9]|[0-2][0-9]|(3)[0-1])\\s" + MONTHS_REGEX + "\\s[0-9]{4})";
    public static final String REGEX_YEAR_SLASH =
            "([0-9]{4}/(((0)?[0-9])|((1)[0-2]))/([1-9]|[0-2][0-9]|(3)[0-1]))";
    public static final String REGEX_YEAR_DASH =
            "([0-9]{4}-(((0)?[0-9])|((1)[0-2]))-([1-9]|[0-2][0-9]|(3)[0-1]))";

    public static Pattern VALIDATION_PATTERN = Pattern.compile(
            "(?<dateGroup>" + REGEX_DAY_SLASH + "|" + REGEX_DAY_SPACE + "|" + REGEX_DAY_DASH + "|"
                    + REGEX_DAY_LETTER + "|" + REGEX_YEAR_SLASH + "|" + REGEX_YEAR_DASH +  ")"
                    + "(\\s(?<timeGroup>" + REGEX_TIME_COLON + "|" + REGEX_TIME_NO_SPACE + "))?"
    );
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
     * Returns true if given string follows a valid datetime pattern.
     */
    public static String determineDateFormat(String dateString, Map<String, String> regexSet) {
        for (String regexp : regexSet.keySet()) {
            if (dateString.toLowerCase().matches(regexp)) {
                return regexSet.get(regexp);
            }
        }
        return null;
    }

    public static boolean isValidDateTime(String dateString) {
        Matcher matcher = VALIDATION_PATTERN.matcher(dateString.toLowerCase());
        return matcher.matches();
    }

    public static LocalDate parseDate(String dateString) {
        Matcher matcher = VALIDATION_PATTERN.matcher(dateString.toLowerCase());
        matcher.matches();
        String date = matcher.group("dateGroup");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(
                requireNonNull(determineDateFormat(date, DATE_VALIDATION_REGEXS)));
        return LocalDate.parse(date, dateFormat);
    }

    public static Optional<LocalTime> parseTime(String dateString) {
        Matcher matcher = VALIDATION_PATTERN.matcher(dateString.toLowerCase());
        matcher.matches();
        String time = matcher.group("timeGroup");
        if (time == null) {
            return Optional.empty();
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(
                requireNonNull(determineDateFormat(time, TIME_REGEXES)));
        return Optional.ofNullable(LocalTime.parse(time, dateFormat));
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
