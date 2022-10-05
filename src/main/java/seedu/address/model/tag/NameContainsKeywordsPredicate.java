package seedu.address.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code tag}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Tag> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
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
                // instanceof handles nulls
                || (other instanceof seedu.address.model.tag.NameContainsKeywordsPredicate
                // state check
                && keywords.equals(((seedu.address.model.tag.NameContainsKeywordsPredicate) other).keywords));
    }

}
