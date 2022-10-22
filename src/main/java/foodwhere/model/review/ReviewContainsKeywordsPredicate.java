package foodwhere.model.review;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import foodwhere.commons.util.StringUtil;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;

/**
 * Tests that a {@code Review}'s {@code Name} or {@code Tag} matches any of the keywords given for each field.
 */
public class ReviewContainsKeywordsPredicate implements Predicate<Review> {
    private final HashSet<Name> nameKeywords;
    private final HashSet<Tag> tagKeywords;

    /**
     * Creates a ReviewContainsKeywordsPredicate where arguments are in List
     * @param nameKeywords List of name keywords
     * @param tagKeywords List of tag keywords
     */
    public ReviewContainsKeywordsPredicate(List<Name> nameKeywords, List<Tag> tagKeywords) {
        this.nameKeywords = new HashSet<Name>(nameKeywords);
        this.tagKeywords = new HashSet<Tag>(tagKeywords);
    }

    /**
     * Creates a ReviewContainsKeywordsPredicate where arguments are in Set
     * @param nameKeywords Set of name keywords
     * @param tagKeywords Set of tag keywords
     */
    public ReviewContainsKeywordsPredicate(Set<Name> nameKeywords, Set<Tag> tagKeywords) {
        this.nameKeywords = new HashSet<Name>(nameKeywords);
        this.tagKeywords = new HashSet<Tag>(tagKeywords);
    }

    @Override
    public boolean test(Review review) {
        for (Tag tag : review.getTags()) {
            if (tagKeywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tag.tag, keyword.tag))) {
                return true;
            }
        }

        return nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(review.getName().fullName, keyword.fullName));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReviewContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((ReviewContainsKeywordsPredicate) other).nameKeywords))
                && tagKeywords.equals(((ReviewContainsKeywordsPredicate) other).tagKeywords);
    }
}
