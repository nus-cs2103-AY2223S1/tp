package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class JobTitleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        JobTitleContainsKeywordsPredicate firstPredicate = new JobTitleContainsKeywordsPredicate(
                firstPredicateKeywordList);
        JobTitleContainsKeywordsPredicate secondPredicate = new JobTitleContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobTitleContainsKeywordsPredicate firstPredicateCopy = new JobTitleContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_jobTitleContainsKeywords_returnsTrue() {
        // One keyword
        JobTitleContainsKeywordsPredicate predicate = new JobTitleContainsKeywordsPredicate(
                Collections.singletonList("Data"));
        assertTrue(predicate.test(new PersonBuilder().withTitle("Data Science & Analytics Intern").build()));

        // Multiple matching keywords
        predicate = new JobTitleContainsKeywordsPredicate(Arrays.asList("Data", "Science", "Analytics"));
        assertTrue(predicate.test(new PersonBuilder().withTitle("Data Science & Analytics Intern").build()));

        // Only one matching keyword
        predicate = new JobTitleContainsKeywordsPredicate(Arrays.asList("Computer", "Science"));
        assertTrue(predicate.test(new PersonBuilder().withTitle("Data Science & Analytics Intern").build()));

        // Mixed-case keywords
        predicate = new JobTitleContainsKeywordsPredicate(Arrays.asList("CoMpuTeR", "sCieNcE"));
        assertTrue(predicate.test(new PersonBuilder().withTitle("Data Science & Analytics Intern").build()));
    }

    @Test
    public void test_jobTitleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        JobTitleContainsKeywordsPredicate predicate = new JobTitleContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTitle("Data Science & Analytics Intern").build()));

        // Non-matching keywords
        predicate = new JobTitleContainsKeywordsPredicate(Arrays.asList("Computer", "Engineering"));
        assertFalse(predicate.test(new PersonBuilder().withTitle("Data Science & Analytics Intern").build()));

        // Keywords are substrings
        predicate = new JobTitleContainsKeywordsPredicate(Arrays.asList("Anal", "In"));
        assertFalse(predicate.test(new PersonBuilder().withTitle("Data Science & Analytics Intern").build()));

        // Keywords match name, phone, email and address, but does not match JobTitle
        predicate = new JobTitleContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTitle("Data Intern").build()));
    }
}
