package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.isAnyNonNull;

import java.util.Set;

import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagMatchesQueryPredicate;

/**
 * Class used to combine predicates used for {@code FilterCommand} to query a {@code Person}'s attributes.
 * Combination of {@code NameContainsKeywordsPredicate} and {@code TagMatchesQueryPredicate}.
 */
public class FilterCommandPredicate {
    private final Set<NameContainsKeywordsPredicate> namePredicate;
    private final Set<TagMatchesQueryPredicate> tagsPredicate;

    /**
     * Creates a FilterCommandPredicate consisting of {@code NameContainsKeywordsPredicate}
     * and {@code TagMatchesQueryPredicate}.
     *
     * @param namePredicate Set of predicates for name filtering.
     * @param tagsPredicate Set of predicates for tags filtering.
     */
    public FilterCommandPredicate(Set<NameContainsKeywordsPredicate> namePredicate,
            Set<TagMatchesQueryPredicate> tagsPredicate) {
        assert isAnyNonNull(namePredicate, tagsPredicate);
        this.namePredicate = namePredicate;
        this.tagsPredicate = tagsPredicate;
    }

    /**
     * Creates a FilterCommandPredicate consisting of {@code NameContainsKeywordsPredicate}
     * and {@code TagMatchesQueryPredicate}.
     *
     * @param namePredicate Predicate for name filtering.
     * @param tagsPredicate Predicate for tags filtering.
     */
    public FilterCommandPredicate(NameContainsKeywordsPredicate namePredicate,
            TagMatchesQueryPredicate tagsPredicate) {
        this(namePredicate == null ? null : Set.of(namePredicate),
                tagsPredicate == null ? null : Set.of(tagsPredicate));
    }


    public Set<NameContainsKeywordsPredicate> getNamePredicate() {
        return namePredicate;
    }

    public Set<TagMatchesQueryPredicate> getTagPredicate() {
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
