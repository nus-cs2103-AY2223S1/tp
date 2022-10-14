package seedu.address.model.person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code Date} matches the input {@code Date}.
 */
public class AppointmentOnDatePredicate implements Predicate<Appointment> {
    private LocalDate localDate;

    public AppointmentOnDatePredicate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getLocalDate().equals(localDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentOnDatePredicate// instanceof handles nulls
                && localDate.equals(((AppointmentOnDatePredicate) other).localDate)); // state check
    }
}