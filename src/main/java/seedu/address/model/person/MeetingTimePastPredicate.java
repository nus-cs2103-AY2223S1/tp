package seedu.address.model.person;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code MeetingTime} is before the local time on machine.
 */
public class MeetingTimePastPredicate implements Predicate<MeetingTime> {
    private final LocalDateTime now;

    /**
     * Sets the time to be compared to to the local present time.
     */
    public MeetingTimePastPredicate() {
        this.now = LocalDateTime.now();
        now.toString();
    }

    @Override
    public boolean test(MeetingTime meetingTime) {
        return meetingTime.getDate().isBefore(now);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingTimePastPredicate // instanceof handles nulls
                && now.equals(((MeetingTimePastPredicate) other).now)); // state check
    }

}
