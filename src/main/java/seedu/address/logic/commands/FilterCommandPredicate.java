package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.isAnyNonNull;

import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagMatchesQueryPredicate;

/**
 * Class used to combine predicates used for {@code FilterCommand} to query a {@code Person}'s attributes.
 * Combination of {@code NameContainsKeywordsPredicate} and {@code TagMatchesQueryPredicate}.
 */
public class FilterCommandPredicate {
    private final NameContainsKeywordsPredicate namePredicate;
    private final TagMatchesQueryPredicate tagsPredicate;

    /**
     * Creates a FilterCommandPredicate consisting of {@code NameContainsKeywordsPredicate}
     * and {@code TagMatchesQueryPredicate}.
     *
     * @param namePredicate Predicate for name filtering.
     * @param tagsPredicate Predicate for tags filtering.
     */
    public FilterCommandPredicate(NameContainsKeywordsPredicate namePredicate,
            TagMatchesQueryPredicate tagsPredicate) {
        assert isAnyNonNull(namePredicate, tagsPredicate);
        this.namePredicate = namePredicate;
        this.tagsPredicate = tagsPredicate;
    }

    public NameContainsKeywordsPredicate getNamePredicate() {
        return namePredicate;
    }

    public TagMatchesQueryPredicate getTagsPredicate() {
        return tagsPredicate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof FilterCommandPredicate)) {
            return false;
        }
        FilterCommandPredicate otherPredicate = (FilterCommandPredicate) other;
        boolean isNamePredicateEquals = (namePredicate == null && otherPredicate.namePredicate == null)
                || namePredicate.equals(otherPredicate.namePredicate);
        boolean isTagsPredicateEquals = (tagsPredicate == null && otherPredicate.tagsPredicate == null)
                || tagsPredicate.equals(otherPredicate.tagsPredicate);
        return isNamePredicateEquals && isTagsPredicateEquals;
    }
}
