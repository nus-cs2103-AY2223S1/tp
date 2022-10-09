package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code Patient} matches any of the Persons given.
 */
public class AppointmentOfFilteredPersonsPredicate implements Predicate<Appointment> {
    private final List<Person> filteredPersons;

    public AppointmentOfFilteredPersonsPredicate(List<Person> filteredPersons) {
        this.filteredPersons = filteredPersons;
    }

    @Override
    public boolean test(Appointment appointment) {
        return filteredPersons.stream().anyMatch(validPersons -> validPersons.equals(appointment.getPatient()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentOfFilteredPersonsPredicate // instanceof handles nulls
                && filteredPersons.equals((
                        (AppointmentOfFilteredPersonsPredicate) other).filteredPersons)); // state check
    }
}
