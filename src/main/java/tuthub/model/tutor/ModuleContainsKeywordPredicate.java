package tuthub.model.tutor;

import java.util.List;
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
        return keywords.stream()
                .anyMatch((keyword -> StringUtil.containsWordIgnoreCase(tutor.getModule().value, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ModuleContainsKeywordPredicate
                && keywords.equals(((ModuleContainsKeywordPredicate) other).keywords));
    }
}
