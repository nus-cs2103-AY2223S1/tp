package tracko.model.items;

import tracko.commons.util.StringUtil;
import tracko.model.order.NameContainsKeywordsPredicate;

import java.util.List;
import java.util.function.Predicate;

public class ItemContainsKeywordsPredicate implements Predicate<Item> {
    private final List<String> keywords;

    public ItemContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Item item) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(item.getItemName().itemName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ItemContainsKeywordsPredicate) other).keywords)); // state check
    }

}
