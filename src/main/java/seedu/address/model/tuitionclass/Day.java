package seedu.address.model.tuitionclass;

import seedu.address.model.tuitionclass.exceptions.InvalidDayException;

// Solution below adapted from:
// https://github.com/dextertanyj/tp/blob/master/src/main/java/tutorspet/model/lesson/Day.java
/**
 * Represents the day of the week that the tuition class is held on.
 */
public enum Day {
    MONDAY("monday"), TUESDAY("tuesday"), WEDNESDAY("wednesday"), THURSDAY("thursday"), FRIDAY("friday"),
    SATURDAY("saturday"), SUNDAY("sunday");

    public static final String MESSAGE_CONSTRAINTS = "Day should be a valid day of the week.\n";
    public static final String MESSAGE_DID_YOU_MEAN_MONDAY =
            "Did you mean \"monday\"? ";
    public static final String MESSAGE_DID_YOU_MEAN_TUESDAY =
            "Did you mean \"tuesday\"? ";
    public static final String MESSAGE_DID_YOU_MEAN_WEDNESDAY =
            "Did you mean \"wednesday\"? ";
    public static final String MESSAGE_DID_YOU_MEAN_THURSDAY =
            "Did you mean \"thursday\"? ";
    public static final String MESSAGE_DID_YOU_MEAN_FRIDAY =
            "Did you mean \"friday\"? ";
    public static final String MESSAGE_DID_YOU_MEAN_SATURDAY =
            "Did you mean \"saturday\"? ";
    public static final String MESSAGE_DID_YOU_MEAN_SUNDAY =
            "Did you mean \"sunday\"? ";
    public static final String VALIDATION_REGEX = "(?i)mon|monday"
            + "|tue|tues|tuesday"
            + "|wed|wednesday"
            + "|thu|thur|thurs|thursday"
            + "|fri|friday"
            + "|sat|saturday"
            + "|sun|sunday";
    public final String day;

    Day(String day) {
        this.day = day;
    }


    /**
     * Creates a Day object depending on the inputDay.
     *
     * @param inputDay A string representing the day of the week of the tuition class.
     * @return A Day object with the respective enum value.
     * @throws InvalidDayException if the inputDay does not match any of the enum values.
     */
    public static Day createDay(String inputDay) throws InvalidDayException {
        for (Day day : Day.values()) {
            if (inputDay.toUpperCase().equals(day.name())) {
                return day;
            }
        }
        throw new InvalidDayException(); //change to null?
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

    /**
     * Returns true if a given string is a valid value in enum Day.
     */
    public static boolean isValidDay(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
