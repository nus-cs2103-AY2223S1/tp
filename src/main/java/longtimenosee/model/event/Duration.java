package longtimenosee.model.event;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;



/**
 * Represents a duration class, which consists of 2 LocalTime Objects
 *
 */
public class Duration {

    public static final String MESSAGE_CONSTRAINTS =
            "Start and End times should follow the format: HH:MM ; Start cannot be before end.";
    private static final double MINUTES_IN_HOUR = 60;
    private static LocalTime startTime;
    private static LocalTime endTime;
    private String value;

    /**
     * For Input: The string value is seperated by "__". This is also what is saved in Storage
     * Both the start and end dates are already validated (by parserUtil)
     * Stores both values
     * @param input
     */
    public Duration(String input) {
        requireNonNull(input);
        checkArgument(isValidStartAndEnd(input), MESSAGE_CONSTRAINTS);
        value = input;
        String[] timeInput = value.split("__");
        startTime = LocalTime.parse(timeInput[0]);
        endTime = LocalTime.parse(timeInput[1]);
    }

    /**
     * Used for validation purposes in ParserUtil
     * Will be used to parse both start and end times (individually)
     * @param time
     * @return isValidTime or not
     */
    public static boolean isValidTime(String time) {
        try {
            LocalTime verifiedTime = LocalTime.parse(time);
        } catch (DateTimeParseException dte) {
            return false;
        }
        return true;
    }

    /**
     * Used for validation purposes in ParserUtil
     * The deliminator "__" will be added in between both start and end times
     * returns whether endTime is after startTime
     * @param time
     * @return isValidTime or not
     */
    public static boolean isValidStartAndEnd(String time) {
        try {
            String[] timeInput = time.split("__");
            LocalTime startTime = LocalTime.parse(timeInput[0]);
            LocalTime endTime = LocalTime.parse(timeInput[1]);
        } catch (DateTimeParseException dte) {
            //System.out.println("This should be verified previously");
            return false;
        }
        String[] timeInput = time.split("__");
        LocalTime startTime = LocalTime.parse(timeInput[0]);
        LocalTime endTime = LocalTime.parse(timeInput[1]);
        return endTime.isAfter(startTime);
    }

    /**
     * Compares 2 sets of time frames for a potential overlap.
     * @param other
     * @return isOverlap or not
     */
    public static boolean isOverlap(Duration other) {
        //check if the other event's start time is before this start time or after the end time
        boolean otherStartCheck = other.startTime.isBefore(startTime)
                || other.startTime.isAfter(endTime);
        //Likewise for the other event's end time
        boolean otherEndCheck = other.endTime.isBefore(startTime)
                || other.endTime.isAfter(endTime);
        return otherStartCheck && otherEndCheck;

    }

    /**
     * Utility function to generate the number of hours between 2 events, in decimal representation
     * @return nearest number of hours.
     */
    public double getHours() {
        long numHours = startTime.until(endTime, ChronoUnit.MINUTES);
        return ((double) numHours) / MINUTES_IN_HOUR;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Duration // instanceof handles nulls
                && startTime.equals(((Duration) other).startTime)// state check
                && endTime.equals(((Duration) other).endTime)); // state check
    }
    @Override
    public String toString() {
        return "Start: " + startTime.format(DateTimeFormatter
                .ofLocalizedTime(FormatStyle.LONG))
                + "End: " + startTime.format(DateTimeFormatter
                .ofLocalizedTime(FormatStyle.LONG));
    }
}

