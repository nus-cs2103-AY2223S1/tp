package seedu.address.storage.datetime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.datetime.DatetimeRange;
import seedu.address.model.datetime.WeeklyTimeslot;

/**
 * Jackson-friendly version of {@link DatetimeRange}.
 */
public class JsonAdaptedWeeklyTimeslot {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    public final String day;
    public final String startTime;
    public final String endTime;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
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
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedWeeklyTimeslot(WeeklyTimeslot source) {
        day = source.getDay();
        startTime = source.getStartTimeFormatted();
        endTime = source.getEndTimeFormatted();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public WeeklyTimeslot toModelType() throws IllegalValueException {
        if (day == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, WeeklyTimeslot.class.getSimpleName()));
        }
        if (!WeeklyTimeslot.isValidDay(day)) {
            throw new IllegalValueException(WeeklyTimeslot.MESSAGE_CONSTRAINTS_DAY);
        }
        if (startTime == null || endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, WeeklyTimeslot.class.getSimpleName()));
        }
        if (!WeeklyTimeslot.isValidTimeRange(startTime, endTime)) {
            throw new IllegalValueException(WeeklyTimeslot.MESSAGE_CONSTRAINTS_TIMES);
        }
        return new WeeklyTimeslot(day, startTime, endTime);
    }
}
