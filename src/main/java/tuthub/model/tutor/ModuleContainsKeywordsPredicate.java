package tuthub.model.tutor;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import tuthub.commons.util.StringUtil;

/**
 * Tests that a {@code Tutor}'s {@code Module} matches any of the keywords given.
 */
public class ModuleContainsKeywordsPredicate implements Predicate<Tutor> {
    private final List<String> keywords;

    public ModuleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tutor tutor) {
        Set<Module> moduleSet = tutor.getModules();
        for (Module module : moduleSet) {
            if (keywords.stream()
                .anyMatch((keyword -> StringUtil.containsWordIgnoreCasePartialMatch(module.value, keyword)))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ModuleContainsKeywordsPredicate
                && keywords.equals(((ModuleContainsKeywordsPredicate) other).keywords));
    }
}
