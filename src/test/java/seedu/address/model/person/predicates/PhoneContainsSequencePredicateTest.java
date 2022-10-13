package seedu.address.model.person.predicates;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
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
    void testEquals() {
    }

    private Person generatePersonWithPhone(String phone) {
        return new PersonBuilder().withPhone(phone).build();
    }
}