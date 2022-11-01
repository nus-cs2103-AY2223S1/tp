package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Module}'s {@code ModuleCode} matches exactly with the keywords given (case-insensitive).
 */
public class ModuleCodeMatchesKeywordPredicate implements Predicate<Module> {
    private final String keyword;

    public ModuleCodeMatchesKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Module module) {
        requireNonNull(module);

        String moduleCode = module.getModuleCodeAsUpperCaseString();
        return moduleCode.equalsIgnoreCase(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCodeMatchesKeywordPredicate // instanceof handles nulls
                && keyword.equals(((ModuleCodeMatchesKeywordPredicate) other).keyword)); // state check
    }
}
