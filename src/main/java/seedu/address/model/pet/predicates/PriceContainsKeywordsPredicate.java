package seedu.address.model.pet.predicates;

import seedu.address.model.pet.Pet;

import java.util.List;
import java.util.function.Predicate;

public class PriceContainsKeywordsPredicate<T extends Pet> implements Predicate<T> {
    private final List<Double> keywords;

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
