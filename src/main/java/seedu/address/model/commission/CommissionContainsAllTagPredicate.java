package seedu.address.model.commission;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Commission}'s {@code Tags} has all the tags given.
 */
public class CommissionContainsAllTagPredicate implements Predicate<Commission> {
    private final List<Tag> tags;

    public CommissionContainsAllTagPredicate(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Commission commission) {
        return tags.stream().allMatch(tag -> commission.hasTag(tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof CommissionContainsAllTagPredicate)
                && tags.equals(((CommissionContainsAllTagPredicate) other).tags);
    }
}
