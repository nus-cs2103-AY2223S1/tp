package seedu.address.model.person.predicates;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.model.person.Appointment;

/**
 * Tests that an {@code Appointment}'s {@code dateTime} is within the range specified.
 */
public class DateTimeWithinRangePredicate implements Predicate<Appointment> {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public DateTimeWithinRangePredicate(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public boolean test(Appointment appointment) {
        return isDateTimeWithinRange(appointment.getDateTime());
    }

    private boolean isDateTimeWithinRange(LocalDateTime appointmentDateTime) {
        boolean isAtOrAfterStartTime =
                appointmentDateTime.isEqual(startDateTime) || appointmentDateTime.isAfter(startDateTime);
        boolean isAtOrBeforeEndTime =
                appointmentDateTime.isEqual(endDateTime) || appointmentDateTime.isBefore(endDateTime);
        return isAtOrAfterStartTime && isAtOrBeforeEndTime;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateTimeWithinRangePredicate)) {
            return false;
        }

        // state check
        DateTimeWithinRangePredicate otherPredicate = (DateTimeWithinRangePredicate) other;
        return startDateTime.equals(otherPredicate.startDateTime)
                && endDateTime.equals(otherPredicate.endDateTime);
    }
}
