package seedu.address.model.pet.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalPets;

public class UniquePetIdPredicateTest {
    @Test
    public void equals() {
        UniquePetIdPredicate firstPredicate = new UniquePetIdPredicate(TypicalPets.DOJA.getId());
        UniquePetIdPredicate secondPredicate = new UniquePetIdPredicate(TypicalPets.PLUM.getId());

        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        UniquePetIdPredicate firstPredicateCopy = new UniquePetIdPredicate(TypicalPets.DOJA.getId());
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
