package tuthub.model.tutor;

import java.util.List;
import java.util.function.Predicate;

import tuthub.commons.util.StringUtil;

/**
 * Tests that a {@code Tutor}'s {@code TeachingNomination} matches any of the keywords given.
 */
public class TeachingNominationContainKeywordsPredicate implements Predicate<Tutor> {
    private final List<String> keywords;

    public TeachingNominationContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tutor tutor) {
        return keywords.stream()
                .anyMatch((keyword ->
                        StringUtil.containsWordIgnoreCasePartialMatch(tutor.getTeachingNomination().value, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TeachingNominationContainKeywordsPredicate
                && keywords.equals(((TeachingNominationContainKeywordsPredicate) other).keywords));
    }
}
