package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

class AppointmentOfFilteredPersonsPredicateTest {

    @Test
    public void equals() {
        List<Person> firstPersonList = TypicalPersons.getTypicalPersons();
        List<Person> secondPersonList = Arrays.asList(TypicalPersons.ALICE, TypicalPersons.BENSON);

        AppointmentOfFilteredPersonsPredicate firstPredicate =
                new AppointmentOfFilteredPersonsPredicate(firstPersonList);
        AppointmentOfFilteredPersonsPredicate firstPredicateCopy =
                new AppointmentOfFilteredPersonsPredicate(firstPersonList);
        AppointmentOfFilteredPersonsPredicate secondPredicate =
                new AppointmentOfFilteredPersonsPredicate(secondPersonList);

        PredicateTestUtil.assertBasicEqualities(firstPredicate, firstPredicateCopy, secondPredicate);
    }

    @Test
    public void test_personListContainsAppointment_returnsTrue() {
        List<Person> personList = Arrays.asList(TypicalPersons.ALICE, TypicalPersons.BENSON, TypicalPersons.CARL);

        Appointment appointmentInList = TypicalPersons.BENSON.getAppointments().get(0);
        AppointmentOfFilteredPersonsPredicate predicate = new AppointmentOfFilteredPersonsPredicate(personList);
        assertTrue(predicate.test(appointmentInList));

        Appointment anotherAppointmentInList = TypicalPersons.CARL.getAppointments().get(1);
        predicate = new AppointmentOfFilteredPersonsPredicate(personList);
        assertTrue(predicate.test(appointmentInList));
    }

    @Test
    public void test_personListDoesNotContainsAppointment_returnsFalse() {
        List<Person> personList = Arrays.asList(TypicalPersons.BENSON);
        Appointment appointmentNotInList = TypicalPersons.CARL.getAppointments().get(1);

        AppointmentOfFilteredPersonsPredicate predicate = new AppointmentOfFilteredPersonsPredicate(personList);
        assertFalse(predicate.test(appointmentNotInList));
    }
}