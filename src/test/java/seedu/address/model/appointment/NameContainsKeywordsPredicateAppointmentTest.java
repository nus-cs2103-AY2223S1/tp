package seedu.address.model.appointment;

/*
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder; */

public class NameContainsKeywordsPredicateAppointmentTest {

    /*
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicateAppointment firstPredicate =
                new NameContainsKeywordsPredicateAppointment(firstPredicateKeywordList);
        NameContainsKeywordsPredicateAppointment secondPredicate =
                new NameContainsKeywordsPredicateAppointment(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicateAppointment firstPredicateCopy =
                new NameContainsKeywordsPredicateAppointment(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicateAppointment predicate =
                new NameContainsKeywordsPredicateAppointment(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new AppointmentBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicateAppointment(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new AppointmentBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicateAppointment(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new AppointmentBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicateAppointment(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new AppointmentBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicateAppointment predicate =
                new NameContainsKeywordsPredicateAppointment(Collections.emptyList());
        assertFalse(predicate.test(new AppointmentBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicateAppointment(Arrays.asList("Carol"));
        assertFalse(predicate.test(new AppointmentBuilder().withName("Alice Bob").build()));
    } */
}
