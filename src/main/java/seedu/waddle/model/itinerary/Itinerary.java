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
    private final Date endDate;
    private final People people;

    private UniqueItemList uniqueItemList;

    /**
     * Every field must be present and not null.
     */

    // TODO
    // Have to ensure startDate is < endDate
    public Itinerary(Name name, Country country, Date startDate, Date endDate, People people) {
        requireAllNonNull(name, country, startDate, endDate, people);
        this.name = name;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
        this.people = people;
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

    public Date getEndDate() {
        return endDate;
    }

    public People getPeople() {
        return people;
    }

    public UniqueItemList getItemList() {
        return uniqueItemList;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
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

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
                && otherItinerary.getEndDate().equals(getEndDate())
                && otherItinerary.getPeople().equals(getPeople());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, country, startDate, endDate, people);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Country: ")
                .append(getCountry())
                .append("; Start Date: ")
                .append(getStartDate())
                .append("; End Date: ")
                .append(getEndDate())
                .append("; Number of people: ")
                .append(getPeople());

        return builder.toString();
    }

}
