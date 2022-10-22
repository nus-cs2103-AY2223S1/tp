package seedu.condonery.model.property;

import java.util.List;
import java.util.function.Predicate;

import seedu.condonery.commons.util.StringUtil;

/**
 * Tests that a {@code Property}'s {@code Tags} matches any of the keywords given.
 */
public class PropertyTagContainsKeywordsPredicate implements Predicate<Property> {
    private final List<String> keywords;

    public PropertyTagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Property property) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(property.getTagNames(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyTagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PropertyTagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
