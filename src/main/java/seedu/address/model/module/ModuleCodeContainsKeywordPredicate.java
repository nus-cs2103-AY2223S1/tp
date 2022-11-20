package seedu.address.model.module;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Module}'s {@code ModuleCode} matches the keyword given.
 */
public class ModuleCodeContainsKeywordPredicate implements Predicate<Module> {
    private final ModuleCode keyword;

    public ModuleCodeContainsKeywordPredicate(ModuleCode keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Module module) {
        Set<ModuleCode> setOfModules = new HashSet<>();
        setOfModules.add(keyword);
        return setOfModules.stream()
                .anyMatch(keyword -> StringUtil
                        .containsWordIgnoreCase(module.getCode().fullCode, keyword.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model
                .module.ModuleCodeContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((seedu.address.model
                .module.ModuleCodeContainsKeywordPredicate) other).keyword)); // state check
    }
}
