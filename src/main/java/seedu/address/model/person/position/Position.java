package seedu.address.model.person.position;

/**
 * Represents a Person's position in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPosition(String)}
 */
public abstract class Position {

    enum Positions {
        STUDENT,
        TA,
        PROFESSOR
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Positions can only be one of the following: Student, TA, Professor (non case-sensitive).";

    public static final String DETAILS_MESSAGE_CONSTRAINTS =
            "Details cannot be empty";
    public final String value;
    /**
     * Constructor for Position
     */
    public Position(String value) {

        this.value = value;
    }

    /**
     * Returns true if a given string is a valid position.
     */
    public static boolean isValidPosition(String test) {
        for (Positions position : Positions.values()) {
            if (position.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Class method that returns the type of position given by the value.
     * @param position A valid position.
     * @return The desired Position descendant
     */
    public static Position buildPosition(String position) {
        if (Positions.STUDENT.name().equalsIgnoreCase(position)) {
            return new Student();
        } else if (Positions.TA.name().equalsIgnoreCase(position)) {
            return new TeachingAssistant();
        } else if (Positions.PROFESSOR.name().equalsIgnoreCase(position)) {
            return new Professor();
        } else {
            return null;
        }
    }

    public abstract String toString();

    public abstract boolean equals(Object other);

    public abstract int hashcode();

    public abstract String getDetails();
    public abstract void setDetails(String details);
}
