package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class DetailsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DetailsContainsKeywordsPredicate firstPredicate = new DetailsContainsKeywordsPredicate(
                firstPredicateKeywordList);
        DetailsContainsKeywordsPredicate secondPredicate = new DetailsContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DetailsContainsKeywordsPredicate firstPredicateCopy = new DetailsContainsKeywordsPredicate(
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
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(
                Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email, address and github username
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street",
                "AliceInTheWonderLand"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTelegram("Main Street")
                .withGitHub("AliceInTheWonderLand").build()));

        // Keywords matching github username
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("aliceinthewonderland"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTelegram("Main Street")
                .withGitHub("AliceInTheWonderLand").build()));

        // Keywords containing partially-matching github username
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("wonderland"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTelegram("Main Street")
                .withGitHub("AliceInTheWonderLand").build()));

        // Keywords containing partially-matching telegram handle
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("shuai"));
        assertTrue(predicate.test(new PersonBuilder().withName("Handsome").withPhone("999769")
                .withEmail("lengzai@email.com").withTelegram("woShiDaShuaiGe")
                .withGitHub("handsomelengzai888").build()));

        // Keywords containing partially-matching telegram handle
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("999"));
        assertTrue(predicate.test(new PersonBuilder().withName("mata").withPhone("180099969")
                .withEmail("polis@email.com").withTelegram("polis")
                .withGitHub("iamPolis").build()));

    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Array containing no matching keyword
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("jonasGoh"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTelegram("Main Street")
                .withGitHub("AliceInTheWonderLand").build()));

        // Array containing no matching keyword
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("999"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTelegram("Main Street")
                .withGitHub("AliceInTheWonderLand").build()));

    }
}
