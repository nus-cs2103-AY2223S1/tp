package seedu.address.model.commission;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Commission}'s {@code Title} contains any of the given keywords and {@code Tags} has all the
 * tags in @code intersectTags and any of the tags in @code unionTags.
 */
public class CompositeCommissionPredicate implements Predicate<Commission> {
    private final Set<String> keywords;
    private final Set<Tag> intersectTags;
    private final Set<Tag> unionTags;

    /**
     * Constructor of the CompositePredicate.
     */
    public CompositeCommissionPredicate(Set<String> keywords, Set<Tag> intersectTags, Set<Tag> unionTags) {
        this.keywords = keywords;
        this.intersectTags = intersectTags;
        this.unionTags = unionTags;
    }

    @Override
    public boolean test(Commission commission) {
        return (keywords.isEmpty() || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(commission.getTitle().title, keyword)))
                && (intersectTags.isEmpty() || intersectTags.stream().allMatch(tag -> commission.hasTag(tag)))
                && (unionTags.isEmpty() || unionTags.stream().anyMatch(tag -> commission.hasTag(tag)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof CompositeCommissionPredicate) {
            CompositeCommissionPredicate otherPredicate = (CompositeCommissionPredicate) other;
            return this.keywords.equals(otherPredicate.keywords)
                    && this.intersectTags.equals(otherPredicate.intersectTags)
                    && this.unionTags.equals(otherPredicate.unionTags);
        }
        return false;
    }
}
