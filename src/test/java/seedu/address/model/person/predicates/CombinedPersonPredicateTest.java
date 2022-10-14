package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PredicateGeneratorUtil;

class CombinedPersonPredicateTest {

    private final String empty = "";

    private final String name = "John";
    private final String phone = "12345678";
    private final String email = "john@gmail.com";
    private final String address = "Blk 123 Code Road";
    private final String tag1 = "Tag1";
    private final String tag2 = "Tag2";
    private final String combinedTags = tag1 + " " + tag2;

    private final String differentName = "Max";
    private final String differentPhone = "999999";
    private final String differentEmail = "max@gmail.com";
    private final String differentAddress = "Max road";
    private final String differentTagString = "Tag3";
    @Test
    public void equals() {
        CombinedPersonPredicate firstPredicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                name, phone, email, address, tag1);

        CombinedPersonPredicate firstPredicateCopy = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                name, phone, email, address, tag1);

        CombinedPersonPredicate differentPredicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                differentName, differentPhone, differentEmail, differentAddress, differentTagString);

        PredicateTestUtil.assertBasicEqualities(firstPredicate, firstPredicateCopy, differentPredicate);

        // Empty predicate not equals
        CombinedPersonPredicate testPredicate =
                PredicateGeneratorUtil.generateCombinedPersonPredicate(empty, empty, empty, empty, empty);
        assertNotEquals(testPredicate, firstPredicate);

        // Different name not equals
        testPredicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                differentName, phone, email, address, tag1);
        assertNotEquals(testPredicate, firstPredicate);

        // Different phone not equals
        testPredicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                name, differentPhone, email, address, tag1);
        assertNotEquals(testPredicate, firstPredicate);

        // Different email not equals
        testPredicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                name, phone, differentEmail, address, tag1);
        assertNotEquals(testPredicate, firstPredicate);

        // Different address not equals
        testPredicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                name, phone, email, differentAddress, tag1);
        assertNotEquals(testPredicate, firstPredicate);

        // Different tag not equals
        testPredicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                name, phone, email, address, differentTagString);
        assertNotEquals(testPredicate, firstPredicate);

        // Not full tag match not equals
        String extraTagString = tag1 + " " + "ExtraTag";
        testPredicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                name, phone, email, address, extraTagString);
        assertNotEquals(testPredicate, firstPredicate);
    }

    @Test
    public void test_personFufillsPredicate_returnsTrue() {
        Person personToTest = new PersonBuilder().withName(name).withPhone(phone).withEmail(email)
                .withAddress(address).withTags(tag1, tag2).build();

        // Same search criteria
        CombinedPersonPredicate predicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                name, phone, email, address, combinedTags);
        assertTrue(predicate.test(personToTest));

        // Can find without full match (Except tags which needs to be word for word)
        predicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                "John", "1234", "john", "123", tag1);
        assertTrue(predicate.test(personToTest));

        // Some fields empty, but matches
        predicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(empty, empty, empty, empty, tag2);
        assertTrue(predicate.test(personToTest));

        // No criteria
        predicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(empty, empty, empty, empty, empty);
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_personNotFufillPredicate_returnsFalse() {
        Person personToTest = new PersonBuilder().withName(name).withPhone(phone).withEmail(email)
                .withAddress(address).withTags(tag1, tag2).build();

        // Different search criteria
        CombinedPersonPredicate predicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                differentName, differentPhone, differentEmail, differentAddress, differentTagString);
        assertFalse(predicate.test(personToTest));

        // Different search criteria but some fields empty
        predicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                differentName, empty, empty, empty, empty);
        assertFalse(predicate.test(personToTest));

        // Not match all tags
        String someTagsNotInPerson = tag1 + " " + tag2 + " " + "notInPerson";
        predicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                empty, empty, empty, empty, someTagsNotInPerson);
        assertFalse(predicate.test(personToTest));

        // Matches all but 1 field
        predicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                name, phone, differentEmail, address, combinedTags);
        assertFalse(predicate.test(personToTest));

        // Tag not word for word match
        String similarLookingButDifferentTag = "Tag";
        predicate = PredicateGeneratorUtil.generateCombinedPersonPredicate(
                empty, empty, empty, empty, similarLookingButDifferentTag);
        assertFalse(predicate.test(personToTest));
    }
}