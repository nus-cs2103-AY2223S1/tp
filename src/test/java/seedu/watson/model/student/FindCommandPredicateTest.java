package seedu.watson.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.watson.testutil.StudentBuilder;

public class FindCommandPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FindCommandPredicate firstPredicate = new FindCommandPredicate(firstPredicateKeywordList);
        FindCommandPredicate secondPredicate = new FindCommandPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindCommandPredicate firstPredicateCopy = new FindCommandPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        List<String> predicateKeywordList = new ArrayList<>();
        Collections.addAll(predicateKeywordList, "Alice", "", "");
        List<String> unmodifiablePredicateKeywordList = Collections.unmodifiableList(predicateKeywordList);
        FindCommandPredicate predicate = new FindCommandPredicate(unmodifiablePredicateKeywordList);
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new FindCommandPredicate(Arrays.asList(new String[]{"Alice Bob", "", ""}));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new FindCommandPredicate(Arrays.asList(new String[]{"Bob Carol", "", ""}));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new FindCommandPredicate(Arrays.asList(new String[]{"aLIce bOB", "", ""}));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FindCommandPredicate predicate = new FindCommandPredicate(Arrays.asList(new String[]{"", "", ""}));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new FindCommandPredicate(Arrays.asList(new String[]{"Carol", "", ""}));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob").build()));
    }
}
