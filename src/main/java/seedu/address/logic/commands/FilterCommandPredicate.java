package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.isAnyNonNull;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
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
        if (namePredicate == null) {
            return null;
        }
        return Set.copyOf(namePredicate);
    }

    public Set<TagMatchesQueryPredicate> getTagPredicate() {
        if (tagsPredicate == null) {
            return null;
        }
        return Set.copyOf(tagsPredicate);
    }

    /**
     * Adds {@code namePredicate} to {@code predicateSet}.
     *
     * @param predicateSet Set of predicate to add to.
     * @return {@code true} if {@code predicateSet} was changed,
     *      {@code false} if it did not change.
     */
    public boolean addNameFiltersToSet(Set<Predicate<Person>> predicateSet) {
        if (namePredicate == null) {
            return false;
        }
        return predicateSet.addAll(namePredicate);
    }

    /**
     * Adds {@code tagsPredicate} to {@code predicateSet}.
     *
     * @param predicateSet Set of predicate to add to.
     * @return {@code true} if {@code predicateSet} was changed,
     *      {@code false} if it did not change.
     */
    public boolean addTagFiltersToSet(Set<Predicate<Person>> predicateSet) {
        if (tagsPredicate == null) {
            return false;
        }
        return predicateSet.addAll(tagsPredicate);
    }

    /**
     * Removes {@code namePredicate} from {@code predicateSet}.
     *
     * @param predicateSet Set of predicate to remove from.
     * @return {@code true} if {@code predicateSet} was changed,
     *      {@code false} if it did not change.
     */
    public boolean removeNameFiltersFromSet(Set<Predicate<Person>> predicateSet) {
        if (namePredicate == null) {
            return false;
        }
        return predicateSet.removeAll(namePredicate);
    }

    /**
     * Removes {@code tagsPredicate} from {@code predicateSet}.
     *
     * @param predicateSet Set of predicate to remove from.
     * @return {@code true} if {@code predicateSet} was changed,
     *      {@code false} if it did not change.
     */
    public boolean removeTagFiltersFromSet(Set<Predicate<Person>> predicateSet) {
        if (tagsPredicate == null) {
            return false;
        }
        return predicateSet.removeAll(tagsPredicate);
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
