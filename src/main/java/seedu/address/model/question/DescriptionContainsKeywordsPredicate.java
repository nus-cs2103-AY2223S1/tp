package seedu.address.model.question;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Question}'s {@code Description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Question> {
    private final List<String> keywords;

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Question question) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(question.getDescription().descriptionString,
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
