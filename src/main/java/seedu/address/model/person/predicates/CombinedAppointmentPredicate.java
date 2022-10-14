package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Appointment;

/**
 * A predicate the encapsulates the combination of several other Appointment predicates.
 */
public class CombinedAppointmentPredicate implements Predicate<Appointment> {
    private final String reason;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    // There is no equals() method for predicates. Ensure this predicate and variables to generate it are always final!
    private final Predicate<Appointment> combinedPredicate;

    /**
     * Creates a {@code CombinedAppointmentPredicate} object that tests if a given appointment contains a given
     * reason, and has a date between startDateTime and endDateTime.
     *
     * @param reason The string to test on an {@code Appointment}'s reason.
     * @param startDateTime Start date to test.
     * @param endDateTime End date of test.
     */
    public CombinedAppointmentPredicate(String reason, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        requireNonNull(startDateTime);
        requireNonNull(endDateTime);

        this.reason = reason;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;

        combinedPredicate = combineAllPredicates();
    }

    private Predicate<Appointment> combineAllPredicates() {
        assert startDateTime != null : "startDateTime should not be null.";
        assert endDateTime != null : "endDateTime should not be null.";

        List<Predicate<Appointment>> appointmentPredicates = new ArrayList<>();

        if (!reason.isEmpty()) {
            appointmentPredicates.add(new ReasonContainsSequencePredicate(reason));
        }
        appointmentPredicates.add(new DateTimeWithinRangePredicate(startDateTime, endDateTime));

        return appointmentPredicates.stream().reduce(PREDICATE_SHOW_ALL_APPOINTMENTS, Predicate::and);
    }

    @Override
    public boolean test(Appointment appointment) {
        return combinedPredicate.test(appointment);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CombinedAppointmentPredicate)) {
            return false;
        }

        CombinedAppointmentPredicate otherPredicate = (CombinedAppointmentPredicate) other;
        return reason.equals(otherPredicate.reason)
                && startDateTime.equals(otherPredicate.startDateTime)
                && endDateTime.equals(otherPredicate.endDateTime);
    }
}
