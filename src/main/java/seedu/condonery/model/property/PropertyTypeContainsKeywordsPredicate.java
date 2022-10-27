package seedu.condonery.model.property;

import java.util.List;
import java.util.function.Predicate;

import seedu.condonery.commons.util.StringUtil;

/**
 * Tests that a {@code Property}'s {@code Tags} matches any of the keywords given.
 */
public class PropertyTypeContainsKeywordsPredicate implements Predicate<Property> {
    private final List<String> keywords;

    public PropertyTypeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Property property) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(property.getPropertyTypeEnum().name(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyTypeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PropertyTypeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
