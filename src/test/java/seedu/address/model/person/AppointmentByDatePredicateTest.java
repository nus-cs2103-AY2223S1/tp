package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class AppointmentByDatePredicateTest {
    @Test
    public void equalsTest() {
        List<LocalDate> firstPredicateKeywordList = Collections.singletonList(LocalDate.of(2006, Month.AUGUST, 12));
        List<LocalDate> secondPredicateKeywordList = Arrays.asList(LocalDate.of(2012, Month.APRIL, 20),
                LocalDate.of(2001, Month.APRIL, 24));

        AppointmentByDatePredicate firstPredicate = new AppointmentByDatePredicate(firstPredicateKeywordList);
        AppointmentByDatePredicate secondPredicate = new AppointmentByDatePredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AppointmentByDatePredicate firstPredicateCopy = new AppointmentByDatePredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different date -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_appointmentByDatePredicate_returnsTrue() {
        AppointmentByDatePredicate predicate =
                new AppointmentByDatePredicate(Collections.singletonList(LocalDate.of(2030, Month.AUGUST, 12)));
        //with only upcoming appointments
        assertTrue(predicate.test(new PersonBuilder().withUpcomingAppointment("12-08-2030").build()));

        //with only past appointments
        predicate = new AppointmentByDatePredicate(Collections.singletonList(LocalDate.of(2006, Month.AUGUST, 12)));
        String[] pastAppointmentsDetails = {"12-08-2006", "ibuprofen", "headache"};
        assertTrue(predicate.test(new PersonBuilder().withPastAppointment(pastAppointmentsDetails).build()));

        //with upcoming appointment and past appointments
        predicate = new AppointmentByDatePredicate(Collections.singletonList(LocalDate.of(2024, Month.JUNE, 12)));
        String[] pastAppointments = {"12-08-2009", "panadol", "headache"};
        assertTrue(predicate.test(new PersonBuilder().withPastAppointment(pastAppointments).withUpcomingAppointment("12-06-2024").build()));
    }

    @Test
    public void test_appointmentByDatePredicate_returnsFalse() {
        String[] pastAppointments = {"12-08-2006", "panadol", "headache"};
        // Zero keywords
        AppointmentByDatePredicate predicate = new AppointmentByDatePredicate(Collections.emptyList());
        assertFalse(predicate.test(
                new PersonBuilder().withPastAppointment(pastAppointments).withUpcomingAppointment("12-12-2030").build()));

        // Non-matching keyword
        predicate = new AppointmentByDatePredicate(Arrays.asList(LocalDate.of(2012, Month.APRIL, 20),
                LocalDate.of(2001, Month.APRIL, 24)));
        assertFalse(predicate.test(
                new PersonBuilder().withPastAppointment(pastAppointments).withUpcomingAppointment("12-12-2030").build()));
    }
}
