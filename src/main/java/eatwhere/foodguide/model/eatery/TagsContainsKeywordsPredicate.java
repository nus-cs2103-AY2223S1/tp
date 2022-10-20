package eatwhere.foodguide.model.eatery;

import java.util.List;
import java.util.function.Predicate;

import eatwhere.foodguide.commons.util.StringUtil;
import eatwhere.foodguide.model.tag.Tag;

/**
 * Tests that a {@code Eatery}'s {@code Name} matches any of the keywords given.
 */
public class TagsContainsKeywordsPredicate implements Predicate<Eatery> {
    private final List<String> keywords;

    public TagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Eatery eatery) {
        StringBuilder builder = new StringBuilder();
        for (Tag t: eatery.getTags()) {
            builder.append(t.tagName).append(" ");
        }

        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(builder.toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
