package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_STRING;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedPersonPredicate;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedPersonPredicateWithOnlyAddress;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedPersonPredicateWithOnlyEmail;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedPersonPredicateWithOnlyName;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedPersonPredicateWithOnlyPhone;
import static seedu.address.testutil.PredicateGeneratorUtil.generateCombinedPersonPredicateWithOnlyTags;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class CombinedPersonPredicateTest {

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
        CombinedPersonPredicate firstPredicate = generateCombinedPersonPredicate(name, phone, email, address, tag1);

        CombinedPersonPredicate firstPredicateCopy = generateCombinedPersonPredicate(name, phone, email, address, tag1);

        CombinedPersonPredicate differentPredicate = generateCombinedPersonPredicate(
                differentName, differentPhone, differentEmail, differentAddress, differentTagString);

        PredicateTestUtil.assertBasicEqualities(firstPredicate, firstPredicateCopy, differentPredicate);

        // Empty predicate not equals
        CombinedPersonPredicate testPredicate = generateCombinedPersonPredicate(
                EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
        assertNotEquals(testPredicate, firstPredicate);

        // Different name not equals
        testPredicate = generateCombinedPersonPredicate(differentName, phone, email, address, tag1);
        assertNotEquals(testPredicate, firstPredicate);

        // Different phone not equals
        testPredicate = generateCombinedPersonPredicate(name, differentPhone, email, address, tag1);
        assertNotEquals(testPredicate, firstPredicate);

        // Different email not equals
        testPredicate = generateCombinedPersonPredicate(name, phone, differentEmail, address, tag1);
        assertNotEquals(testPredicate, firstPredicate);

        // Different address not equals
        testPredicate = generateCombinedPersonPredicate(name, phone, email, differentAddress, tag1);
        assertNotEquals(testPredicate, firstPredicate);

        // Different tag not equals
        testPredicate = generateCombinedPersonPredicate(name, phone, email, address, differentTagString);
        assertNotEquals(testPredicate, firstPredicate);

        // Not full tag match not equals
        String extraTagString = tag1 + " " + "ExtraTag";
        testPredicate = generateCombinedPersonPredicate(name, phone, email, address, extraTagString);
        assertNotEquals(testPredicate, firstPredicate);
    }

    @Test
    public void test_personFufillsPredicateMixedFields_returnsTrue() {
        Person personToTest = new PersonBuilder().withName(name).withPhone(phone).withEmail(email)
                .withAddress(address).withTags(tag1, tag2).build();

        // Same search criteria
        CombinedPersonPredicate predicate = generateCombinedPersonPredicate(name, phone, email, address, combinedTags);
        assertTrue(predicate.test(personToTest));

        // Can find without full match (Except tags which needs to be word for word)
        predicate = generateCombinedPersonPredicate("John", "1234", "john", "123", tag1);
        assertTrue(predicate.test(personToTest));

        // Some fields empty, but matches
        predicate = generateCombinedPersonPredicate(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, tag2);
        assertTrue(predicate.test(personToTest));

        // No criteria
        predicate = generateCombinedPersonPredicate(
                EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_personNotFufillPredicateMixedFields_returnsFalse() {
        Person personToTest = new PersonBuilder().withName(name).withPhone(phone).withEmail(email)
                .withAddress(address).withTags(tag1, tag2).build();

        // Different search criteria
        CombinedPersonPredicate predicate = generateCombinedPersonPredicate(
                differentName, differentPhone, differentEmail, differentAddress, differentTagString);
        assertFalse(predicate.test(personToTest));

        // Different search criteria but some fields empty
        predicate = generateCombinedPersonPredicate(
                differentName, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
        assertFalse(predicate.test(personToTest));

        // Not match all tags
        String someTagsNotInPerson = tag1 + " " + tag2 + " " + "notInPerson";
        predicate = generateCombinedPersonPredicate(
                EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, someTagsNotInPerson);
        assertFalse(predicate.test(personToTest));

        // Matches all but 1 field
        predicate = generateCombinedPersonPredicate(name, phone, differentEmail, address, combinedTags);
        assertFalse(predicate.test(personToTest));

        // Tag not word for word match
        String similarLookingButDifferentTag = "Tag";
        predicate = generateCombinedPersonPredicate(
                EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, similarLookingButDifferentTag);
        assertFalse(predicate.test(personToTest));
    }

    @Test
    public void test_personFufillsPredicateNameOnly_returnsTrue() {
        String nameToTest = "Alice Yeoh";
        Person personToTest = new PersonBuilder().withName(nameToTest).build();

        // Same name
        CombinedPersonPredicate predicate = generateCombinedPersonPredicateWithOnlyName(nameToTest);
        assertTrue(predicate.test(personToTest));

        // Contains sequence
        predicate = generateCombinedPersonPredicateWithOnlyName("Ali");
        assertTrue(predicate.test(personToTest));

        // Can find names through spaces
        predicate = generateCombinedPersonPredicateWithOnlyName("ce Ye");
        assertTrue(predicate.test(personToTest));

        // Mixed-case sequence
        predicate = generateCombinedPersonPredicateWithOnlyName("iCE yEO");
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_personNotFufillPredicateNameOnly_returnsFalse() {
        String nameToTest = "John Doe";
        Person personToTest = new PersonBuilder().withName(nameToTest).build();

        // Non-matching sequence
        CombinedPersonPredicate predicate = generateCombinedPersonPredicateWithOnlyName("Alice");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = generateCombinedPersonPredicateWithOnlyName("Johnny");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = generateCombinedPersonPredicateWithOnlyName("John Does");
        assertFalse(predicate.test(personToTest));

        // Keywords match phone, email and address, but does not match name
        predicate = generateCombinedPersonPredicateWithOnlyName("John");
        assertFalse(predicate.test(new PersonBuilder().withName("12345").withPhone("12345")
                .withEmail("John@gmail.com").withAddress("John Street").build()));
    }

    @Test
    public void test_personFufillsPredicatePhoneOnly_returnsTrue() {
        String phoneToTest = "44556677";
        Person personToTest = new PersonBuilder().withPhone(phoneToTest).build();

        // Same phone
        CombinedPersonPredicate predicate = generateCombinedPersonPredicateWithOnlyPhone(phoneToTest);
        assertTrue(predicate.test(personToTest));

        // Contains sequence
        predicate = generateCombinedPersonPredicateWithOnlyPhone("5667");
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_personNotFufillPredicatePhoneOnly_returnsFalse() {
        String phoneToTest = "12345678";
        Person personToTest = new PersonBuilder().withPhone(phoneToTest).build();

        // Non-matching sequence
        CombinedPersonPredicate predicate = generateCombinedPersonPredicateWithOnlyPhone("99999");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = generateCombinedPersonPredicateWithOnlyPhone("24");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = generateCombinedPersonPredicateWithOnlyPhone("1234567890");
        assertFalse(predicate.test(personToTest));

        // Sequence match name, email and address, but does not match phone
        predicate = generateCombinedPersonPredicateWithOnlyPhone("12345");
        assertFalse(predicate.test(new PersonBuilder().withName("12345").withPhone("99999")
                .withEmail("12345@email.com").withAddress("12345 Street").build()));
    }

    @Test
    public void test_personFufillsPredicateEmailOnly_returnsTrue() {
        String emailToTest = "Alice@gmail.com";
        Person personToTest = new PersonBuilder().withEmail(emailToTest).build();

        // Same email
        CombinedPersonPredicate predicate = generateCombinedPersonPredicateWithOnlyEmail(emailToTest);
        assertTrue(predicate.test(personToTest));

        // Contains sequence
        predicate = generateCombinedPersonPredicateWithOnlyEmail("gmail");
        assertTrue(predicate.test(personToTest));

        // Can find non-alphanumeric characters
        predicate = generateCombinedPersonPredicateWithOnlyEmail("ce@gmail.c");
        assertTrue(predicate.test(personToTest));

        // Mixed-case sequence
        predicate = generateCombinedPersonPredicateWithOnlyEmail("ICE@GMail.cOM");
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_personNotFufillPredicateEmailOnly_returnsFalse() {
        String emailToTest = "John@gmail.com";
        Person personToTest = new PersonBuilder().withEmail(emailToTest).build();

        // Non-matching sequence
        CombinedPersonPredicate predicate = generateCombinedPersonPredicateWithOnlyEmail("google");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = generateCombinedPersonPredicateWithOnlyEmail("Johnny");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = generateCombinedPersonPredicateWithOnlyEmail("John@gmail.com.sg");
        assertFalse(predicate.test(personToTest));

        // Sequence match name, phone and address, but does not match email
        predicate = generateCombinedPersonPredicateWithOnlyEmail("John");
        assertFalse(predicate.test(new PersonBuilder().withName("John").withPhone("12345")
                .withEmail("Alice@gmail.com").withAddress("John Street").build()));
    }

    @Test
    public void test_personFufillsPredicateAddressOnly_returnsTrue() {
        String addressToTest = "Blk 22 Potato Street 29, #06-40, Singapore 123456";
        Person personToTest = new PersonBuilder().withAddress(addressToTest).build();

        // Same address
        CombinedPersonPredicate predicate =
                generateCombinedPersonPredicateWithOnlyAddress(addressToTest);
        assertTrue(predicate.test(personToTest));

        // Contains sequence
        predicate = generateCombinedPersonPredicateWithOnlyAddress("ato Str");
        assertTrue(predicate.test(personToTest));

        // Mixed-case sequence
        predicate = generateCombinedPersonPredicateWithOnlyAddress("SINgAPORE");
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_personNotFufillPredicateAddressOnly_returnsFalse() {
        String addressToTest = "Potato Street, Blk 123 #12-34, Singapore 123456";
        Person personToTest = new PersonBuilder().withAddress(addressToTest).build();

        // Non-matching sequence
        CombinedPersonPredicate predicate = generateCombinedPersonPredicateWithOnlyAddress("Malaysia");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = generateCombinedPersonPredicateWithOnlyAddress("Blk 123a");
        assertFalse(predicate.test(personToTest));

        // Incomplete match
        predicate = generateCombinedPersonPredicateWithOnlyAddress(
                "Potato Street, Blk 123 #12-34, Singapore 123456789");
        assertFalse(predicate.test(personToTest));

        // Sequence match name, phone, email, but does not match address
        predicate = generateCombinedPersonPredicateWithOnlyAddress("12345");
        assertFalse(predicate.test(new PersonBuilder().withName("12345").withPhone("12345")
                .withEmail("12345@gmail.com").withAddress("Clementi Street Singapore 88888").build()));
    }

    @Test
    public void test_personFufillsPredicateTagsOnly_returnsTrue() {
        String[] tagsToTest = new String[]{"Tag3", "Tag4", "Tag5"};
        Person personToTest = new PersonBuilder().withTags(tagsToTest).build();

        // Same tags
        CombinedPersonPredicate predicate = generateCombinedPersonPredicateWithOnlyTags(tagsToTest);
        assertTrue(predicate.test(personToTest));

        // Matches all search tags, mixed-case works
        predicate = generateCombinedPersonPredicateWithOnlyTags("TAG3", "tAg4");
        assertTrue(predicate.test(personToTest));
    }

    @Test
    public void test_personNotFufillPredicateTagsOnly_returnsFalse() {
        String[] tagsToTest = new String[]{"Tag6", "Tag7", "Tag8"};
        Person personToTest = new PersonBuilder().withTags(tagsToTest).build();

        // Non-matching tag
        CombinedPersonPredicate predicate = generateCombinedPersonPredicateWithOnlyTags("Hi");
        assertFalse(predicate.test(personToTest));

        // Different tag
        predicate = generateCombinedPersonPredicateWithOnlyTags("Tag60");
        assertFalse(predicate.test(personToTest));

        // Some tags not match
        predicate = generateCombinedPersonPredicateWithOnlyTags("Tag6", "Wrong", "Tag8");
        assertFalse(predicate.test(personToTest));

        // Tags match name and address, but does not match tag
        predicate = generateCombinedPersonPredicateWithOnlyTags("Tag");
        assertFalse(predicate.test(new PersonBuilder().withName("Tag").withEmail("Tag@gmail.com")
                .withAddress("Tag").withTags("SomethingElse").build()));
    }
}
