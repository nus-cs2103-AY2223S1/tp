package seedu.condonery.model.property;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Tags} matches any of the keywords given.
 */
public class PropertyStatusContainsKeywordsPredicate implements Predicate<Property> {
    private final List<String> keywords;

    public PropertyStatusContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Property property) {
        return keywords.stream()
                .anyMatch(keyword -> keyword.equalsIgnoreCase(property.getPropertyStatusEnum().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyStatusContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PropertyStatusContainsKeywordsPredicate) other).keywords)); // state check
    }

}
