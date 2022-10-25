package seedu.waddle.model.item;

import static seedu.waddle.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;

/**
 * Represents an item in the itinerary.
 */
public class Item {
    private final String description;
    private final Priority priority;
    private final Cost cost;
    private final Duration duration;
    private LocalTime startTime;
    // private Category category;

    /**
     * Constructor for an item.
     *
     * @param description description of the item
     */
    public Item(String description, Priority priority, Cost cost, Duration duration) {
        requireAllNonNull(description, priority);
        this.description = description;
        this.priority = priority;
        this.cost = cost;
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public Cost getCost() {
        return this.cost;
    }

    public Duration getDuration() {
        return duration;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return this.startTime.plusMinutes(this.duration.getDuration());
    }

    public String getTimeString() {
        if (this.startTime != null) {
            if (this.duration != null) {
                return this.startTime + " - " + getEndTime();
            } else {
                return this.startTime.toString();
            }
        }
        return "(Not planned)";
    }

    public void resetStartTime() {
        this.startTime = null;
    }

    /**
     * Returns true if both items have the same description.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
                && otherItem.getDescription().equals(getDescription());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("; Priority: ")
                .append(getPriority())
                .append("; Cost: ")
                .append(getCost())
                .append("; Duration: ")
                .append(getDuration());
        return builder.toString();
    }
}
