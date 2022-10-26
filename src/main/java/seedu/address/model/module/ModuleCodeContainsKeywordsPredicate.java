package seedu.address.model.module;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Module}'s {@code ModuleCode} matches any of the keywords given.
 */
public class ModuleCodeContainsKeywordsPredicate implements Predicate<Module> {
    private final List<String> keywords;

    public ModuleCodeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Module module) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsPartialWord(module.getModuleCode().moduleCode, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCodeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleCodeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
