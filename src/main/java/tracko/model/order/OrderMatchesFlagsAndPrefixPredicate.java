package tracko.model.order;

import java.util.List;
import java.util.function.Predicate;

import tracko.commons.util.StringUtil;

/**
 * Tests that a {@code Order}'s {@code Name} matches any of the keywords given.
 */
public class OrderMatchesFlagsAndPrefixPredicate implements Predicate<Order> {
    private final List<String> nameKeywords;
    private final List<String> addressKeywords;
    private final List<String> itemKeywords;
    private final Boolean isFilteringByPaid;
    private final Boolean isFilteringByDelivered;
    private final Boolean isPaid;
    private final Boolean isDelivered;


    public OrderMatchesFlagsAndPrefixPredicate(List<String> nameKeywords, List<String> addressKeywords,
                                               List<String> itemKeywords, Boolean isFilteringByPaid,
                                               Boolean isFilteringByDelivered, Boolean isPaid, Boolean isDelivered) {
        this.nameKeywords = nameKeywords;
        this.addressKeywords = addressKeywords;
        this.itemKeywords = itemKeywords;
        this.isFilteringByPaid = isFilteringByPaid;
        this.isFilteringByDelivered = isFilteringByDelivered;
        this.isPaid = isPaid;
        this.isDelivered = isDelivered;
    }

    @Override
    public boolean test(Order order) {
        boolean isOrderMatchingKeywords = isOrderMatchingKeywords(order);

        boolean isFilterByKeyword = !isAllKeywordsEmpty();

        // case 1: search by flag and keyword
        if (((isFilteringByDelivered || isFilteringByPaid) && isFilterByKeyword)) {
            if (isFilteringByPaid && isFilteringByDelivered) {
                return isOrderMatchingKeywords && (paidStatusMatch(order) && deliveredStatusMatch(order));
            }

            if (isFilteringByDelivered) {
                return isOrderMatchingKeywords && deliveredStatusMatch(order);
            }

            return isOrderMatchingKeywords && paidStatusMatch(order);
        }

        // case 2: search by flag only
        if ((isFilteringByDelivered || isFilteringByPaid)) {
            if (isFilteringByPaid && isFilteringByDelivered) {
                return paidStatusMatch(order) && deliveredStatusMatch(order);
            }

            if (isFilteringByDelivered) {
                return deliveredStatusMatch(order);
            }

            if (isFilteringByPaid) {
                return paidStatusMatch(order);
            }
        }

        // case 3: search by keyword only
        if (isFilterByKeyword) {
            return isOrderMatchingKeywords;
        }

        assert false;
        return false; // shouldnt reach here because there are only 3 valid cases
    }

    /**
     * Checks if the order is matching keywords from different prefixes.
     * If 2 or 3  prefixes are present, the order will match only if it contains at least one keyword from each prefix.
     * @param order to be checked.
     * @return True if order matches keywords according to specifications of this function.
     */
    private Boolean isOrderMatchingKeywords(Order order) {
        boolean isNameKeywords = !nameKeywords.isEmpty();
        boolean isAddressKeywords = !addressKeywords.isEmpty();
        boolean isItemKeywords = !itemKeywords.isEmpty();

        // 2 prefixes present
        if (isNameKeywords && isAddressKeywords && !isItemKeywords) {
            return isMatchingNameKeywords(order)
                    && isMatchingAddressKeywords(order);
        }

        if (!isNameKeywords && isAddressKeywords && isItemKeywords) {
            return isMatchingAddressKeywords(order)
                    && isMatchingItemKeywords(order);
        }

        if (isNameKeywords && !isAddressKeywords && isItemKeywords) {
            return isMatchingNameKeywords(order)
                    && isMatchingItemKeywords(order);
        }

        // 3 prefixes present
        if (isNameKeywords && isAddressKeywords && isItemKeywords) {
            return isMatchingNameKeywords(order)
                    && isMatchingAddressKeywords(order)
                    && isMatchingItemKeywords(order);
        }

        // one prefix, or no prefix present
        return isMatchingNameKeywords(order)
                || isMatchingAddressKeywords(order)
                || isMatchingItemKeywords(order);
    }

    /**
     * Checks if name of given order matches any keyword given after n/ prefix.
     * @param order to be checked.
     * @returns True if name of given order matches any keyword given after n/ prefix.
     */
    private Boolean isMatchingNameKeywords(Order order) {
        if (nameKeywords.isEmpty()) {
            return false;
        }

        return nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getName().toString(), keyword.trim()));
    }

    /**
     * Checks if address of given order matches any keyword given after a/ prefix.
     * @param order to be checked.
     * @returns True if address of given order matches any keyword given after a/ prefix.
     */
    private Boolean isMatchingAddressKeywords(Order order) {
        if (addressKeywords.isEmpty()) {
            return false;
        }

        return addressKeywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(order.getAddress().toString(), keyword.trim()));
    }

    /**
     * Checks if item name of given order matches any keyword given after i/ prefix.
     * @param order to be checked.
     * @returns True if item name of given order matches any keyword given after i/ prefix.
     */
    private Boolean isMatchingItemKeywords(Order order) {
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

    /**
     * Checks if there were no prefixes used in search command.
     * @returns True if there were no prefixes used in search command.
     */
    private Boolean isAllKeywordsEmpty() {
        return itemKeywords.isEmpty()
                && nameKeywords.isEmpty()
                && addressKeywords.isEmpty();
    }

    /**
     * Checks if paid status of an order matches the payment status we are searching by.
     * @returns True if paid status of an order matches the payment status we are searching by.
     */
    private Boolean paidStatusMatch(Order order) {
        return isPaid == order.getPaidStatus();
    }

    /**
     * Checks if delivered status of an order matches the delivery status we are searching by.
     * @returns True if delivered status of an order matches the delivery status we are searching by.
     */
    private Boolean deliveredStatusMatch(Order order) {
        return isDelivered == order.getDeliveryStatus();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderMatchesFlagsAndPrefixPredicate // instanceof handles nulls
                && (nameKeywords.equals(((OrderMatchesFlagsAndPrefixPredicate) other).nameKeywords)
                && addressKeywords.equals(((OrderMatchesFlagsAndPrefixPredicate) other).addressKeywords)
                && itemKeywords.equals(((OrderMatchesFlagsAndPrefixPredicate) other).itemKeywords)
                && (isPaid == ((OrderMatchesFlagsAndPrefixPredicate) other).isPaid)
                && (isDelivered == ((OrderMatchesFlagsAndPrefixPredicate) other).isDelivered)
                && (isFilteringByPaid == ((OrderMatchesFlagsAndPrefixPredicate) other).isFilteringByPaid)
                && (isFilteringByDelivered == ((OrderMatchesFlagsAndPrefixPredicate) other).isFilteringByDelivered)));// state check
    }

}
