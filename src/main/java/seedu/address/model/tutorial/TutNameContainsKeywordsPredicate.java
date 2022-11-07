package seedu.address.model.tutorial;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Tutorial}'s {@code Name} matches any of the keywords given.
 */
public class TutNameContainsKeywordsPredicate implements Predicate<Tutorial> {
    private final List<String> keywords;

    public TutNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tutorial tutorial) {
        return keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tutorial.getContent().content,
                keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TutNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}


