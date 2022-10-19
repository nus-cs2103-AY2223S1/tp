package seedu.address.model.pet.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalPets;

public class ColorContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ColorContainsKeywordsPredicate firstPredicate = new ColorContainsKeywordsPredicate(
                firstPredicateKeywordList);
        ColorContainsKeywordsPredicate secondPredicate = new ColorContainsKeywordsPredicate(
                secondPredicateKeywordList);

        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ColorContainsKeywordsPredicate firstPredicateCopy = new ColorContainsKeywordsPredicate(
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
    public void test_colorContainsKeywords_returnsTrue() {
        // One keyword
        ColorContainsKeywordsPredicate predicate = new ColorContainsKeywordsPredicate(
                Collections.singletonList("brown"));
        assertTrue(predicate.test(TypicalPets.PLUM));

        // Multiple keywords
        predicate = new ColorContainsKeywordsPredicate(Arrays.asList("brown", "black"));
        assertTrue(predicate.test(TypicalPets.PLUM));


        // Mixed-case keywords
        predicate = new ColorContainsKeywordsPredicate(Arrays.asList("bROwn", "bLacK"));
        assertTrue(predicate.test(TypicalPets.PLUM));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ColorContainsKeywordsPredicate predicate = new ColorContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(TypicalPets.DOJA));

        // Non-matching keyword
        predicate = new ColorContainsKeywordsPredicate(Arrays.asList("blue"));
        assertFalse(predicate.test(TypicalPets.DOJA));

        // Keywords match phone, email and address, but does not match name
        predicate = new ColorContainsKeywordsPredicate(Arrays.asList("blue", "green", "orange"));
        assertFalse(predicate.test(TypicalPets.DOJA));
    }
}
