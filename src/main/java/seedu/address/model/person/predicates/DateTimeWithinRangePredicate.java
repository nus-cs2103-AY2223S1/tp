package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.model.person.Appointment;

/**
 * Tests that an {@code Appointment}'s {@code dateTime} is within the range specified.
 */
public class DateTimeWithinRangePredicate implements Predicate<Appointment> {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Creates a new {@code DateTimeWithinRangePredicate} that tests if an appointment lies within
     * {@code startDateTime} and {@code endDateTime}.
     *
     * @param startDateTime Start date to test.
     * @param endDateTime End date of test.
     */
    public DateTimeWithinRangePredicate(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        requireNonNull(startDateTime);
        requireNonNull(endDateTime);

        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public boolean test(Appointment appointment) {
        return isDateTimeWithinRange(appointment.getDateTime());
    }

    private boolean isDateTimeWithinRange(LocalDateTime appointmentDateTime) {
        assert startDateTime != null : "startDateTime should not be null.";
        assert endDateTime != null : "endDateTime should not be null.";

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
