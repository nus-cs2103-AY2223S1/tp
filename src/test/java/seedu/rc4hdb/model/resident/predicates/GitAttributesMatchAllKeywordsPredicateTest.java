package seedu.rc4hdb.model.resident.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.BENSON;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.resident.ResidentStringDescriptor;
import seedu.rc4hdb.testutil.ResidentBuilder;
import seedu.rc4hdb.testutil.ResidentStringDescriptorBuilder;

public class GitAttributesMatchAllKeywordsPredicateTest {

    @Test
    public void equals() {

        ResidentStringDescriptor firstDescriptor = new ResidentStringDescriptorBuilder().withName(ALICE.getName().value)
                .withPhone(ALICE.getPhone().value).build();
        ResidentStringDescriptor secondDescriptor = new ResidentStringDescriptorBuilder()
                .withName(BENSON.getName().value).withPhone(BENSON.getPhone().value).build();

        AttributesMatchAllKeywordsPredicate firstPredicate =
                new AttributesMatchAllKeywordsPredicate(firstDescriptor);
        AttributesMatchAllKeywordsPredicate secondPredicate =
                new AttributesMatchAllKeywordsPredicate(secondDescriptor);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AttributesMatchAllKeywordsPredicate firstPredicateCopy =
                new AttributesMatchAllKeywordsPredicate(firstDescriptor);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_attributesMatchAllKeywords_returnsTrue() {
        // No keywords
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder().build());
        assertTrue(predicate.test(new ResidentBuilder().withName("Alice Bob").build()));

        // All keywords
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder(ALICE).build());
        assertTrue(predicate.test(ALICE));

        // some keywords
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withName(ALICE.getName().value).withPhone(ALICE.getPhone().value).build());
        assertTrue(predicate.test(ALICE));

        // name keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withName(ALICE.getName().value).build());
        assertTrue(predicate.test(ALICE));

        // phone keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withPhone(ALICE.getPhone().value).build());
        assertTrue(predicate.test(ALICE));

        // email keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withEmail(ALICE.getEmail().value).build());
        assertTrue(predicate.test(ALICE));

        // matric keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withMatricNumber(ALICE.getMatricNumber().value).build());
        assertTrue(predicate.test(ALICE));

        // house keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withName(ALICE.getHouse().value).build());
        assertTrue(predicate.test(ALICE));

        // gender keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withGender(ALICE.getGender().value).build());
        assertTrue(predicate.test(ALICE));

        // tags keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withTags(ALICE.getTags()).build());
        assertTrue(predicate.test(ALICE));

        // room keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withRoom(ALICE.getRoom().value).build());
        assertTrue(predicate.test(ALICE));
    }

    @Test
    public void test_attributesMatchAllKeywords_returnsFalse() {
        // All keywords
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder(ALICE).build());
        assertFalse(predicate.test(BENSON));

        // some keywords
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withName(ALICE.getName().value).withPhone(ALICE.getPhone().value).build());
        assertFalse(predicate.test(BENSON));

        // name keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withName(ALICE.getName().value).build());
        assertFalse(predicate.test(BENSON));

        // phone keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withPhone(ALICE.getPhone().value).build());
        assertFalse(predicate.test(BENSON));

        // email keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withEmail(ALICE.getEmail().value).build());
        assertFalse(predicate.test(BENSON));

        // matric keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withMatricNumber(ALICE.getMatricNumber().value).build());
        assertFalse(predicate.test(BENSON));

        // house keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withName(ALICE.getHouse().value).build());
        assertFalse(predicate.test(BENSON));

        // gender keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withGender(ALICE.getGender().value).build());
        assertFalse(predicate.test(BENSON));

        // tags keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withTags(ALICE.getTags()).build());
        assertFalse(predicate.test(BENSON));

        // room keyword only
        predicate = new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder()
                .withRoom(ALICE.getRoom().value).build());
        assertFalse(predicate.test(BENSON));
    }
}
