package seedu.address.model.person.student;

import seedu.address.model.person.student.exceptions.InvalidLevelException;

public enum Level {
    PRIMARY1, PRIMARY2, PRIMARY3, PRIMARY4, PRIMARY5, PRIMARY6, SECONDARY1, SECONDARY2, SECONDARY3, SECONDARY4;

    public static final String MESSAGE_CONSTRAINTS =
            "Levels should only contain alphanumeric characters and spaces, and it should not be blank";

    public static Level createLevel(String inputLevel) throws InvalidLevelException {
        for (Level level : Level.values()) {
            if (inputLevel.toUpperCase().equals(level.name())) {
                return level;
            }
        }
        throw new InvalidLevelException();
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1, name().length() - 1).toLowerCase()
                + " " + name().charAt(name().length() - 1);
    }
}
