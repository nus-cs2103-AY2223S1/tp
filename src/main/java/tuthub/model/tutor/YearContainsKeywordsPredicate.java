package tuthub.model.tutor;

import java.util.List;
import java.util.function.Predicate;

import tuthub.commons.util.StringUtil;

/**
 * Tests that a {@code Tutor}'s {@code Year} matches any of the keywords given.
 */
public class YearContainsKeywordsPredicate implements Predicate<Tutor> {
    private final List<String> keywords;

    public YearContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tutor tutor) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCasePartialMatch(tutor.getYear().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof YearContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((YearContainsKeywordsPredicate) other).keywords)); // state check
    }
}
