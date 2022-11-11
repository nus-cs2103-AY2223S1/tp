package seedu.studmap.model.student;

import seedu.studmap.commons.exceptions.IllegalValueException;

/**
 * Represents an Assignment object in StudMap.
 * Guarantees: immutable; identifier is valid as declared in {@link #isValidAttributeIdentifier(String)} (String)}
 */
public class Assignment extends MultiStateAttribute<String, Assignment.Status> {

    public static final String MESSAGE_CONSTRAINTS = "Assignment names should consist of "
            + "alphanumerics, space, dash and underscore.";
    public static final String VALIDATION_REGEX = "[\\p{Alnum} \\-_]+";
    public static final String ASSIGNMENT_MARKED = "Marked";
    public static final String ASSIGNMENT_RECEIVED = "Received";
    public static final String ASSIGNMENT_NEW = "New";
    public static final String MESSAGE_STATUS_CONSTRAINTS = "Assignment marking status should be one of "
            + ASSIGNMENT_NEW + ", " + ASSIGNMENT_RECEIVED + " or " + ASSIGNMENT_MARKED + ".";


    /**
     * Constructs an {@code Assignment} object.
     *
     * @param identifier A valid assignment name.
     * @param state      A status representing whether the assignment has been marked
     */
    public Assignment(String identifier, Status state) {
        super(identifier, state);
    }

    public static boolean isValidAssignmentName(String assignmentName) {
        return assignmentName != null && assignmentName.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the given string is a valid marking status.
     */
    public static boolean isValidMarkingStatus(String test) {
        return test.equals(ASSIGNMENT_MARKED)
                || test.equals(ASSIGNMENT_RECEIVED)
                || test.equals(ASSIGNMENT_NEW);
    }

    @Override
    public boolean isValidAttributeIdentifier(String identifier) {
        return isValidAssignmentName(identifier);
    }

    @Override
    public String getIdentifierConstraintsMessage() {
        return MESSAGE_CONSTRAINTS;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && identifier.equalsIgnoreCase(((Assignment) other).identifier)); // identifier check
    }

    @Override
    public int hashCode() {
        return identifier.toUpperCase().hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + getString() + ']';
    }

    /**
     * Possible states of an Assignment.
     */
    public enum Status {
        MARKED {
            @Override
            public String toString() {
                return ASSIGNMENT_MARKED;
            }
        },
        RECEIVED {
            @Override
            public String toString() {
                return ASSIGNMENT_RECEIVED;
            }
        },
        NEW {
            @Override
            public String toString() {
                return ASSIGNMENT_NEW;
            }
        };

        /**
         * Translates a string value of {@code Status} to an enum value.
         */
        public static Status fromString(String value) throws IllegalValueException {
            switch (value) {
            case ASSIGNMENT_MARKED:
                return MARKED;
            case ASSIGNMENT_RECEIVED:
                return RECEIVED;
            case ASSIGNMENT_NEW:
                return NEW;
            default:
                throw new IllegalValueException(MESSAGE_STATUS_CONSTRAINTS);
            }
        }
    }

}
