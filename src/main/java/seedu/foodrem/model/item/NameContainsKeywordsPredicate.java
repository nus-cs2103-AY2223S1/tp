package seedu.foodrem.model.item;

import java.util.List;
import java.util.function.Predicate;

import seedu.foodrem.commons.util.StringUtil;

/**
 * Tests that a {@code Item}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Item> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Item item) {
        return keywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(item.getName().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof NameContainsKeywordsPredicate
            && keywords.equals(((NameContainsKeywordsPredicate) other).keywords));
    }
}
