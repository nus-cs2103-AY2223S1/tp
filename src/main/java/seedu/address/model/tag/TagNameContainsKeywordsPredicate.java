package seedu.address.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Tag}'s {@code Name} matches any of the keywords given.
 */
public class TagNameContainsKeywordsPredicate implements Predicate<Tag> {
    private final List<String> keywords;

    public TagNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tag tag) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tag.getName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
