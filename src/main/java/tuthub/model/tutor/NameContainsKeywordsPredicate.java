package tuthub.model.tutor;

import java.util.List;
import java.util.function.Predicate;

import tuthub.commons.util.StringUtil;

/**
 * Tests that a {@code Tutor}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Tutor> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tutor tutor) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCasePartialMatch(tutor.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
