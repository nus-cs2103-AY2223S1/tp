package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.Calorie;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Food {

    // Identity fields
    private final Name name;
    private final Calorie calorie;
    private final Set<Tag> tags = new HashSet<>();
    private final DateTime dateTime;

    /**
     * Every field must be present and not null.
     */
    public Food(Name name, Calorie calorie, Set<Tag> tags) {
        requireAllNonNull(name, calorie, tags);
        this.name = name;
        this.calorie = calorie;
        this.tags.addAll(tags);
        this.dateTime = new DateTime();
    }

    /**
     * Constructor for an edited Food.
     */
    public Food(Name name, Calorie calorie, Set<Tag> tags, DateTime dateTime) {
        requireAllNonNull(name, calorie, tags, dateTime);
        this.name = name;
        this.calorie = calorie;
        this.tags.addAll(tags);
        this.dateTime = dateTime;
    }

    public Name getName() {
        return name;
    }

    public Calorie getCalorie() {
        return calorie;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Food otherFood) {
        if (otherFood == this) {
            return true;
        }

        return otherFood != null
                && otherFood.getName().equals(getName());
    }

    private boolean hasTag(String tagName) {
        return tags.stream().anyMatch(tag -> tag.tagName.equals(tagName));
    }

    /**
     * Returns a String representing the earliest meal tag on this {@code Food}.
     * @return "B" for breakfast, "L" for lunch, "D" for dinner and "X" for no tag.
     */
    public String getEarliestMealTag() {
        if (hasTag("breakfast")) {
            return "B";
        } else if (hasTag("lunch")) {
            return "L";
        } else if (hasTag("dinner")) {
            return "D";
        } else {
            return "X";
        }
    }

    /**
     * Returns true if this {@code Food} was recorded after the given {@code Food}.
     * @param otherFood The other {@code Food} to compare to.
     * @return True if this {@code Food} was recorded after the given {@code Food}.
     */
    public boolean isAfter(Food otherFood) {
        return dateTime.isAfter(otherFood.getDateTime());
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

        if (!(other instanceof Food)) {
            return false;
        }

        Food otherFood = (Food) other;
        return otherFood.getName().equals(getName())
                && otherFood.getTags().equals(getTags())
                && otherFood.getCalorie().equals(getCalorie());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, calorie, tags, dateTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Calories: ")
                .append(getCalorie());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
