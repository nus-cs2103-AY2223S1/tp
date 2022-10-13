package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class NameContainsSequencePredicateTest {

    @Test
    public void equals() {
        String firstSequence = "first";
        String secondSequence = "second";

        NameContainsSequencePredicate firstPredicate = new NameContainsSequencePredicate(firstSequence);
        NameContainsSequencePredicate firstPredicateCopy = new NameContainsSequencePredicate(firstSequence);
        NameContainsSequencePredicate secondPredicate = new NameContainsSequencePredicate(secondSequence);

        PredicateTestUtil.assertBasicEqualities(firstPredicate, firstPredicateCopy, secondPredicate);
    }

    @Test
    public void test_nameContainsSequence_returnsTrue() {
        Person personToTest = generatePersonWithName("Alice Yeoh");

        // Same name
        NameContainsSequencePredicate predicate = new NameContainsSequencePredicate("Alice Yeoh");
        assertTrue(predicate.test(personToTest));

        // Contains sequence
        predicate = new NameContainsSequencePredicate("Ali");
        assertTrue(predicate.test(personToTest));

        // Can find names with spaces
        predicate = new NameContainsSequencePredicate("Alice Yeoh");
        assertTrue(predicate.test(personToTest));

        // Can find names through spaces
        predicate = new NameContainsSequencePredicate("ce Ye");
        assertTrue(predicate.test(personToTest));

        // Mixed-case sequence
        predicate = new NameContainsSequencePredicate("iCE yEO");
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_nameDoesNotContainSequence_returnsFalse() {
        Person personToTest = generatePersonWithName("John Doe");
        // Non-matching sequence
        NameContainsSequencePredicate predicate = new NameContainsSequencePredicate("Alice");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = new NameContainsSequencePredicate("Johnny");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = new NameContainsSequencePredicate("John Does");
        assertFalse(predicate.test(personToTest));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameContainsSequencePredicate("John");
        assertFalse(predicate.test(new PersonBuilder().withName("12345").withPhone("12345")
                .withEmail("John@email.com").withAddress("John Street").build()));
    }

    private Person generatePersonWithName(String name) {
        return new PersonBuilder().withName(name).build();
    }
}