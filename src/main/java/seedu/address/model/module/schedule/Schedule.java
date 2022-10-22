package seedu.address.model.module.schedule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a schedule of a module
 */
public class Schedule {
    public static final String MESSAGE_WEEKDAYS_CONSTRAINT = "Weekday should be one of Monday, Tuesday, Wednesday, "
            + "Thursday, Friday, Saturday, and Sunday.";


    public static final String MESSAGE_TIMESLOT_CONSTRAINT = "The time slot of a class should follow the 24:00-format"
            + " : HH:MM-HH:MM" + " (space not allowed)\n"
            + "HH denotes hour : from 00 to 23 \n"
            + "MM denotes minute : only 00 or 30 \n";

    public static final String MESSAGE_CLASS_STARTINGTIME_CONSTRAINT = "The class starting time should be after 08:00 "
            + "\n";
    public static final String MESSAGE_CLASS_ENDINGTIME_CONSTRAINT = "The class ending time should be before 22:00 \n";
    public static final String MESSAGE_CLASS_STARTING_ENDINGT_CONSTRAINT = "The class starting time should be before "
            + "the class ending time \n";
    public static final String MESSAGE_CLASS_TIME_CONSTRAINT = MESSAGE_TIMESLOT_CONSTRAINT
            + MESSAGE_CLASS_STARTINGTIME_CONSTRAINT
            + MESSAGE_CLASS_ENDINGTIME_CONSTRAINT
            + MESSAGE_CLASS_STARTING_ENDINGT_CONSTRAINT;
    public static final String MESSAGE_CLASS_TYPE_CONSTRAINT = "Class type should be one of lec, tut, lab, rec.";
    private String module;
    private Venue venue;
    private Weekdays weekday;
    private String startTime;
    private String endTime;
    private ClassType classType;
    private String classGroup;

    /**
     * Requires all fields to be not null
     * @param venue venue
     * @param weekday weekday
     * @param startTime start time
     * @param endTime end time
     */
    public Schedule(String module, Venue venue, Weekdays weekday, String startTime, String endTime, ClassType
            classType, String classGroup) {
        requireAllNonNull(module, venue, weekday, startTime, endTime, classType, classGroup);
        this.module = module;
        this.venue = venue;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classType = classType;
        this.classGroup = classGroup;
    }

    @Override
    public String toString() {
        return module.toUpperCase() + "\n" + classType + "  [" + classGroup + "]" + "\n" + startTime + "-" + endTime
                + "\n" + venue;
    }

    public Weekdays getWeekday() {
        return weekday;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getPeriod() {
        return startTime + "-" + endTime;
    }

    public ClassType getClassType() {
        return classType;
    }

    public String getModule() {
        return module;
    }

    public String getClassGroup() {
        return classGroup;
    }

    public void setVenue(Venue newVenue) {
        this.venue = newVenue;
    }

    public void setStartTime(String newStartTime) {
        this.startTime = newStartTime;
    }

    public void setEndTime(String newEndTime) {
        this.endTime = newEndTime;
    }

    public void setWeekday(Weekdays newWeekday) {
        this.weekday = newWeekday;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public void setClassGroup(String classGroup) {
        this.classGroup = classGroup;
    }

    /**
     * Checks if the time is valid.
     */
    public static boolean isTimeValid(String startTime, String endTime) {
        Pattern pattern = Pattern.compile("[0-9]{2}:[0-9]{2}");
        Matcher matcher1 = pattern.matcher(startTime);
        Matcher matcher2 = pattern.matcher(endTime);
        return matcher1.find() && matcher2.find();
    }

    /**
     * Checks if the new schedule is conflict with this one
     * @param newSchedule new slot to add
     * @return conflict or not
     */
    public boolean isConflictWith(Schedule newSchedule) {
        if (newSchedule.startTime.compareTo(this.endTime) >= 0
                || newSchedule.endTime.compareTo(this.startTime) <= 0) {
            return false;
        }
        return newSchedule.weekday.equals(this.weekday);
    }

    /**
     * Checks if the module code and class type of another schedule match the module code
     * and class type of current schedule.
     * @param other another schedule
     */
    public boolean match(Schedule other) {
        return module.equals(other.getModule()) && classType.equals(other.classType);
    }

    /**
     * Returns the integer start hour
     */
    public double getHour(String time) {
        String hour = time.split(":")[0];
        String halfHour = time.split(":")[1];
        double hourDouble = Double.parseDouble(hour);
        double halfDouble = Double.parseDouble(halfHour) == 30.0 ? 0.5 : 0;
        return hourDouble + halfDouble;
    }

    /**
     * Returns the duration of the schedule
     */
    public double getDuration() {
        return getHour(endTime) - getHour(startTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Schedule)) {
            return false;
        }
        Schedule otherSchedule = (Schedule) other;
        return otherSchedule.venue.equals(this.venue)
                && otherSchedule.module.equals(this.module)
                && otherSchedule.weekday.equals(this.weekday)
                && otherSchedule.startTime.equals(this.startTime)
                && otherSchedule.endTime.equals(this.endTime)
                && otherSchedule.classType.equals(this.classType)
                && otherSchedule.classGroup.equals(this.classGroup);
    }

}
