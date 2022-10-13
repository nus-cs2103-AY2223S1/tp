package seedu.address.model.pet;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Pet}'s {@code Name} matches any of the keywords given.
 */
public class PetNameContainsKeywordsPredicate<T extends Pet> implements Predicate<T> {
    private final List<String> keywords;

    public PetNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T pet) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(pet.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PetNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PetNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
