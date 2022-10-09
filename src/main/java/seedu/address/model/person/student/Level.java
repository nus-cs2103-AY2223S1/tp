package seedu.address.model.person.student;

import seedu.address.model.person.student.exceptions.InvalidLevelException;

/**
 * Represents the education level of a student.
 */
public enum Level {
    PRIMARY1, PRIMARY2, PRIMARY3, PRIMARY4, PRIMARY5, PRIMARY6, SECONDARY1, SECONDARY2, SECONDARY3, SECONDARY4;

    public static final String MESSAGE_CONSTRAINTS =
            "Levels should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * Creates a Level object depending on the inputLevel.
     *
     * @param inputLevel A string representing the education level of the student.
     * @return A Level object with the respective enum value.
     * @throws InvalidLevelException if the inputLevel does not match any of the enum values.
     */
    public static Level createLevel(String inputLevel) throws InvalidLevelException {
        for (Level level : Level.values()) {
            if (inputLevel.toUpperCase().equals(level.name())) {
                return level;
            }
        }
        throw new InvalidLevelException();
    }

    /**
     * Returns true if a given string is a valid value in enum Level
     */
    public static boolean isValidLevel(String level) {
        for (Level l : Level.values()) {
            if (l.name().equals(level)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1, name().length() - 1).toLowerCase()
                + " " + name().charAt(name().length() - 1);
    }
}
