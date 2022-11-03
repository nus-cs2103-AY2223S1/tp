package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class MajorContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MajorContainsKeywordsPredicate firstPredicate = new MajorContainsKeywordsPredicate(firstPredicateKeywordList);
        MajorContainsKeywordsPredicate secondPredicate = new MajorContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MajorContainsKeywordsPredicate firstPredicateCopy = new MajorContainsKeywordsPredicate(
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
    public void test_majorContainsKeywords_returnsTrue() {
        // One keyword
        MajorContainsKeywordsPredicate predicate = new MajorContainsKeywordsPredicate(
                Collections.singletonList("Computer"));
        assertTrue(predicate.test(new PersonBuilder().withMajor("Computer Science").build()));

        // Multiple matching keyword
        predicate = new MajorContainsKeywordsPredicate(Arrays.asList("Computer", "Science"));
        assertTrue(predicate.test(new PersonBuilder().withMajor("Computer Science").build()));

        // Only one matching keyword
        predicate = new MajorContainsKeywordsPredicate(Arrays.asList("Computer", "Engineering"));
        assertTrue(predicate.test(new PersonBuilder().withMajor("Computer Science").build()));

        // Mixed-case keywords
        predicate = new MajorContainsKeywordsPredicate(Arrays.asList("CoMpuTEr", "EnginEErIng"));
        assertTrue(predicate.test(new PersonBuilder().withMajor("Computer Science").build()));
    }

    @Test
    public void test_majorDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        MajorContainsKeywordsPredicate predicate = new MajorContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withMajor("Computer Science").build()));

        // Non-matching keyword
        predicate = new MajorContainsKeywordsPredicate(Arrays.asList("Medicine"));
        assertFalse(predicate.test(new PersonBuilder().withMajor("Computer Science").build()));

        // Keywords match name, phone, email and address, but does not match Major
        predicate = new MajorContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withMajor("Dentistry").build()));
    }
}
