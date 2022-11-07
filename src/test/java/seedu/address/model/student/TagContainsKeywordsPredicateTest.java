package seedu.address.model.student;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.predicate.TagContainsKeywordsPredicate;
import seedu.address.testutil.StudentBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("python");
        List<String> secondPredicateKeywordList = Arrays.asList("java");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.singletonList("Java"));
        assertTrue(predicate.test(new StudentBuilder().withTags("Java").build()));

        // Mixed-case keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("pYthOn"));
        assertTrue(predicate.test(new StudentBuilder().withTags("Python").build()));

        // Zero keywords
        predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new StudentBuilder().withTags("Java").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("python", "java"));
        assertTrue(predicate.test(new StudentBuilder().withTags("python", "java").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("python", "java"));
        assertTrue(predicate.test(new StudentBuilder().withTags("python", "java", "begin").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Arrays.asList("Java"));
        assertFalse(predicate.test(new StudentBuilder().withTags("Python").build()));
    }
}
