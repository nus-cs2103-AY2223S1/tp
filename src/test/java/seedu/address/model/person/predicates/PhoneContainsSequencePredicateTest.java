package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;

class PhoneContainsSequencePredicateTest {

    @Test
    public void equals() {
        String firstSequence = "12345";
        String secondSequence = "678910";

        PhoneContainsSequencePredicate firstPredicate = new PhoneContainsSequencePredicate(firstSequence);
        PhoneContainsSequencePredicate firstPredicateCopy = new PhoneContainsSequencePredicate(firstSequence);
        PhoneContainsSequencePredicate secondPredicate = new PhoneContainsSequencePredicate(secondSequence);

        PredicateTestUtil.assertBasicEqualities(firstPredicate, firstPredicateCopy, secondPredicate);
    }

    @Test
    void test_phoneContainsSequence_returnsTrue() {
        Person personToTest = generatePersonWithPhone("44556677");

        // Same phone
        PhoneContainsSequencePredicate predicate = new PhoneContainsSequencePredicate("44556677");
        assertTrue(predicate.test(personToTest));

        // Contains sequence
        predicate = new PhoneContainsSequencePredicate("5667");
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_phoneDoesNotContainSequence_returnsFalse() {
        Person personToTest = generatePersonWithPhone("12345678");

        // Non-matching sequence
        PhoneContainsSequencePredicate predicate = new PhoneContainsSequencePredicate("99999");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = new PhoneContainsSequencePredicate("22");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = new PhoneContainsSequencePredicate("2468");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = new PhoneContainsSequencePredicate("1234567890");
        assertFalse(predicate.test(personToTest));

        // Sequence match name, email and address, but does not match phone
        predicate = new PhoneContainsSequencePredicate("12345");
        assertFalse(predicate.test(new PersonBuilder().withName("12345").withPhone("99999")
                .withEmail("12345@email.com").withAddress("12345 Street").build()));
    }

    private Person generatePersonWithPhone(String phone) {
        return new PersonBuilder().withPhone(phone).build();
    }
}