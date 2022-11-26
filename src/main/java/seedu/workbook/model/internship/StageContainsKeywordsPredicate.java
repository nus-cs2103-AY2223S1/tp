package seedu.workbook.model.internship;

import java.util.List;
import java.util.function.Predicate;

import seedu.workbook.commons.util.StringUtil;

/**
 * Tests that a {@code Internship}'s {@code Stage} matches any of the keywords given.
 */
public class StageContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> keywords;

    public StageContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Internship internship) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getStage().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StageContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StageContainsKeywordsPredicate) other).keywords)); // state check
    }

}
