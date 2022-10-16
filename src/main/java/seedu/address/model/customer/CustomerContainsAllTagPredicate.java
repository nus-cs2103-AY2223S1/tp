package seedu.address.model.customer;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Customer}'s {@code Name} matches any of the keywords given.
 */
public class CustomerContainsAllTagPredicate implements Predicate<Customer> {
    private final List<Tag> tags;

    public CustomerContainsAllTagPredicate(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Customer customer) {
        return tags.stream()
                .allMatch(tag -> customer.hasTag(tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CustomerContainsAllTagPredicate // instanceof handles nulls
                && tags.equals(((CustomerContainsAllTagPredicate) other).tags)); // state check
    }

}
