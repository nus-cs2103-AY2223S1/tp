package seedu.waddle.model.itinerary;

import static seedu.waddle.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.UniqueItemList;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Itinerary {

    // Details field
    private final Name name;
    private final Country country;
    private final Date startDate;
    private final ItineraryDuration duration;
    private final People people;
    private final Budget budget;

    private UniqueItemList uniqueItemList;

    /**
     * Every field must be present and not null.
     */

    // TODO
    // Have to ensure startDate is < endDate.
    public Itinerary(Name name, Country country, Date startDate, ItineraryDuration duration,
                     People people, Budget budget) {
        requireAllNonNull(name, startDate, duration);
        this.name = name;
        this.country = country;
        this.startDate = startDate;
        this.duration = duration;
        this.people = people;
        this.budget = budget;
        this.uniqueItemList = new UniqueItemList();
    }

    public Name getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public Date getStartDate() {
        return startDate;
    }

    public ItineraryDuration getDuration() {
        return this.duration;
    }

    /* TODO: implement method
    public Date getEndDate() {
        return duration.getEndFromStart(startDate);
    }
     */

    public People getPeople() {
        return people;
    }

    public Budget getBudget() {
        return this.budget;
    }

    public UniqueItemList getItemList() {
        return uniqueItemList;
    }

    /**
     * Returns true if both itineraries have the same name.
     * This defines a weaker notion of equality between two itineraries.
     */
    public boolean isSameItinerary(Itinerary otherItinerary) {
        if (otherItinerary == this) {
            return true;
        }

        return otherItinerary != null
                && otherItinerary.getName().equals(getName());
    }

    public boolean hasItem(Item item) {
        return this.uniqueItemList.contains(item);
    }

    public void addItem(Item item) {
        this.uniqueItemList.add(item);
    }

    public Item removeItem(int index) {
        return this.uniqueItemList.remove(index);
    }

    public void setItem(Item target, Item editedItem) {
        uniqueItemList.setItem(target, editedItem);
    }

    public int getItemSize() {
        return this.uniqueItemList.getSize();
    }

    /**
     * Returns true if both itineraries have the same identity and data fields.
     * This defines a stronger notion of equality between two itineraries.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Itinerary)) {
            return false;
        }

        Itinerary otherItinerary = (Itinerary) other;
        return otherItinerary.getName().equals(getName())
                && otherItinerary.getCountry().equals(getCountry())
                && otherItinerary.getStartDate().equals(getStartDate())
                && otherItinerary.getDuration().equals(getDuration())
                && otherItinerary.getPeople().equals(getPeople())
                && otherItinerary.getBudget().equals(getBudget());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, country, startDate, duration, people, budget);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Country: ")
                .append(getCountry())
                .append("; Start Date: ")
                .append(getStartDate())
                .append("; Duration: ")
                .append(getDuration())
                .append("; Number of people: ")
                .append(getPeople())
                .append("; Budget: ")
                .append(getBudget());

        return builder.toString();
    }

}
