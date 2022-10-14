package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class EmailContainsSequencePredicateTest {

    @Test
    public void equals() {
        String firstSequence = "first@google.com";
        String secondSequence = "second@google.com";

        EmailContainsSequencePredicate firstPredicate = new EmailContainsSequencePredicate(firstSequence);
        EmailContainsSequencePredicate firstPredicateCopy = new EmailContainsSequencePredicate(firstSequence);
        EmailContainsSequencePredicate secondPredicate = new EmailContainsSequencePredicate(secondSequence);

        PredicateTestUtil.assertBasicEqualities(firstPredicate, firstPredicateCopy, secondPredicate);
    }

    @Test
    public void test_emailContainsSequence_returnsTrue() {
        Person personToTest = generatePersonWithEmail("Alice@gmail.com");

        // Same email
        EmailContainsSequencePredicate predicate = new EmailContainsSequencePredicate("Alice@gmail.com");
        assertTrue(predicate.test(personToTest));

        // Contains sequence
        predicate = new EmailContainsSequencePredicate("Alice");
        assertTrue(predicate.test(personToTest));

        // Contains sequence
        predicate = new EmailContainsSequencePredicate("gmail");
        assertTrue(predicate.test(personToTest));

        // Can find non-alphanumeric characters
        predicate = new EmailContainsSequencePredicate("ce@gmail.c");
        assertTrue(predicate.test(personToTest));

        // Mixed-case sequence
        predicate = new EmailContainsSequencePredicate("ICE@GMail.cOM");
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_emailDoesNotContainSequence_returnsFalse() {
        Person personToTest = generatePersonWithEmail("John@gmail.com");

        // Non-matching sequence
        EmailContainsSequencePredicate predicate = new EmailContainsSequencePredicate("Alice");
        assertFalse(predicate.test(personToTest));

        // Non-matching sequence
        predicate = new EmailContainsSequencePredicate("google");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = new EmailContainsSequencePredicate("Johnny");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = new EmailContainsSequencePredicate("John@gmail.com.sg");
        assertFalse(predicate.test(personToTest));

        // Sequence match name, phone and address, but does not match email
        predicate = new EmailContainsSequencePredicate("John");
        assertFalse(predicate.test(new PersonBuilder().withName("John").withPhone("12345")
                .withEmail("Alice@gmail.com").withAddress("John Street").build()));
    }

    private Person generatePersonWithEmail(String email) {
        return new PersonBuilder().withEmail(email).build();
    }
}
