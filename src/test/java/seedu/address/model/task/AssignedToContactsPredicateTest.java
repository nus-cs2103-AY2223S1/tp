package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class AssignedToContactsPredicateTest {
    @Test
    public void equals() {
        List<Contact> firstPredicateContact = List.of(new Contact("first"));
        List<Contact> secondPredicateContact = List.of(new Contact("second"));

        AssignedToContactsPredicate firstPredicate = new AssignedToContactsPredicate(firstPredicateContact);
        AssignedToContactsPredicate secondPredicate = new AssignedToContactsPredicate(secondPredicateContact);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AssignedToContactsPredicate firstPredicateCopy = new AssignedToContactsPredicate(firstPredicateContact);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different teammate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        List<Contact> singleContact = List.of(new Contact("Thiago Alcantara"));

        // Matching all
        AssignedToContactsPredicate predicate = new AssignedToContactsPredicate(singleContact);
        assertTrue(predicate.test(new TaskBuilder().withTitle("Test").withContacts("Thiago Alcantara").build()));


        // Subset
        assertTrue(
                predicate.test(
                        new TaskBuilder().withTitle("Test").withContacts("Thiago Alcantara", "Bobby Dazzler").build()
                )
        );
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        List<Contact> singleContact = List.of(new Contact("Thiago Alcantara"));

        // Matching None
        AssignedToContactsPredicate predicate = new AssignedToContactsPredicate(singleContact);
        assertFalse(predicate.test(new TaskBuilder().withTitle("Test").withContacts("Mo Salah").build()));
    }

}
