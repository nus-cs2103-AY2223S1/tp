package seedu.waddle.testutil;

import java.time.LocalTime;

import seedu.waddle.model.item.Cost;
import seedu.waddle.model.item.Duration;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.Priority;
import seedu.waddle.model.item.StartTime;
import seedu.waddle.model.itinerary.Budget;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.ItineraryDuration;
import seedu.waddle.model.itinerary.People;

/**
 * A utility class to help with building Item objects.
 */
public class ItemBuilder {
    public static final String DEFAULT_ITEM_DESC = "Airport";
    public static final String DEFAULT_DURATION = "60";
    public static final String DEFAULT_COST = "100";
    public static final int DEFAULT_PRIORITY = 5;

    private Description description;
    private Duration duration;
    private Cost cost;
    private Priority priority;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemBuilder() {
        this.description = new Description(DEFAULT_ITEM_DESC);
        this.duration = new Duration(DEFAULT_DURATION);
        this.cost = new Cost(DEFAULT_COST);
        this.priority = new Priority(DEFAULT_PRIORITY);
    }

    /**
     * Initializes the ItemBuilder with the data of {@code itemToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        description = itemToCopy.getDescription();
        duration = itemToCopy.getDuration();
        cost = itemToCopy.getCost();
        priority = itemToCopy.getPriority();
    }

    /**
     * Sets the {@code description} of the {@code Item} that we are building.
     */
    public ItemBuilder withDesc(String desc) {
        this.description = new Description(desc);
        return this;
    }

    /**
     * Sets the {@code duration} of the {@code Item} that we are building.
     */
    public ItemBuilder withDuration(String duration) {
        this.duration = new Duration(duration);
        return this;
    }

    /**
     * Sets the {@code cost} of the {@code Item} that we are building.
     */
    public ItemBuilder withCost(String cost) {
        this.cost = new Cost(cost);
        return this;
    }

    /**
     * Sets the {@code priority} of the {@code Item} that we are building.
     */
    public ItemBuilder withPriority(int priority) {
        this.priority = new Priority(priority);
        return this;
    }

    public Item build() {
        return new Item(description, priority, cost, duration);
    }
}
