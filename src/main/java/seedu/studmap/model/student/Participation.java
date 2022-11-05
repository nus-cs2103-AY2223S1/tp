package seedu.studmap.model.student;

/**
 * Represents a Participation object in StudMap.
 * Guarantees: immutable; name is valid as declared in {@link #isValidParticipationName(String)}
 */
public class Participation extends MultiStateAttribute<String, Participation.Status> {

    public static final String MESSAGE_CONSTRAINTS = "Participation component should consist of "
            + "alphanumerics, space, dash and underscore.";
    public static final String VALIDATION_REGEX = "[\\p{Alnum} \\-_]+";
    public static final String PARTICIPATION_TRUE = "Participated";
    public static final String PARTICIPATION_FALSE = "Did not participate";
    public static final String MESSAGE_STATUS_CONSTRAINTS =
            "Status must be " + PARTICIPATION_TRUE + " or " + PARTICIPATION_FALSE + ".";

    /**
     * Constructs an {@code Participation} object.
     *
     * @param identifier A valid participation component name.
     * @param status     Participation status.
     */
    public Participation(String identifier, Participation.Status status) {
        super(identifier, status);
    }

    /**
     * Returns true if a given string is a valid participation component name.
     */
    public static boolean isValidParticipationName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean isSameAttribute(MultiStateAttribute<String, Status> other) {
        return other.identifier.equalsIgnoreCase(this.identifier);
    }

    @Override
    public boolean isValidAttributeIdentifier(String identifier) {
        return isValidParticipationName(identifier);
    }

    @Override
    public String getIdentifierConstraintsMessage() {
        return MESSAGE_STATUS_CONSTRAINTS;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Participation // instanceof handles nulls
                && identifier.equals(((Participation) other).identifier)); // state check
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + getString() + ']';
    }

    /**
     * Possible states of a Participation.
     */
    public enum Status {
        PARTICIPATED {
            @Override
            public String toString() {
                return PARTICIPATION_TRUE;
            }
        },
        NOT_PARTICIPATED {
            @Override
            public String toString() {
                return PARTICIPATION_FALSE;
            }
        };

        /**
         * Translates a string value of {@code Status} to an enum value.
         */
        public static Participation.Status fromString(String value) throws IllegalArgumentException {
            switch (value) {
            case PARTICIPATION_TRUE:
                return PARTICIPATED;
            case PARTICIPATION_FALSE:
                return NOT_PARTICIPATED;
            default:
                throw new IllegalArgumentException(MESSAGE_STATUS_CONSTRAINTS);
            }
        }
    }

}
