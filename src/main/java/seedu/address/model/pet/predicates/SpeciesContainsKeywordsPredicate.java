package seedu.address.model.pet.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.pet.Pet;

import java.util.List;
import java.util.function.Predicate;

public class SpeciesContainsKeywordsPredicate<T extends Pet> implements Predicate<T> {
    private final List<String> keywords;

    public SpeciesContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T pet) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(pet.getSpecies().getValue(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SpeciesContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SpeciesContainsKeywordsPredicate) other).keywords)); // state check
    }
}
