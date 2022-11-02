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
        //keyword is the smaller one
        //the one that goes inside test is all the task in the task list.
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(Collections.singletonList("task"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDescription("task").build()));

        //One keyword mixed case
         predicate = new DescriptionContainsKeywordsPredicate(Collections.singletonList("task"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDescription("TAsK").build()));

        // Multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("task", "one"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDescription("TasK ONe").build()));

        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("task", "one"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDescription("TasK Two Task ONe").build()));

        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("as"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDescription("TasK Two Task ONe").build()));

    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withTaskDescription("Task one").build()));

        // Non-matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("task"));
        assertFalse(predicate.test(new TaskBuilder().withTaskDescription("homework paper").build()));

    }
}
