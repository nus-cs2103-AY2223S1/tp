package seedu.address.model.pet.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.pet.Pet;

/**
 * Tests that a {@code Price}'s {@code Price} matches any of the keywords given.
 */
public class PriceContainsKeywordsPredicate<T extends Pet> implements Predicate<T> {
    private final List<Double> keywords;

    /**
     * Creates a {@code PriceContainsKeywordsPredicate} based on keywords given.
     */
    public PriceContainsKeywordsPredicate(List<Double> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T pet) {
        return keywords.stream()
                .anyMatch(keyword -> keyword.equals(pet.getPrice().getPrice()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PriceContainsKeywordsPredicate) other).keywords)); // state check
    }
}
