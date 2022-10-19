package seedu.address.model.pet.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalPets;

public class VaccinationStatusPredicateTest {
    @Test
    public void equals() {
        VaccinationStatusPredicate firstPredicate = new VaccinationStatusPredicate(true);
        VaccinationStatusPredicate secondPredicate = new VaccinationStatusPredicate(false);

        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        VaccinationStatusPredicate firstPredicateCopy = new VaccinationStatusPredicate(true);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_vaccinated_returnsTrue() {
        // One keyword
        VaccinationStatusPredicate predicate = new VaccinationStatusPredicate(true);
        assertTrue(predicate.test(TypicalPets.PLUM));
    }

    @Test
    public void test_notVaccinated_returnsFalse() {
        // Zero keywords
        VaccinationStatusPredicate predicate = new VaccinationStatusPredicate(false);
        assertFalse(predicate.test(TypicalPets.PLUM));
    }
}
