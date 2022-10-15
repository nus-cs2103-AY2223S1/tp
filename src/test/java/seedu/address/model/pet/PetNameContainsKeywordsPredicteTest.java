package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalPets;

public class PetNameContainsKeywordsPredicteTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PetNameContainsKeywordsPredicate firstPredicate = new PetNameContainsKeywordsPredicate(firstPredicateKeywordList);
        PetNameContainsKeywordsPredicate secondPredicate = new PetNameContainsKeywordsPredicate(secondPredicateKeywordList);

        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PetNameContainsKeywordsPredicate firstPredicateCopy = new PetNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_petNameContainsKeywords_returnsTrue() {
        // One keyword
        PetNameContainsKeywordsPredicate predicate = new PetNameContainsKeywordsPredicate(Collections.singletonList("Plum"));
        assertTrue(predicate.test(TypicalPets.PLUM));

        // Multiple keywords
        predicate = new PetNameContainsKeywordsPredicate(Arrays.asList("Plum", "Ashy"));
        assertTrue(predicate.test(TypicalPets.PLUM));


        // Mixed-case keywords
        predicate = new PetNameContainsKeywordsPredicate(Arrays.asList("pLUm", "aShy"));
        assertTrue(predicate.test(TypicalPets.PLUM));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PetNameContainsKeywordsPredicate predicate = new PetNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(TypicalPets.DOJA));

        // Non-matching keyword
        predicate = new PetNameContainsKeywordsPredicate(Arrays.asList("Putu"));
        assertFalse(predicate.test(TypicalPets.DOJA));

        // Keywords match phone, email and address, but does not match name
        predicate = new PetNameContainsKeywordsPredicate(Arrays.asList("Putu", "Ashy", "Plum"));
        assertFalse(predicate.test(TypicalPets.DOJA));
    }
}
