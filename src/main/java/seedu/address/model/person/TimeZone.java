package seedu.address.model.person;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class TimeZone {

    public static final String MESSAGE_CONSTRAINTS = "Timezone offsets should be double digit hours, " +
            "followed by \":\", then double digit minutes";

    private static final String OFFSET_REGEX = "^(?:Z|[+-](?:2[0-3]|[01][0-9]):[0-5][0-9])$";

    private static final int HOURS_TO_MINUTES = 60;
    private final String offset;

    public TimeZone(String offset) {
        requireNonNull(offset);
        if (offset.equals("")) {
            offset = getDeviceTimeZoneOffset();
        }
        checkArgument(isValidTimeZone(offset), MESSAGE_CONSTRAINTS);
        this.offset = offset;
    }

    private String getDeviceTimeZoneOffset() {

        java.util.TimeZone tz = java.util.TimeZone.getDefault();
        Calendar cal = GregorianCalendar.getInstance(tz);
        int offsetInMillis = tz.getOffset(cal.getTimeInMillis());

        String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000),
                Math.abs((offsetInMillis / 60000) % 60));
        offset = (offsetInMillis >= 0 ? "+" : "-") + offset;

        return offset;

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

    public static String getMessageConstraints() {
        return MESSAGE_CONSTRAINTS;
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
