package seedu.waddle.model.item.exceptions;

import java.time.LocalTime;

/**
 * This class encapsulates a time period.
 */
public class Period {
    private final LocalTime start;
    private final LocalTime end;

    /**
     * Constructor.
     *
     * @param start Start time.
     * @param end   End time.
     */
    public Period(LocalTime start, LocalTime end) {
        //assert(end.isAfter(start) || start.equals(end)) : "start and end time must be valid";
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return this.start;
    }

    public LocalTime getEnd() {
        return this.end;
    }

    public String getStartString() {
        return this.start.toString();
    }

    public String getEndString() {
        if (this.end.equals(LocalTime.MAX)) {
            return LocalTime.MIDNIGHT.toString() + " (next day)";
        }
        return this.end.toString();
    }
}
