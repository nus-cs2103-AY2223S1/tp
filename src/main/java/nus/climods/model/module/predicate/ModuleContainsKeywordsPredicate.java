package nus.climods.model.module.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import nus.climods.model.module.Module;

public class ModuleContainsKeywordsPredicate implements Predicate<Module> {

    private final List<Pattern> searchRegexes;

    public ModuleContainsKeywordsPredicate(List<Pattern> searchRegexes) {
        this.searchRegexes = searchRegexes;
    }
    @Override
    public boolean test(Module module) {
        return searchRegexes.stream().anyMatch(module::containsKeyword);
    }
}
