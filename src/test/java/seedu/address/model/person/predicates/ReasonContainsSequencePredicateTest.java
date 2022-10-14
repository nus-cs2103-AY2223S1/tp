package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.PersonBuilder;

class ReasonContainsSequencePredicateTest {

    @Test
    public void equals() {
        String firstSequence = "first";
        String secondSequence = "second";

        ReasonContainsSequencePredicate firstPredicate = new ReasonContainsSequencePredicate(firstSequence);
        ReasonContainsSequencePredicate firstPredicateCopy = new ReasonContainsSequencePredicate(firstSequence);
        ReasonContainsSequencePredicate secondPredicate = new ReasonContainsSequencePredicate(secondSequence);

        PredicateTestUtil.assertBasicEqualities(firstPredicate, firstPredicateCopy, secondPredicate);
    }

    @Test
    public void test_reasonContainsSequence_returnsTrue() {
        Appointment appointmentToTest = generateAppointmentWithReason("Sore Throat");

        // Same reason
        ReasonContainsSequencePredicate predicate = new ReasonContainsSequencePredicate("Sore Throat");
        assertTrue(predicate.test(appointmentToTest));

        // Contains sequence
        predicate = new ReasonContainsSequencePredicate("Sore");
        assertTrue(predicate.test(appointmentToTest));

        // Can find reason through spaces
        predicate = new ReasonContainsSequencePredicate("re Thro");
        assertTrue(predicate.test(appointmentToTest));

        // Mixed-case sequence
        predicate = new ReasonContainsSequencePredicate("OrE tHROAt");
        assertTrue(predicate.test(appointmentToTest));
    }

    @Test
    public void test_reasonDoesNotContainSequence_returnsFalse() {
        Appointment appointmentToTest = generateAppointmentWithReason("Sore Throat");

        // Non-matching sequence
        ReasonContainsSequencePredicate predicate = new ReasonContainsSequencePredicate("Cough");
        assertFalse(predicate.test(appointmentToTest));

        // Incomplete match
        predicate = new ReasonContainsSequencePredicate("Sore Throats");
        assertFalse(predicate.test(appointmentToTest));

        // Incomplete match
        predicate = new ReasonContainsSequencePredicate("Canker Sore");
        assertFalse(predicate.test(appointmentToTest));
    }

    private Appointment generateAppointmentWithReason(String reason) {
        return new AppointmentBuilder().withReason(reason).build();
    }
}