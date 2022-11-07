package seedu.address.model.student;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;



/**
 * Tests that a {@code Student}'s {@code tutorialGroup} {@code name} matches any of the keywords given.
 */
public class TutorialNameContainsKeywordsPredicate implements Predicate<TutorialGroup> {
    private final List<String> keywords;

    public TutorialNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(TutorialGroup tutorialGroup) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tutorialGroup.toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TutorialNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
