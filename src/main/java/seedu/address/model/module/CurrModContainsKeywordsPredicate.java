package seedu.address.model.module;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code CurrentModule} matches the keyword given.
 */
public class CurrModContainsKeywordsPredicate implements Predicate<Person> {

    private final String keyword;

    public CurrModContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return person.getCurrModules().stream()
                .anyMatch(currMod -> StringUtil.containsWordIgnoreCase(keyword, currMod.moduleName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CurrModContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((CurrModContainsKeywordsPredicate) other).keyword)); // state check
    }
}
