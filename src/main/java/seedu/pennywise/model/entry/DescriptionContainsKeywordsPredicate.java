package seedu.pennywise.model.entry;

import java.util.List;
import java.util.function.Predicate;

import seedu.pennywise.commons.util.StringUtil;

/**
 * Tests that a {@code Entry}'s {@code Description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Entry> {
    private final List<String> keywords;

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Entry entry) {
        return keywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(entry.getDescription().fullDescription, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DescriptionContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((DescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
