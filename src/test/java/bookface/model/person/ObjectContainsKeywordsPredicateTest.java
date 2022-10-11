package bookface.model.person;

import static bookface.testutil.Assert.assertThrows;
import static bookface.testutil.TestUtil.preparePredicateToCheckPersonForPartialWordIgnoreCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import bookface.model.ObjectContainsKeywordsPredicate;
import bookface.testutil.PersonBuilder;

public class ObjectContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        Function<String, Predicate<? super String>> function = y -> x -> false;

        ObjectContainsKeywordsPredicate<String, String> firstPredicate =
                new ObjectContainsKeywordsPredicate<>(firstPredicateKeywordList, function);
        ObjectContainsKeywordsPredicate<String, String> secondPredicate =
                new ObjectContainsKeywordsPredicate<>(secondPredicateKeywordList, function);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        ObjectContainsKeywordsPredicate<String, String> firstPredicateCopy =
                new ObjectContainsKeywordsPredicate<>(firstPredicateKeywordList, function);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ObjectContainsKeywordsPredicate<Person, String> predicate =
                preparePredicateToCheckPersonForPartialWordIgnoreCase(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = preparePredicateToCheckPersonForPartialWordIgnoreCase(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = preparePredicateToCheckPersonForPartialWordIgnoreCase(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate =
                preparePredicateToCheckPersonForPartialWordIgnoreCase(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ObjectContainsKeywordsPredicate<Person, String> predicate =
                preparePredicateToCheckPersonForPartialWordIgnoreCase("");
        final ObjectContainsKeywordsPredicate<Person, String> finalPredicate = predicate;
        assertThrows(IllegalArgumentException.class, () -> finalPredicate
                .test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = preparePredicateToCheckPersonForPartialWordIgnoreCase("Carol");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email, but does not match name
        predicate = preparePredicateToCheckPersonForPartialWordIgnoreCase("12345 alice@email.com");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }
}
