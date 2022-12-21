package seedu.masslinkers.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.testutil.StudentBuilder;

public class DetailsContainKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DetailsContainKeywordsPredicate firstPredicate = new DetailsContainKeywordsPredicate(
                firstPredicateKeywordList);
        DetailsContainKeywordsPredicate secondPredicate = new DetailsContainKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DetailsContainKeywordsPredicate firstPredicateCopy = new DetailsContainKeywordsPredicate(
                firstPredicateKeywordList);
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
        DetailsContainKeywordsPredicate predicate = new DetailsContainKeywordsPredicate(
                Collections.singletonList("Alice"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new DetailsContainKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new DetailsContainKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new DetailsContainKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email, address and github username
        predicate = new DetailsContainKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street",
                "AliceInTheWonderLand"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTelegram("MainStreet")
                .withGitHub("AliceInTheWonderLand").build()));

        // Keywords matching github username
        predicate = new DetailsContainKeywordsPredicate(Arrays.asList("aliceinthewonderland"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTelegram("MainStreet")
                .withGitHub("AliceInTheWonderLand").build()));

        // Keywords containing partially-matching github username
        predicate = new DetailsContainKeywordsPredicate(Arrays.asList("wonderland"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTelegram("MainStreet")
                .withGitHub("AliceInTheWonderLand").build()));

        // Keywords containing partially-matching telegram handle
        predicate = new DetailsContainKeywordsPredicate(Arrays.asList("shuai"));
        assertTrue(predicate.test(new StudentBuilder().withName("Handsome").withPhone("999769")
                .withEmail("lengzai@email.com").withTelegram("woShiDaShuaiGe")
                .withGitHub("handsomelengzai888").build()));

        // Keywords containing partially-matching telegram handle
        predicate = new DetailsContainKeywordsPredicate(Arrays.asList("999"));
        assertTrue(predicate.test(new StudentBuilder().withName("mata").withPhone("180099969")
                .withEmail("polis@email.com").withTelegram("polis")
                .withGitHub("iamPolis").build()));

    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DetailsContainKeywordsPredicate predicate = new DetailsContainKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new DetailsContainKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Array containing no matching keyword
        predicate = new DetailsContainKeywordsPredicate(Arrays.asList("jonasGoh"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTelegram("MainStreet")
                .withGitHub("AliceInTheWonderLand").build()));

        // Array containing no matching keyword
        predicate = new DetailsContainKeywordsPredicate(Arrays.asList("999"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTelegram("MainStreet")
                .withGitHub("AliceInTheWonderLand").build()));

    }
}
