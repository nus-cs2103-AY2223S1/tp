package seedu.waddle.model.item.exceptions;

import java.time.LocalTime;

/**
 * This class encapsulates a time period.
 */
public class Period {
    private LocalTime start;
    private LocalTime end;

    /**
     * Constructor.
     *
     * @param start Start time.
     * @param end End time.
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
}
