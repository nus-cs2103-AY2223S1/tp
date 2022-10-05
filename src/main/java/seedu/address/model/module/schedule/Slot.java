package seedu.address.model.module.schedule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a slot in the schedule
 */
public class Slot {
    private final Venue venue;
    private final Weekdays weekday;
    private final String startTime;
    private final String endTime;

    /**
     * Requires all fields to be not null
     * @param venue venue
     * @param weekday weekday
     * @param startTime start time
     * @param endTime end time
     */
    public Slot(Venue venue, Weekdays weekday, String startTime, String endTime) {
        requireAllNonNull(venue, weekday, startTime, endTime);
        this.venue = venue;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return startTime + " - " + endTime + "\n" + venue;
    }

    public Weekdays getWeekday() {
        return weekday;
    }

    public String getPeriod() {
        return startTime + " - " + endTime;
    }

    /**
     * Checks if two slots are overlapping
     * @param newSlot another slot
     * @return overlaps or not
     */
    public boolean isOverlap(Slot newSlot) {
        if (newSlot.startTime.compareTo(this.endTime) > 0
                || newSlot.endTime.compareTo(this.startTime) < 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Slot)) {
            return false;
        }
        Slot otherSlot = (Slot) other;
        return otherSlot.venue.equals(this.venue)
                && otherSlot.weekday.equals(this.weekday)
                && otherSlot.startTime.equals(this.startTime)
                && otherSlot.endTime.equals(this.endTime);
    }
}
