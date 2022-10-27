package seedu.address.model.commission;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Commission}'s {@code Tags} has any of the tags given.
 */
public class CommissionContainsAnyTagPredicate implements Predicate<Commission> {
    private final List<Tag> tags;

    public CommissionContainsAnyTagPredicate(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Commission commission) {
        return tags.stream().anyMatch(tag -> commission.hasTag(tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof CommissionContainsAnyTagPredicate)
                && tags.equals(((CommissionContainsAnyTagPredicate) other).tags);
    }
}
