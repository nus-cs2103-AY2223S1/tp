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
            "Cost should be written as HHmm in 24H format. For example, 3:25pm is 1525.";
    private static final String timePattern = "HHmm";
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timePattern);
    private final LocalTime startTime;

    /**
     * Constructs a {@code StartTime}.
     *
     * @param startTime A valid start time.
     */
    public StartTime(String startTime) {
        requireNonNull(startTime);
        checkArgument(isValidCost(startTime), MESSAGE_CONSTRAINTS);
        this.startTime = LocalTime.parse(startTime, timeFormatter);
    }

    /**
     * Returns true if a given string is a valid Cost
     */
    public static boolean isValidCost(String test) {
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
