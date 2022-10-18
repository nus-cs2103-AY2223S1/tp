package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

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
        if (other == this) {
            return true;
        }

        if (!(other instanceof AppointmentOfFilteredPersonsPredicate)) {
            return false;
        }

        AppointmentOfFilteredPersonsPredicate otherPredicate = (AppointmentOfFilteredPersonsPredicate) other;
        return filteredPersons.equals(otherPredicate.filteredPersons);
    }
}
