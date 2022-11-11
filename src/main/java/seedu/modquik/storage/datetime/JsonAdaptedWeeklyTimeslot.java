package seedu.modquik.storage.datetime;

import static seedu.modquik.model.datetime.WeeklyTimeslot.fromFormattedString;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.modquik.commons.exceptions.IllegalValueException;
import seedu.modquik.model.datetime.WeeklyTimeslot;

/**
 * Jackson-friendly version of {@link WeeklyTimeslot}.
 */
public class JsonAdaptedWeeklyTimeslot {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "WeeklyTimeslot's %s field is missing!";

    public final String day;
    public final String startTime;
    public final String endTime;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given weekly timeslot details.
     */
    @JsonCreator
    public JsonAdaptedWeeklyTimeslot(@JsonProperty("day") String day,
                                    @JsonProperty("startTime") String startTime,
                                    @JsonProperty("endTime") String endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code WeeklyTimeslot} into this class for Jackson use.
     */
    public JsonAdaptedWeeklyTimeslot(WeeklyTimeslot source) {
        day = source.getDay();
        startTime = source.getStartTimeFormatted();
        endTime = source.getEndTimeFormatted();
    }

    /**
     * Converts this Jackson-friendly adapted weekly timeslot object into the model's {@code WeeklyTimeslot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted weekly timeslot.
     */
    public WeeklyTimeslot toModelType() throws IllegalValueException {
        if (day == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, WeeklyTimeslot.class.getSimpleName()));
        }
        if (!WeeklyTimeslot.isValidDay(day)) {
            throw new IllegalValueException("Invalid day");
        }
        if (startTime == null || endTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, WeeklyTimeslot.class.getSimpleName()));
        }
        if (!WeeklyTimeslot.isValidTimeRange(startTime, endTime)) {
            throw new IllegalValueException("Invalid time");
        }
        return fromFormattedString(day, startTime, endTime);
    }
}
