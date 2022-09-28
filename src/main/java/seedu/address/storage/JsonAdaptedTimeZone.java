package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.TimeZone;

public class JsonAdaptedTimeZone {

    private final int offset;

    private final String strOffset;

    @JsonCreator
    public JsonAdaptedTimeZone(String offset) {
        this.strOffset = offset;
        this.offset = TimeZone.convertToIntOffset(offset);
    }

    @JsonCreator
    public JsonAdaptedTimeZone(int offset) {
        this.offset = offset;
        StringBuilder stringBuilder = new StringBuilder();
        String hours = Integer.toString(offset / TimeZone.getHoursToMinutes());
        if (hours.length() == 1) {
            stringBuilder.append("0").append(hours);
        } else {
            stringBuilder.append(hours);
        }
        stringBuilder.append(":");
        String minutes = Integer.toString(offset % TimeZone.getHoursToMinutes());
        stringBuilder.append(minutes);
        strOffset = stringBuilder.toString();
    }

    public TimeZone toModelType() throws IllegalValueException {
        if (!TimeZone.isValidTimeZone(strOffset)) {
            throw new IllegalValueException(TimeZone.getTimezoneConstraints());
        }
        return new TimeZone(strOffset);
    }
}
