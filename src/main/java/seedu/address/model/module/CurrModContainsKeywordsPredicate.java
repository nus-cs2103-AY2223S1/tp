package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code CurrentModule} matches the keyword given.
 */
public class CurrModContainsKeywordsPredicate implements Predicate<Person> {

    private final String keywords;

    /**
     * Constructor for the CurrModContainsKeywordsPredicate class.
     * @param keywords The name of the current module(s) that the user wants to find in their contact list.
     */
    public CurrModContainsKeywordsPredicate(String keywords) {
        requireNonNull(keywords);
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

