package eatwhere.foodguide.model.eatery;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import eatwhere.foodguide.commons.util.CollectionUtil;
import eatwhere.foodguide.model.tag.Tag;

/**
 * Represents an Eatery in the food guide.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Eatery {

    // Identity fields
    private final Name name;
    private final Price price;
    private final Cuisine cuisine;

    // Data fields
    private final Location location;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Eatery(Name name, Price price, Cuisine cuisine, Location location, Set<Tag> tags) {
        CollectionUtil.requireAllNonNull(name, price, cuisine, location, tags);
        this.name = name;
        this.price = price;
        this.cuisine = cuisine;
        this.location = location;
        this.tags.addAll(tags);
    }

    /**
     * Constructor for an eatery that does not require a price.
     */
    public Eatery(Name name, Cuisine cuisine, Location location, Set<Tag> tags) {
        CollectionUtil.requireAllNonNull(name, cuisine, location, tags);
        this.name = name;
        this.cuisine = cuisine;
        this.location = location;
        this.tags.addAll(tags);
        this.price = new Price();
    }

    public Name getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both eateries have the same name & location.
     * This defines a weaker notion of equality between two eateries,
     * used for duplicate detection when adding eateries to the Food Guide.
     */
    public boolean isSameEatery(Eatery otherEatery) {
        if (otherEatery == this) {
            return true;
        }

        return otherEatery != null
                && otherEatery.getName().similarTo(getName())
                && otherEatery.getLocation().similarTo(getLocation());
    }

    /**
     * Returns true if both eateries have the same identity and data fields.
     * This defines a stronger notion of equality between two eateries.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Eatery)) {
            return false;
        }

        Eatery otherEatery = (Eatery) other;
        return otherEatery.getName().equals(getName())
                && otherEatery.getPrice().equals(getPrice())
                && otherEatery.getCuisine().equals(getCuisine())
                && otherEatery.getLocation().equals(getLocation())
                && otherEatery.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, price, cuisine, location, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Price: ")
                .append(getPrice())
                .append("; Cuisine: ")
                .append(getCuisine())
                .append("; Location: ")
                .append(getLocation());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
