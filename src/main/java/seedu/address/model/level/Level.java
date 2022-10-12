package seedu.address.model.level;

import seedu.address.model.level.exceptions.InvalidLevelException;

/**
 * Represents the level of education.
 */
public enum Level {
    PRIMARY1("primary1"), PRIMARY2("primary2"), PRIMARY3("primary3"), PRIMARY4("primary4"), PRIMARY5("primary5"),
    PRIMARY6("primary6"), SECONDARY1("secondary1"), SECONDARY2("secondary2"), SECONDARY3("secondary3"),
    SECONDARY4("secondary4");

    public static final String MESSAGE_CONSTRAINTS =
            "Levels should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "(?i)primary[1-6]|secondary[1-4]";
    public final String level;

    Level(String level) {
        this.level = level;
    }

    /**
     * Creates a Level object depending on the inputLevel.
     *
     * @param inputLevel A string representing the level of education.
     * @return A Level object with the respective enum value.
     * @throws InvalidLevelException if the inputLevel does not match any of the enum values.
     */
    public static Level createLevel(String inputLevel) throws InvalidLevelException {
        for (Level level : Level.values()) {
            if (inputLevel.toUpperCase().equals(level.name())) {
                return level;
            }
        }
        throw new InvalidLevelException(); //change to return null?
    }

    //    /**
    //     * Returns true if a given string is a valid value in enum Level
    //     */
    //    public static boolean isValidLevel(String level) {
    //        for (Level l : Level.values()) {
    //            if (l.name().equals(level)) {
    //                return true;
    //            }
    //        }
    //        return false;
    //    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1, name().length() - 1).toLowerCase()
                + " " + name().charAt(name().length() - 1);
    }

    /**
     * Returns true if a given string is a valid value in enum Level
     */
    public static boolean isValidLevel(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
