package seedu.address.model.commission;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.customer.Customer;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Customer}'s {@code Name} contains any of the given keywords and {@code Tags} has all the
 * tags in @code intersectTags and any of the tags in @code unionTags.
 */
public class CompositeCustomerPredicate implements Predicate<Customer> {
    private final Set<String> keywords;
    private final Set<Tag> intersectTags;
    private final Set<Tag> unionTags;

    /**
     * Constructor of the CompositePredicate.
     */
    public CompositeCustomerPredicate(Set<String> keywords, Set<Tag> intersectTags, Set<Tag> unionTags) {
        this.keywords = keywords;
        this.intersectTags = intersectTags;
        this.unionTags = unionTags;
    }

    @Override
    public boolean test(Customer customer) {
        return (keywords.isEmpty() || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(customer.getName().fullName, keyword)))
                && (intersectTags.isEmpty() || intersectTags.stream().allMatch(tag -> customer.hasTag(tag)))
                && (unionTags.isEmpty() || unionTags.stream().anyMatch(tag -> customer.hasTag(tag)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof CompositeCustomerPredicate) {
            CompositeCustomerPredicate otherPredicate = (CompositeCustomerPredicate) other;
            return this.keywords.equals(otherPredicate.keywords)
                    && this.intersectTags.equals(otherPredicate.intersectTags)
                    && this.unionTags.equals(otherPredicate.unionTags);
        }
        return false;
    }
}
