package seedu.address.model.property;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Tests that a {@code Property}'s {@code Name} matches any of the keywords given.
 */
public class PropertyNameContainsKeywordsPredicate implements Predicate<Property> {
    private final List<String> keywords;

    public PropertyNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Property property) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(property.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PropertyNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
