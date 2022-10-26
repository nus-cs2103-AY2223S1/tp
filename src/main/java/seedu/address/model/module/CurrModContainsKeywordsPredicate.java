package seedu.address.model.module;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code CurrentModule} matches the keyword given.
 */
public class CurrModContainsKeywordsPredicate implements Predicate<Person> {

    private final String keywords;

    public CurrModContainsKeywordsPredicate(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return person.getCurrModules().stream()
                .anyMatch(currMod -> StringUtil.containsWordIgnoreCase(keywords, currMod.moduleName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CurrModContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CurrModContainsKeywordsPredicate) other).keywords)); // state check
    }
}
