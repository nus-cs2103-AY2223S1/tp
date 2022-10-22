package foodwhere.model.stall;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import foodwhere.commons.util.StringUtil;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;

/**
 * Tests that a {@code Stall}'s {@code Name} or {@code Tag} matches any of the keywords given for each field.
 */
public class StallContainsKeywordsPredicate implements Predicate<Stall> {
    private final HashSet<Name> nameKeywords;
    private final HashSet<Tag> tagKeywords;

    /**
     * Creates a StallContainsKeywordsPredicate where arguments are in List
     * @param nameKeywords List of name keywords
     * @param tagKeywords List of tag keywords
     */
    public StallContainsKeywordsPredicate(List<Name> nameKeywords, List<Tag> tagKeywords) {
        this.nameKeywords = new HashSet<Name>(nameKeywords);
        this.tagKeywords = new HashSet<Tag>(tagKeywords);
    }

    /**
     * Creates a StallContainsKeywordsPredicate where arguments are in Set
     * @param nameKeywords Set of name keywords
     * @param tagKeywords Set of tag keywords
     */
    public StallContainsKeywordsPredicate(Set<Name> nameKeywords, Set<Tag> tagKeywords) {
        this.nameKeywords = new HashSet<Name>(nameKeywords);
        this.tagKeywords = new HashSet<Tag>(tagKeywords);
    }

    @Override
    public boolean test(Stall stall) {
        for (Tag tag : stall.getTags()) {
            if (tagKeywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tag.tag, keyword.tag))) {
                return true;
            }
        }

        return nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(stall.getName().fullName, keyword.fullName));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StallContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((StallContainsKeywordsPredicate) other).nameKeywords))
                && tagKeywords.equals(((StallContainsKeywordsPredicate) other).tagKeywords);
    }

}
