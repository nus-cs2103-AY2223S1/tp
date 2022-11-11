package seedu.studmap.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.studmap.testutil.StudentBuilder;


public class TagContainsKeywordsPredicateTest {

    @Test
    public void test_tagContainsKeywords_returnTrue() {
        //One keyword
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("friends"));
        assertTrue(predicate.test(new StudentBuilder().withTags("friends").build()));

        //Only matching one keyword
        TagContainsKeywordsPredicate predicate1 =
                new TagContainsKeywordsPredicate(Arrays.asList("friends", "colleagues"));
        assertTrue(predicate1.test(new StudentBuilder().withTags("friends", "family").build()));

        //Check case sensitivity
        TagContainsKeywordsPredicate predicate2 =
                new TagContainsKeywordsPredicate(Arrays.asList("Friends", "Family"));
        assertTrue(predicate2.test(new StudentBuilder().withTags("friends").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnFalse() {
        //Empty input
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withTags("friends").build()));

        //No matching input
        TagContainsKeywordsPredicate predicate1 =
                new TagContainsKeywordsPredicate(Collections.singletonList("home"));
        assertFalse(predicate1.test(new StudentBuilder().withTags("family").build()));
    }
}
