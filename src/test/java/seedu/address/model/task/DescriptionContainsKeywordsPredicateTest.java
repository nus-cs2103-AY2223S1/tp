package seedu.address.model.task;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TaskBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DescriptionContainsKeywordsPredicateTest {

//    @Test
//    public void equals() {
//        List<String> firstPredicateKeywordList = Collections.singletonList("first");
//        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
//
//        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
//        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);
//
//        // same object -> returns true
//        assertTrue(firstPredicate.equals(firstPredicate));
//
//        // same values -> returns true
//        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
//        assertTrue(firstPredicate.equals(firstPredicateCopy));
//
//        // different types -> returns false
//        assertFalse(firstPredicate.equals(1));
//
//        // null -> returns false
//        assertFalse(firstPredicate.equals(null));
//
//        // different person -> returns false
//        assertFalse(firstPredicate.equals(secondPredicate));
//    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // One keyword
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new TaskBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new TaskBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new TaskBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new TaskBuilder().withName("Alice Bob").build()));
    }

//    @Test
//    public void test_nameDoesNotContainKeywords_returnsFalse() {
//        // Zero keywords
//        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
//        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));
//
//        // Non-matching keyword
//        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
//        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
//
//        // Keywords match phone, email and address, but does not match name
//        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
//        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
//                .withEmail("alice@email.com").withAddress("Main Street").build()));
//    }
}
