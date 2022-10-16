package seedu.address.model.person;

/**
 * Represents the timings in a week in which a friend
 * is available to play Minecraft.
 */
public interface ITimesAvailable {

    boolean isAvailable(DayTimeInWeek dayTimeInWeek);

}
