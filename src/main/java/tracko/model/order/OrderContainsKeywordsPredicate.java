package tracko.model.order;

import java.util.List;
import java.util.function.Predicate;

import tracko.commons.util.StringUtil;

/**
 * Tests that a {@code Order}'s {@code Name} matches any of the keywords given.
 */
public class OrderContainsKeywordsPredicate implements Predicate<Order> {
    private final List<String> keywords;

    public OrderContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Order order) {
        return keywords.stream()
                .anyMatch(keyword -> {
                    for (ItemQuantityPair i : order.getItemList()) {
                        if (StringUtil.containsWordIgnoreCase(i.getItemName(), keyword)) {
                            return true;
                        }
                    }
                    return false;
                }
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((OrderContainsKeywordsPredicate) other).keywords)); // state check
    }

}
