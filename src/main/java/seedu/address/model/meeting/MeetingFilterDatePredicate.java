package seedu.address.model.meeting;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.model.util.DateTimeConverter;

/**
 * Tests that a {@code Meetings}'s date is between specified dates
 */
public class MeetingFilterDatePredicate implements Predicate<Meeting> {
    private final LocalDateTime afterDate;
    private final LocalDateTime beforeDate;

    /**
     * Constructor for the object
     * @param afterDate checks if date is "after" this date
     * @param beforeDate checks if date is "before" this date
     */
    public MeetingFilterDatePredicate(LocalDateTime afterDate, LocalDateTime beforeDate) {
        this.afterDate = afterDate;
        this.beforeDate = beforeDate;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param meeting the input argument
     * @return {@code true} if the meeting date is between
     *     date1 and date2, if the date1 is equal to date2, then
     *     {@code true} if meeting is on date1, {@code  false} otherwise
     */
    @Override
    public boolean test(Meeting meeting) {
        LocalDateTime meetingDate = DateTimeConverter
            .processedStringToLocalDatetime(meeting.getDateAndTime());
        if (afterDate.isEqual(beforeDate)) {
            return afterDate.isEqual(meetingDate);
        }
        return meetingDate.isAfter(afterDate) && meetingDate.isBefore(beforeDate)
            || meetingDate.isEqual(afterDate) || meetingDate.isEqual(beforeDate);
    }
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MeetingFilterDatePredicate
                && beforeDate.equals(((MeetingFilterDatePredicate) other).beforeDate)
                && afterDate.equals(((MeetingFilterDatePredicate) other).afterDate));
    }
}
