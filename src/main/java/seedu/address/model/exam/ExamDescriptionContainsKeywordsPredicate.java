package seedu.address.model.exam;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code TaskDescription} matches any of the keywords given.
 */
public class ExamDescriptionContainsKeywordsPredicate implements Predicate<Exam> {
    private final List<String> keywords;

    public ExamDescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Exam exam) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsPartialWord(exam.getDescription()
                        .description.toLowerCase(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExamDescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ExamDescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }
}
