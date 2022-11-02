package seedu.address.model.tutorial;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.commons.ModuleCode;
import seedu.address.model.commons.Venue;
import seedu.address.model.datetime.WeeklyTimeslot;

/**
 * Represents a Tutorial in ModQuik.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Tutorial {

    // Identity fields
    private final TutorialName name;
    private final ModuleCode module;
    private final Venue venue;
    // Data fields
    private final WeeklyTimeslot timeslot;

    /**
     * Every field must be present and not null.
     */
    public Tutorial(TutorialName name, ModuleCode module, Venue venue,
                    WeeklyTimeslot timeslot) {
        requireAllNonNull(name, module, venue, timeslot);
        this.name = name;
        this.module = module;
        this.venue = venue;
        this.timeslot = timeslot;
    }

    public TutorialName getName() {
        return name;
    }

    public ModuleCode getModule() {
        return module;
    }

    public Venue getVenue() {
        return venue;
    }

    public WeeklyTimeslot getTimeslot() {
        return timeslot;
    }

    /**
     * Returns true if both tutorials have the same name.
     * This defines a weaker notion of equality between two tutorials.
     */
    public boolean isSameTutorial(Tutorial otherTutorial) {
        if (otherTutorial == this) {
            return true;
        }

        return otherTutorial != null
                && otherTutorial.getName().equals(getName())
                && otherTutorial.getModule().equals(getModule());
    }

    /**
     * Returns true if given tutorial's timeslot overlaps with other's timeslot.
     */
    public boolean isClashTutorial(Tutorial otherTutorial) {
        if (otherTutorial == this) {
            return true;
        }

        return otherTutorial != null
                && otherTutorial.getTimeslot().isOverlapping(getTimeslot());
    }

    /**
     * Returns true if both tutorials have the same identity and data fields.
     * This defines a stronger notion of equality between two tutorials.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tutorial)) {
            return false;
        }

        Tutorial otherTutorial = (Tutorial) other;
        return otherTutorial.getName().equals(getName())
                && otherTutorial.getModule().equals(getModule())
                && otherTutorial.getVenue().equals(getVenue())
                && otherTutorial.getTimeslot().equals(getTimeslot());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, module, venue, timeslot);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Module: ")
                .append(getModule())
                .append("; Venue: ")
                .append(getVenue())
                .append("; Timeslot: ")
                .append(getTimeslot());

        return builder.toString();
    }

}
