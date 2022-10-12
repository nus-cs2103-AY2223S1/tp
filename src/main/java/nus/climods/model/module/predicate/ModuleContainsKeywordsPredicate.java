package nus.climods.model.module.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import nus.climods.model.module.Module;

/**
 * Tests that a {@code Module}'s given information matches any of the keywords given.
 */
public class ModuleContainsKeywordsPredicate implements Predicate<Module> {

    private final List<Pattern> searchRegexes;

    public ModuleContainsKeywordsPredicate(List<Pattern> searchRegexes) {
        this.searchRegexes = searchRegexes;
    }

    @Override
    public boolean test(Module module) {
        return searchRegexes.stream().anyMatch(module::containsKeyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof ModuleContainsKeywordsPredicate
            && searchRegexes.equals(((ModuleContainsKeywordsPredicate) other).searchRegexes));
    }
}
