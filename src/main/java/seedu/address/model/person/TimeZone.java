package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class TimeZone {

    private static final String TIMEZONE_CONSTRAINTS = "Timezone offsets should be double digit hours, " +
            "followed by \":\", then double digit minutes";

    private static final String OFFSET_REGEX = "/^(?:Z|[+-](?:2[0-3]|[01][0-9]):[0-5][0-9])$/";

    private static final int HOURS_TO_MINUTES = 60;
    private final String offset;

    public TimeZone(String offset) {
        requireNonNull(offset);
        checkArgument(isValidTimeZone(offset), TIMEZONE_CONSTRAINTS);
        this.offset = offset;
    }

    public static boolean isValidTimeZone(String test) {
        return test.matches(OFFSET_REGEX);
    }

    public static int convertToIntOffset(String strOffset) {
        String [] strArray = strOffset.split(":");
        return Integer.parseInt(strArray[0].trim()) * HOURS_TO_MINUTES //calculate hours
                + Integer.parseInt(strArray[1].trim()); //calculate seconds
    }

    public String getOffsetInString() {
        return offset;
    }

    public int getOffsetInInt() {
        return convertToIntOffset(offset);
    }

    public static String getTimezoneConstraints() {
        return TIMEZONE_CONSTRAINTS;
    }

    public static int getHoursToMinutes() {
        return HOURS_TO_MINUTES;
    }

    @Override
    public boolean equals(Object other) {
        return this == other //short circuit if same object
                || (other instanceof TimeZone
                && offset.equals(((TimeZone) other).getOffsetInString()));
    }

    @Override
    public String toString() {
        return offset;
    }



}
