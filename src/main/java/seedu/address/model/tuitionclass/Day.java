package seedu.address.model.tuitionclass;

import seedu.address.model.tuitionclass.exceptions.InvalidDayException;

// Solution below adapted from: https://github.com/dextertanyj/tp/blob/master/src/main/java/tutorspet/model/lesson/Day.java
/**
 * Represents the day of the week that the tuition class is held on.
 */
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static final String MESSAGE_CONSTRAINTS =
            "Day should only contain letters, and should be spelt out in full.";

    public static Day createDay(String inputDay) throws InvalidDayException {
        for (Day day : Day.values()) {
            if (inputDay.toUpperCase().equals(day.name())) {
                return day;
            }
        }
        throw new InvalidDayException();
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
