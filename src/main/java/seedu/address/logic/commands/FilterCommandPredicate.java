package seedu.address.logic.commands;

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
        assert namePredicate != null || tagsPredicate != null;
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
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                        && namePredicate.equals(((FilterCommandPredicate) other).namePredicate)) // state check
                        && tagsPredicate.equals(((FilterCommandPredicate) other).tagsPredicate); // state check
    }
}
