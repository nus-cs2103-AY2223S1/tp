package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Module}'s {@code ModuleCode} starts with the keywords given (case-insensitive).
 */
public class ModuleCodeStartsWithKeywordPredicate implements Predicate<Module> {
    private final String keyword;

    public ModuleCodeStartsWithKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Module module) {
        requireNonNull(module);

        String moduleCode = module.getModuleCodeAsUpperCaseString();
        return moduleCode.startsWith(keyword.toUpperCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCodeStartsWithKeywordPredicate // instanceof handles nulls
                && keyword.equals(((ModuleCodeStartsWithKeywordPredicate) other).keyword)); // state check
    }
}
