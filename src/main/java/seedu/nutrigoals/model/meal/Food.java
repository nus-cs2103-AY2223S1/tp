package seedu.nutrigoals.model.meal;

import static seedu.nutrigoals.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.tag.Tag;

/**
 * Represents a Food in the food list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Food {

    private static final String TAG_BREAKFAST = "B";
    private static final String TAG_LUNCH = "L";
    private static final String TAG_DINNER = "D";
    private static final String TAG_NONE = "X";

    // Identity fields
    private final Name name;
    private final Calorie calorie;
    private final Tag tag;
    private final DateTime dateTime;

    /**
     * Every field must be present and not null.
     */
    public Food(Name name, Calorie calorie, Tag tag) {
        requireAllNonNull(name, calorie, tag);
        this.name = name;
        this.calorie = calorie;
        this.tag = tag;
        this.dateTime = new DateTime();
    }

    /**
     * Constructor for an edited Food.
     */
    public Food(Name name, Calorie calorie, Tag tag, DateTime dateTime) {
        requireAllNonNull(name, calorie, tag, dateTime);
        this.name = name;
        this.calorie = calorie;
        this.tag = tag;
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
    public Tag getTag() {
        return tag;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns true if both Foods have the same name.
     * This defines a weaker notion of equality between two Foods.
     */
    public boolean isSameFood(Food otherFood) {
        if (otherFood == this) {
            return true;
        }

        return otherFood != null
                && otherFood.getName().equals(getName());
    }

    private boolean hasTag(String tagName) {
        return tag.tagName.equals(tagName);
    }

    /**
     * Returns a String representing the earliest meal tag on this {@code Food}.
     * @return "B" for breakfast, "L" for lunch, "D" for dinner and "X" for no tag.
     */
    public String getEarliestMealTag() {
        if (hasTag("breakfast")) {
            return TAG_BREAKFAST;
        } else if (hasTag("lunch")) {
            return TAG_LUNCH;
        } else if (hasTag("dinner")) {
            return TAG_DINNER;
        } else {
            return TAG_NONE;
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
     * Returns true if both Foods have the same identity and data fields.
     * This defines a stronger notion of equality between two Foods.
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
                && otherFood.getTag().equals(getTag())
                && otherFood.getCalorie().equals(getCalorie());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, calorie, tag, dateTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Calories: ")
                .append(getCalorie())
                .append("; Tag")
                .append(getTag());

        return builder.toString();
    }

}
