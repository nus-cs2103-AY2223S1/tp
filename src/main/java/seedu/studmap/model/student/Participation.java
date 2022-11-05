package seedu.studmap.model.student;

import static seedu.studmap.commons.util.AppUtil.checkArgument;
import static seedu.studmap.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Participation object in StudMap.
 * Guarantees: immutable; name is valid as declared in {@link #isValidParticipationName(String)}
 */
public class Participation {

    public static final String MESSAGE_CONSTRAINTS = "Participation component should consist of "
            + "alphanumerics, space, dash and underscore.";
    public static final String VALIDATION_REGEX = "[\\p{Alnum} \\-_]+";
    public static final String PARTICIPATION_TRUE = "Participated";
    public static final String PARTICIPATION_FALSE = "Did not participate";

    public final String participationComponent;
    public final boolean hasParticipated;

    /**
     * Constructs an {@code Participation} object.
     *
     * @param participationComponent A valid participation component name.
     * @param hasParticipated A boolean representing whether the student has participated or not
     */
    public Participation(String participationComponent, Boolean hasParticipated) {
        requireAllNonNull(participationComponent, hasParticipated);
        checkArgument(isValidParticipationName(participationComponent), MESSAGE_CONSTRAINTS);
        this.participationComponent = participationComponent;
        this.hasParticipated = hasParticipated;
    }

    /**
     * Returns true if a given string is a valid participation component name.
     */
    public static boolean isValidParticipationName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getString() {
        return participationComponent + ':' + getParticipationString();
    }

    public String getParticipationString() {
        return (hasParticipated ? PARTICIPATION_TRUE : PARTICIPATION_FALSE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Participation // instanceof handles nulls
                && participationComponent.equals(((Participation) other).participationComponent)); // state check
                // no check for hasParticipated to ensure only one participation record per participationComponent
    }

    @Override
    public int hashCode() {
        return participationComponent.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + getString() + ']';
    }

}
