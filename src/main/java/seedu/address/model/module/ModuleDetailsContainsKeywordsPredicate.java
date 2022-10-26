package seedu.address.model.module;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Module}'s {@code ModuleCode} matches any of the keywords given.
 */
public class ModuleDetailsContainsKeywordsPredicate implements Predicate<Module> {

    private final List<String> keywords;

    public ModuleDetailsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Module module) {
        String moduleDetails = module.getModuleCode().moduleCode + " " + module.getLectureDetails().toString()
                + " " + module.getTutorialDetails().toString() + " " + module.getAssignmentDetails().toString();
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(moduleDetails, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleDetailsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleDetailsContainsKeywordsPredicate) other).keywords)); // state check
    }
}
