package tuthub.model.tutor;

import java.util.List;
import java.util.function.Predicate;

import tuthub.commons.util.StringUtil;

/**
 * Tests that a {@code Tutor}'s {@code Rating} matches any of the keywords given.
 */
public class RatingContainsKeywordsPredicate implements Predicate<Tutor> {
    private final List<String> keywords;

    public RatingContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tutor tutor) {
        return keywords.stream()
                .anyMatch((keyword ->
                        StringUtil.startsWithWordIgnoreCasePartialMatch(tutor.getRating().value, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RatingContainsKeywordsPredicate
                && keywords.equals(((RatingContainsKeywordsPredicate) other).keywords));
    }
}
