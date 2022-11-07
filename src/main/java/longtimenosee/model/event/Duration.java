package longtimenosee.model.event;

import longtimenosee.commons.core.index.Index;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.regex.PatternSyntaxException;


/**
 * Represents a duration class, which consists of 2 LocalTime Objects
 *
 */
public class Duration {

    public static final String MESSAGE_CONSTRAINTS =
            "Start cannot be before end.";
    public static final String FORMAT_CONSTRAINTS =
            "Time input must follow format : HH:MM";
    private static final double MINUTES_IN_HOUR = 60;
    public final String value;
    private LocalTime startTime;
    private LocalTime endTime;


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
     * Parses the string value and checks if each value follows "HH:MM"
     * @param time in format: "HH
     * @return
     */
    public static boolean isValidFormat(String time) {
        try {
            String[] timeInput = time.split("__");
            String startTime = timeInput[0];
            String endTime = timeInput[1];
            return isValidTime(startTime) && isValidTime(endTime);
        } catch (PatternSyntaxException pse) {
            return false;
        } catch (IndexOutOfBoundsException ibe) {
            return false;
        }
    }

    /**
     * Used for validation purposes in ParserUtil, to check if each individual date given can be parsed
     * Will be used to parse both start and end times (individually)
     * @param time
     * @return isValidTime or not
     */
    public static boolean isValidTime(String time) {
        try {
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException dte) {
            return false;
        }
        return true;
    }

    /**
     * Used for validation purposes in ParserUtil
     * The deliminator "__" will be added in between both start and end times after
     * both have been individually validated
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
    public boolean isOverlap(Duration other) {
        //check if the other event's start time is before this start time or after the end time
        //Defensive programming by checking both the start and end time for each event
        //Case 1: Other event happens before --> other event must start and end before this event's start time
        boolean happensBefore = other.startTime.isBefore(this.startTime)
                && other.endTime.isBefore(this.startTime);
        //Case 1: Other event happens after --> other event must start and end after this event's end time
        boolean happensAfter = other.startTime.isAfter(this.endTime)
                && other.endTime.isAfter(this.endTime);
        return !(happensBefore || happensAfter);
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
                .ofLocalizedTime(FormatStyle.SHORT))
                + " || End: " + endTime.format(DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT));
    }

    public LocalTime getStartTime() {
        return startTime;
    }
}

