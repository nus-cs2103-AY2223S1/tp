package tracko.model.item;

import java.util.List;
import java.util.function.Predicate;

import tracko.commons.util.StringUtil;

/**
 * Tests that a {@code Item}'s {@code ItemName} matches any of the keywords given.
 */
public class ItemContainsKeywordsPredicate implements Predicate<InventoryItem> {
    private final List<String> keywords;

    public ItemContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(InventoryItem inventoryItem) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(inventoryItem.getItemName().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ItemContainsKeywordsPredicate) other).keywords)); // state check
    }

}
