package seedu.address.model.pet.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.pet.Pet;

import java.util.List;
import java.util.function.Predicate;

public class VaccinationStatusPredicate<T extends Pet> implements Predicate<T> {
    private final boolean status;

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
