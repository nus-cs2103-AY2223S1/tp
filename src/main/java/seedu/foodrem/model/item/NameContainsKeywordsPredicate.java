package seedu.foodrem.model.item;

import java.util.List;
import java.util.function.Predicate;

import seedu.foodrem.commons.util.StringUtil;

/**
 * Tests that a {@code Item}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Item> {
    private final List<String> keywords;

    /**
     * Constructs a {@link NameContainsKeywordsPredicate}.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if the item name matches all the keywords.
     */
    @Override
    public boolean test(Item item) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsSubstringIgnoreCase(item.getName().toString(), keyword));
    }

    /**
     * Returns {@code true} if both {@link NameContainsKeywordsPredicate} have the same keywords.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof NameContainsKeywordsPredicate
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords));
    }
}
