package gim.model.exercise;

import static gim.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import gim.model.date.Date;

/**
 * Represents an Exercise in the exercise tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Exercise implements Comparable<Exercise> {

    private final Name name;
    private final Weight weight;
    private final Sets sets;
    private final Reps reps;
    private final Date date;

    /**
     * Every field must be present and not null.
     */
    public Exercise(Name name, Weight weight, Sets sets, Reps reps, Date date) {
        requireAllNonNull(name, weight, sets, reps, date);
        this.name = name;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
        this.date = date;
    }

    public Name getName() {
        return name;
    }

    public Weight getWeight() {
        return weight;
    }

    public Sets getSets() {
        return sets;
    }

    public Reps getReps() {
        return reps;
    }

    /**
     * Returns an immutable Date object, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the String representation of the date object.
     * @return date String
     */
    public String getDateString() {
        return date.toString();
    }

    /**
     * Returns true if both Exercises have the same Name.
     * This defines a weaker notion of equality between two Exercises.
     *
     * Two Names are equal if, after removal of whitespaces and being set to lowercase, their String values are equal.
     */
    public boolean isSameExercise(Exercise otherExercise) {
        if (otherExercise == this) {
            return true;
        }

        return otherExercise != null
                && otherExercise.getName().equals(getName());
    }

    /**
     * Returns true if both Exercises have the same identity and data fields.
     * This defines a stronger notion of equality between two exercises.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Exercise)) {
            return false;
        }

        Exercise otherExercise = (Exercise) other;
        return otherExercise.getName().equals(getName())
                && otherExercise.getWeight().equals(getWeight())
                && otherExercise.getSets().equals(getSets())
                && otherExercise.getReps().equals(getReps())
                && otherExercise.getDate().equals(getDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, weight, sets, reps, date);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Weight: ")
                .append(getWeight())
                .append("kg")
                .append("; Sets: ")
                .append(getSets())
                .append("; Reps: ")
                .append(getReps());

        Date date = getDate();
        if (!(date == null)) {
            builder.append("; Date: ");
            builder.append(date);
        }
        return builder.toString();
    }

    @Override
    public int compareTo(Exercise o) {
        // Does not handle if both same weight yet
        return this.getWeight().compareTo(o.getWeight());
    }
}
