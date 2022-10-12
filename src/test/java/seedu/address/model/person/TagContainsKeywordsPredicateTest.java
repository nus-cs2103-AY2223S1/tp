package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void test_tagContainsKeywords_returnTrue() {
        //One keyword
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("friends"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        //Only matching one keyword
        TagContainsKeywordsPredicate predicate1 =
                new TagContainsKeywordsPredicate(Arrays.asList("friends", "colleagues"));
        assertTrue(predicate1.test(new PersonBuilder().withTags("friends", "family").build()));

        //Check case sensitivity
        TagContainsKeywordsPredicate predicate2 =
                new TagContainsKeywordsPredicate(Arrays.asList("Friends", "Family"));
        assertTrue(predicate2.test(new PersonBuilder().withTags("friends").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnFalse() {
        //Empty input
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").build()));

        //No matching input
        TagContainsKeywordsPredicate predicate1 =
                new TagContainsKeywordsPredicate(Collections.singletonList("home"));
        assertFalse(predicate1.test(new PersonBuilder().withTags("family").build()));
    }
}
