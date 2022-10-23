package tracko.model.order;

import java.util.List;
import java.util.function.Predicate;

import tracko.commons.util.StringUtil;

/**
 * Tests that a {@code Order}'s {@code Name} matches any of the keywords given.
 */
public class OrderContainsKeywordsPredicate implements Predicate<Order> {
    private final List<String> nameKeywords;
    private final List<String> addressKeywords;
    private final List<String> itemKeywords;

    public OrderContainsKeywordsPredicate(List<String> nameKeywords, List<String> addressKeywords,
                                          List<String> itemKeywords) {
        this.nameKeywords = nameKeywords;
        this.addressKeywords = addressKeywords;
        this.itemKeywords = itemKeywords;
    }

    @Override
    public boolean test(Order order) {
        return orderMatchesNameKeywords(order)
                || orderMatchesAddressKeywords(order)
                || orderMatchesItemKeywords(order);
    }

    private boolean orderMatchesNameKeywords(Order order) {
        if (nameKeywords.isEmpty()) {
            return false;
        }

        return nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getName().toString(), keyword.trim()));
    }

    private boolean orderMatchesAddressKeywords(Order order) {
        if (addressKeywords.isEmpty()) {
            return false;
        }

        return addressKeywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(order.getAddress().toString(), keyword.trim()));
    }

    private boolean orderMatchesItemKeywords(Order order) {
        if (itemKeywords.isEmpty()) {
            return false;
        }

        return itemKeywords.stream()
                .anyMatch(keyword -> {
                    for (ItemQuantityPair i : order.getItemList()) {
                        if (StringUtil.containsWordIgnoreCase(i.getItemName(), keyword.trim())) {
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
                && (nameKeywords.equals(((OrderContainsKeywordsPredicate) other).nameKeywords))
                && addressKeywords.equals(((OrderContainsKeywordsPredicate) other).addressKeywords)
                && itemKeywords.equals(((OrderContainsKeywordsPredicate) other).itemKeywords)); // state check
    }

}
