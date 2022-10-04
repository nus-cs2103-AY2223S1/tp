package seedu.address.model.tutorial;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Tutorial in the ModQuik.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Tutorial {

    // Identity fields
    private final seedu.address.model.tutorial.TutorialName tutorialName;

    /**
     * Every field must be present and not null.
     */
    public Tutorial(seedu.address.model.tutorial.TutorialName tutorialName) {
        requireAllNonNull(tutorialName);
        this.tutorialName = tutorialName;
    }

    public TutorialName getName() {
        return tutorialName;
    }

    /**
     * Returns true if both tutorials have the same name.
     * This defines a weaker notion of equality between two tutorials.
     */
    public boolean isSameTutorial(seedu.address.model.tutorial.Tutorial otherTutorial) {
        if (otherTutorial == this) {
            return true;
        }

        return otherTutorial != null
                && otherTutorial.getName().equals(getName());
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

        if (!(other instanceof seedu.address.model.tutorial.Tutorial)) {
            return false;
        }

        seedu.address.model.tutorial.Tutorial otherPerson = (seedu.address.model.tutorial.Tutorial) other;
        return otherPerson.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(tutorialName);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        return builder.toString();
    }

}
