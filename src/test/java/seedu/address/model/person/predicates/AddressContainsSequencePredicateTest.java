package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class AddressContainsSequencePredicateTest {
    @Test
    public void equals() {
        String firstSequence = "Blk 30 Geylang Street 29, #06-40";
        String secondSequence = "Blk 12 Singapore Street 34, #56-78";

        AddressContainsSequencePredicate firstPredicate = new AddressContainsSequencePredicate(firstSequence);
        AddressContainsSequencePredicate firstPredicateCopy = new AddressContainsSequencePredicate(firstSequence);
        AddressContainsSequencePredicate secondPredicate = new AddressContainsSequencePredicate(secondSequence);

        PredicateTestUtil.assertBasicEqualities(firstPredicate, firstPredicateCopy, secondPredicate);
    }

    @Test
    public void test_addressContainsSequence_returnsTrue() {
        Person personToTest = generatePersonWithAddress("Blk 22 Potato Street 29, #06-40, Singapore 123456");

        // Same address
        AddressContainsSequencePredicate predicate =
                new AddressContainsSequencePredicate("Blk 22 Potato Street 29, #06-40, Singapore 123456");
        assertTrue(predicate.test(personToTest));

        // Contains sequence
        predicate = new AddressContainsSequencePredicate("123456");
        assertTrue(predicate.test(personToTest));

        // Contains sequence
        predicate = new AddressContainsSequencePredicate("Blk 22 Potato Street 29, #06-40");
        assertTrue(predicate.test(personToTest));

        // Can find emails through spaces
        predicate = new AddressContainsSequencePredicate("ato Street 2");
        assertTrue(predicate.test(personToTest));

        // Mixed-case sequence
        predicate = new AddressContainsSequencePredicate("SINgAPORE 123456");
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_addressDoesNotContainSequence_returnsFalse() {
        Person personToTest = generatePersonWithAddress("Potato Street, Blk 123 #12-34, Singapore 123456");

        // Non-matching sequence
        AddressContainsSequencePredicate predicate = new AddressContainsSequencePredicate("Malaysia");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = new AddressContainsSequencePredicate("Blk 123a");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = new AddressContainsSequencePredicate("Potato Street, Blk 123 #12-34, Singapore 123456789");
        assertFalse(predicate.test(personToTest));

        // Keywords match name, phone, email, but does not match address
        predicate = new AddressContainsSequencePredicate("12345");
        assertFalse(predicate.test(new PersonBuilder().withName("12345").withPhone("12345")
                .withEmail("12345@gmail.com").withAddress("Clementi Street Singapore 88888").build()));
    }

    private Person generatePersonWithAddress(String address) {
        return new PersonBuilder().withAddress(address).build();
    }
}