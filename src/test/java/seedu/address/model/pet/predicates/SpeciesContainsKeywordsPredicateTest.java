package seedu.address.model.pet.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalPets;

public class SpeciesContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PetNameContainsKeywordsPredicate firstPredicate = new PetNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        PetNameContainsKeywordsPredicate secondPredicate = new PetNameContainsKeywordsPredicate(
                secondPredicateKeywordList);

        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PetNameContainsKeywordsPredicate firstPredicateCopy = new PetNameContainsKeywordsPredicate(
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
    public void test_speciesContainsKeywords_returnsTrue() {
        // One keyword
        SpeciesContainsKeywordsPredicate predicate = new SpeciesContainsKeywordsPredicate(
                Collections.singletonList("cat"));
        assertTrue(predicate.test(TypicalPets.PLUM));

        // Multiple keywords
        predicate = new SpeciesContainsKeywordsPredicate(Arrays.asList("cat", "mouse"));
        assertTrue(predicate.test(TypicalPets.PLUM));


        // Mixed-case keywords
        predicate = new SpeciesContainsKeywordsPredicate(Arrays.asList("cAt", "mOuse"));
        assertTrue(predicate.test(TypicalPets.PLUM));
    }

    @Test
    public void test_speciesDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SpeciesContainsKeywordsPredicate predicate = new SpeciesContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(TypicalPets.DOJA));

        // Non-matching keyword
        predicate = new SpeciesContainsKeywordsPredicate(Arrays.asList("dog"));
        assertFalse(predicate.test(TypicalPets.DOJA));

        // Keywords match phone, email and address, but does not match name
        predicate = new SpeciesContainsKeywordsPredicate(Arrays.asList("chinchilla", "deer", "gorilla"));
        assertFalse(predicate.test(TypicalPets.DOJA));
    }
}
