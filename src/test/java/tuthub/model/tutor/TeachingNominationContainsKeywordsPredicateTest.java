package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tuthub.testutil.TutorBuilder;

public class TeachingNominationContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TeachingNominationContainKeywordsPredicate firstPredicate =
                new TeachingNominationContainKeywordsPredicate(firstPredicateKeywordList);
        TeachingNominationContainKeywordsPredicate secondPredicate =
                new TeachingNominationContainKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TeachingNominationContainKeywordsPredicate firstPredicateCopy =
                new TeachingNominationContainKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tutor -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_teachingNominationContainsKeywords_returnsTrue() {
        // One keyword
        TeachingNominationContainKeywordsPredicate predicate =
                new TeachingNominationContainKeywordsPredicate(Collections.singletonList("1"));
        assertTrue(predicate.test(new TutorBuilder().withTeachingNomination("1").build()));

        // Partial keywords
        predicate = new TeachingNominationContainKeywordsPredicate(Collections.singletonList("1"));
        assertTrue(predicate.test(new TutorBuilder().withTeachingNomination("10").build()));
    }

    @Test
    public void test_teachingNominationDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TeachingNominationContainKeywordsPredicate predicate =
                new TeachingNominationContainKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TutorBuilder().withTeachingNomination("1").build()));

        // Non-matching keyword
        predicate = new TeachingNominationContainKeywordsPredicate(Arrays.asList("2"));
        assertFalse(predicate.test(new TutorBuilder().withTeachingNomination("1").build()));
    }
}
