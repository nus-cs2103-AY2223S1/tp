package tuthub.model.tutor;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import tuthub.commons.util.StringUtil;
import tuthub.model.tag.Tag;

/**
 * Tests that a {@code Tutor}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Tutor> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tutor tutor) {
        Set<Tag> tagSet = tutor.getTags();
        for (Tag tag : tagSet) {
            if (keywords.stream()
                    .anyMatch((keyword -> StringUtil.containsWordIgnoreCasePartialMatch(tag.tagName, keyword)))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TagContainsKeywordsPredicate
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords));
    }
}
