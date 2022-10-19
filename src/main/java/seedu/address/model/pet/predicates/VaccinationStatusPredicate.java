package seedu.address.model.pet.predicates;

import java.util.function.Predicate;

import seedu.address.model.pet.Pet;

/**
 * Tests that a {@code Pet}'s {@code VaccinationStatus} matches any of the keywords given.
 */
public class VaccinationStatusPredicate<T extends Pet> implements Predicate<T> {
    private final boolean status;

    /**
     * Creates a {@code VaccinationStatusPredicate} based on status given.
     */
    public VaccinationStatusPredicate(boolean status) {
        this.status = status;
    }

    @Override
    public boolean test(T pet) {
        return pet.getVaccinationStatus().getVaccinationStatus() == status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VaccinationStatusPredicate // instanceof handles nulls
                && ((VaccinationStatusPredicate) other).status == status); // state check
    }
}
