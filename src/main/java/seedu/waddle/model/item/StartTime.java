package seedu.waddle.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the start time of the item.
 */
public class StartTime {
    public static final String MESSAGE_CONSTRAINTS =
            "Start time should be written as HH:mm in 24H format. For example, 3:25pm is 15:25.";
    private static final String timePattern = "HH:mm";
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timePattern);
    private final LocalTime startTime;

    /**
     * Constructs a {@code StartTime}.
     *
     * @param startTime A valid start time.
     */
    public StartTime(String startTime) {
        requireNonNull(startTime);
        checkArgument(isValidStartTime(startTime), MESSAGE_CONSTRAINTS);
        this.startTime = LocalTime.parse(startTime, timeFormatter);
    }

    /**
     * Returns true if a given string is a valid Cost
     */
    public static boolean isValidStartTime(String test) {
        LocalTime time;
        try {
            time = LocalTime.parse(test, timeFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    @Override
    public String toString() {
        return this.startTime.toString();
    }
}
