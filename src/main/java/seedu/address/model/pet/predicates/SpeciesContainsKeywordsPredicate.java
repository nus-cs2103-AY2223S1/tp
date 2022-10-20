package seedu.address.model.pet.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.pet.Pet;

/**
 * Tests that a {@code Pet}'s {@code Species} matches any of the keywords given.
 */
public class SpeciesContainsKeywordsPredicate<T extends Pet> implements Predicate<T> {
    private final List<String> keywords;

    /**
     * Creates a {@code SpeciesContainsKeywordsPredicate} based on keywords given.
     */
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
