package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.schedule.ClassType;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.Venue;
import seedu.address.model.module.schedule.Weekdays;

/**
 * Jackson-friendly version of {@link Schedule}
 */
public class JsonAdaptedSchedule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";
    private final String module;
    private final Venue venue;
    private final Weekdays weekday;
    private final String startTime;
    private final String endTime;

    private final ClassType classType;
    private final String classGroup;
    /**
     * Constructs a {@code JsonAdaptedSchedule} with the given schedule details.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("module") String module, @JsonProperty("venue") Venue venue,
                               @JsonProperty("weekday") Weekdays weekday, @JsonProperty("startTime") String startTime,
                               @JsonProperty("endTime") String endTime, @JsonProperty("classType") ClassType classType,
                               @JsonProperty("classGroup") String classGroup) {
        this.module = module;
        this.venue = venue;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classType = classType;
        this.classGroup = classGroup;
    }

    /**
     * Construct a given {@code Schedule} into this class
     */
    public JsonAdaptedSchedule(Schedule source) {
        module = source.getModule();
        venue = source.getVenue();
        weekday = source.getWeekday();
        startTime = source.getStartTime();
        endTime = source.getEndTime();
        classType = source.getClassType();
        classGroup = source.getClassGroup();
    }

    /**
     * Converts this Jackson-friendly adapted schedule object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted schedule.
     */
    public Schedule toModelType() throws IllegalValueException {
        if (module == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "module"));
        }
        if (weekday == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Weekdays.class.getSimpleName()));
        }
        if (venue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Venue.class.getSimpleName()));
        }
        if (startTime == null || endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "time"));
        }
        if (classType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClassType.class.getSimpleName()));
        }
        if (classGroup == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "group"));
        }
        if (!Schedule.isTimeValid(startTime, endTime)) {
            throw new IllegalValueException(Schedule.MESSAGE_CLASS_TIME_CONSTRAINT);
        }
        return new Schedule(module, venue, weekday, startTime, endTime, classType, classGroup);
    }
}
