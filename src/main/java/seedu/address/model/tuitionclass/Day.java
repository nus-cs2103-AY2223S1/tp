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

    public static final String MESSAGE_CONSTRAINTS =
            "Day should only contain letters, and should be spelt out in full.";
    public static final String VALIDATION_REGEX = "(?i)monday|tuesday|wednesday|thursday|friday|saturday|sunday";
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

    //    /**
    //     * Returns true if a given string is a valid value in enum Day.
    //     */
    //    public static boolean isValidDay(String day) {
    //        for (Day d : Day.values()) {
    //            if (d.name().equals(day)) {
    //                return true;
    //            }
    //        }
    //        return false;
    //    }

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
