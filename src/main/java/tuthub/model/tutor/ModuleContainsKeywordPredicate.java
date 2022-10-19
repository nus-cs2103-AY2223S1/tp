package tuthub.model.tutor;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import tuthub.commons.util.StringUtil;

/**
 * Tests that a {@code Tutor}'s {@code Module} matches any of the keywords given.
 */
public class ModuleContainsKeywordPredicate implements Predicate<Tutor> {
    private final List<String> keywords;

    public ModuleContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tutor tutor) {
        Set<Module> moduleSet = tutor.getModules();
        for (Module module : moduleSet) {
            if (keywords.stream()
                .anyMatch((keyword -> StringUtil.containsWordIgnoreCase(module.value, keyword)))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ModuleContainsKeywordPredicate
                && keywords.equals(((ModuleContainsKeywordPredicate) other).keywords));
    }
}
