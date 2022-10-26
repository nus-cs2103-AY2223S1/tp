package seedu.address.model.pet.predicates;

import java.util.function.Predicate;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.model.pet.Pet;

/**
 * Tests that a {@code Pet}'s {@code UniqueId} matches the Unique Id given.
 */
public class UniquePetIdPredicate<T extends Pet> implements Predicate<T> {
    private final UniqueId uniqueId;

    public UniquePetIdPredicate(UniqueId uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public boolean test(T pet) {
        String petId = uniqueId.getIdToString();
        boolean isPet = pet.getId().getIdToString().equals(petId);
        return isPet;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePetIdPredicate // instanceof handles nulls
                && uniqueId.equals(((UniquePetIdPredicate) other).uniqueId)); // state check
    }
}
