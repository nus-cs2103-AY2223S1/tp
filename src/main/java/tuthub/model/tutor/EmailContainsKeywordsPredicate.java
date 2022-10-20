package tuthub.model.tutor;

import java.util.List;
import java.util.function.Predicate;

import tuthub.commons.util.StringUtil;

/**
 * Tests that a {@code Tutor}'s {@code Email} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate implements Predicate<Tutor> {
    private final List<String> keywords;

    public EmailContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tutor tutor) {
        return keywords.stream()
                .anyMatch((keyword -> StringUtil.containsWordIgnoreCasePartialMatch(tutor.getEmail().value, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EmailContainsKeywordsPredicate
                && keywords.equals(((EmailContainsKeywordsPredicate) other).keywords));
    }
}
