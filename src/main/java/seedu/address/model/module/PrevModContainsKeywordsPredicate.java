package seedu.address.model.module;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code PreviousModule} matches the keyword given.
 */
public class PrevModContainsKeywordsPredicate implements Predicate<Person> {

    private final String keyword;

    public PrevModContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return person.getPrevModules().stream()
                .anyMatch(prevMod -> StringUtil.containsWordIgnoreCase(keyword, prevMod.moduleName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrevModContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((PrevModContainsKeywordsPredicate) other).keyword)); // state check
    }
}
