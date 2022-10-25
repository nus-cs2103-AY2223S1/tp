package seedu.address.model.module;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Module}'s {@code ModuleCode} matches any of the keywords given.
 */
public class TutorialDetailsContainsKeywordsPredicate implements Predicate<Module> {

    private final List<String> keywords;

    public TutorialDetailsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Module module) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(module.getTutorialDetails().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialDetailsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TutorialDetailsContainsKeywordsPredicate) other).keywords)); // state check
    }
}
