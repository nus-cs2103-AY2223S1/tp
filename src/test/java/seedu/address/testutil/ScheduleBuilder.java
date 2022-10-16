package seedu.address.testutil;

import seedu.address.model.module.schedule.ClassType;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.Venue;
import seedu.address.model.module.schedule.Weekdays;

/**
 * A utility class to help with building Schedule objects.
 */
public class ScheduleBuilder {
    public static final String DEFAULT_MODULE = "CS2103T";
    public static final String DEFAULT_START_TIME = "16:00";
    public static final String DEFAULT_END_TIME = "18:00";
    public static final Venue DEFAULT_VENUE = new Venue("COM2 02-17");
    public static final Weekdays DEFAULT_WEEKDAY = Weekdays.Friday;
    public static final ClassType DEFAULT_CLASS_TYPE = ClassType.Lecture;

    private String module;
    private Weekdays weekday;
    private String startTime;
    private String endTime;
    private ClassType classType;
    private Venue venue;

    /**
     * Creates a {@code ScheduleBuilder} with the default details.
     */
    public ScheduleBuilder() {
        this.module = DEFAULT_MODULE;
        this.startTime = DEFAULT_START_TIME;
        this.endTime = DEFAULT_END_TIME;
        this.weekday = DEFAULT_WEEKDAY;
        this.venue = DEFAULT_VENUE;
        this.classType = DEFAULT_CLASS_TYPE;
    }

    /**
     * Initializes the ScheduleBuilder with the data of {@code scheduleToCopy}.
     */
    public ScheduleBuilder(Schedule scheduleToCopy) {
        this.module = scheduleToCopy.getModule();
        this.startTime = scheduleToCopy.getStartTime();
        this.endTime = scheduleToCopy.getEndTime();
        this.venue = scheduleToCopy.getVenue();
        this.classType = scheduleToCopy.getClassType();
        this.weekday = scheduleToCopy.getWeekday();
    }

    /**
     * Sets the {@code module} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withModule(String module) {
        this.module = module;
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Sets the {@code weekday} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withWeekday(Weekdays weekday) {
        this.weekday = weekday;
        return this;
    }

    /**
     * Sets the {@code classType} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withClassType(ClassType classType) {
        this.classType = classType;
        return this;
    }

    /**
     * Sets the {@code venue} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withVenue(Venue venue) {
        this.venue = venue;
        return this;
    }

    /**
     * Builds a schedule for testing.
     *
     * @return a Schedule that we are building
     */
    public Schedule build() {
        return new Schedule(module, venue, weekday, startTime, endTime, classType);
    }
}
