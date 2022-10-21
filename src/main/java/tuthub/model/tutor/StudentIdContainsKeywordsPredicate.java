package tuthub.model.tutor;

import java.util.List;
import java.util.function.Predicate;

import tuthub.commons.util.StringUtil;

/**
 * Tests that a {@code Tutor}'s {@code StudentId} matches any of the keywords given.
 */
public class StudentIdContainsKeywordsPredicate implements Predicate<Tutor> {
    private final List<String> keywords;

    public StudentIdContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tutor tutor) {
        return keywords.stream()
                .anyMatch((keyword ->
                        StringUtil.containsWordIgnoreCasePartialMatch(tutor.getStudentId().value, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof StudentIdContainsKeywordsPredicate
                && keywords.equals(((StudentIdContainsKeywordsPredicate) other).keywords));
    }
}
