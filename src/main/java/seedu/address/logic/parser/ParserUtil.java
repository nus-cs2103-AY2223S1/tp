package seedu.address.logic.parser;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.MissingPrefixesException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.datetime.Datetime;
import seedu.address.model.datetime.DatetimeRange;
import seedu.address.model.datetime.WeeklyTimeslot;

import static java.util.Objects.requireNonNull;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String WEEKLY_TIMESLOT_CONSTRAINTS_TIMES =
            "Times should be in HH:mm-HH:mm format, e.g. 08:00-09:00";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

     /**
     * Parses a {@code String deadline} into a {@code ReminderDeadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ReminderDeadline} is invalid.
     */
    public static Datetime parseDatetime(String datetime) throws ParseException {
        requireNonNull(datetime);
        String trimmedDeadline = datetime.trim();
        if (!Datetime.isValidDatetime(trimmedDeadline)) {
            throw new ParseException(Datetime.MESSAGE_CONSTRAINTS);
        }
        return new Datetime(trimmedDeadline);
    }

    /**
     * Parses a {@code String deadline} into a {@code ReminderDeadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ReminderDeadline} is invalid.
     */
    public static DatetimeRange parseDatetimeRange(String datetimeRangeString) throws ParseException {
        requireNonNull(datetimeRangeString);
        String[] times = datetimeRangeString.trim().split("-");
        if (times.length != 2) {
            throw new ParseException(WEEKLY_TIMESLOT_CONSTRAINTS_TIMES);
        }
        String startTime = times[0];
        String endTime = times[1];
        if (!DatetimeRange.isValidDatetimeRange(startTime.trim(), endTime.trim())) {
            throw new ParseException(DatetimeRange.MESSAGE_CONSTRAINTS);
        }
        return new DatetimeRange(startTime, endTime);
    }


    /**
     * Parses a {@code String timeslot} into a {@code TutorialTimeslot}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timeslot} is invalid.
     */
    public static WeeklyTimeslot parseWeeklyTimeslot(String day, String timeslot) throws ParseException {
        requireNonNull(day);
        requireNonNull(timeslot);
        String[] times = timeslot.trim().split("-");
        if (times.length != 2) {
            throw new ParseException(WEEKLY_TIMESLOT_CONSTRAINTS_TIMES);
        }
        String startTime = times[0];
        String endTime = times[1];
        if (!WeeklyTimeslot.isValidDay(day)) {
            throw new ParseException(WeeklyTimeslot.MESSAGE_CONSTRAINTS_DAY);
        }
        if (!WeeklyTimeslot.isValidTimeRange(startTime, endTime)) {
            throw new ParseException(WEEKLY_TIMESLOT_CONSTRAINTS_TIMES);
        }
        return new WeeklyTimeslot(day, startTime, endTime);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static void assertPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes)
            throws MissingPrefixesException {
        Prefix[] missingPrefixes = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isEmpty())
                .map(prefix -> new Prefix(prefix.getPrefix()))
                .toArray(Prefix[]::new);

        if (missingPrefixes.length != 0) {
            throw new MissingPrefixesException(missingPrefixes);
        }
    }

}
