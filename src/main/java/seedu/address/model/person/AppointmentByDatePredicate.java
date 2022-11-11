package seedu.address.model.person;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Appointment Date} matches any of the appointment dates given.
 */
public class AppointmentByDatePredicate implements Predicate<Person> {
    private final List<LocalDate> appointments;

    /**
     * Constructor for AppointmentByDatePredicate.
     * @param appointments patients' appointments
     */
    public AppointmentByDatePredicate(List<LocalDate> appointments) {
        this.appointments = appointments;
    }

    /**
     * Tests if the input argument matches the person's details
     * @param person the input argument
     * @return the test result
     */
    @Override
    public boolean test(Person person) {
        Boolean hasMatchPastAppointments = false;
        Boolean hasMatchUpcomingAppointments = false;

        if (isPresentPastAppointment(person)) {
            hasMatchPastAppointments = appointments.stream().anyMatch(
                    keyword -> person.getPastAppointments().stream()
                            .anyMatch(appointment -> keyword.compareTo(appointment.getDate()) == 0));
        }

        if (isPresentUpcomingAppointment(person)) {
            hasMatchUpcomingAppointments = appointments.stream()
                    .anyMatch(appointment -> appointment.compareTo(
                            person.getUpcomingAppointment().get().getDate()) == 0);
        }
        return hasMatchUpcomingAppointments || hasMatchPastAppointments;
    }

    private boolean isPresentUpcomingAppointment(Person person) {
        return person.getUpcomingAppointment().isPresent() && person.getUpcomingAppointment().get().getDate() != null;
    }

    private boolean isPresentPastAppointment(Person person) {
        return person.getPastAppointmentCount() != 0;
    }

    /**
     * Tests the similarity of two objects
     * @param other the object to be tested
     * @return true if the objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentByDatePredicate // instanceof handles nulls
                && appointments.equals(((AppointmentByDatePredicate) other).appointments)); // state check
    }
}
