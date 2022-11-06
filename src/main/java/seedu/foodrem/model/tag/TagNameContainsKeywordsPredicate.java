package seedu.foodrem.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.foodrem.commons.util.StringUtil;

/**
 * Tests that a {@code tag}'s {@code Name} matches any of the keywords given.
 */
public class TagNameContainsKeywordsPredicate implements Predicate<Tag> {
    private final List<String> keywords;

    public TagNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests whether the keyword is contained in the {@code tag}
     *
     * @param tag to be tested
     * @return boolean stating whether keyword in tag name
     */
    @Override
    public boolean test(Tag tag) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tag.getName(), keyword));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TagNameContainsKeywordsPredicate
                && keywords.equals(((TagNameContainsKeywordsPredicate) other).keywords));
    }
}
