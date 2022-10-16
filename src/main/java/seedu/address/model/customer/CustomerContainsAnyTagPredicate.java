package seedu.address.model.customer;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Customer}'s {@code Tags} has any of the tags given.
 */
public class CustomerContainsAnyTagPredicate implements Predicate<Customer> {
    private final List<Tag> tags;
    private CustomerContainsAnyTagPredicate predicate;

    public CustomerContainsAnyTagPredicate(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Customer customer) {
        return tags.stream()
                .anyMatch(tag -> customer.hasTag(tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CustomerContainsAnyTagPredicate // instanceof handles nulls
                && tags.equals(((CustomerContainsAnyTagPredicate) other).tags)); // state check
    }

}
