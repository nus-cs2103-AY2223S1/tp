package seedu.address.model.module;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;

/**
 * Tests that a {@code Person}'s {@code PreviousModule} matches the keyword given.
 */
public class PrevModContainsKeywordsPredicate implements Predicate<Person> {

    private final String keywords;

    public PrevModContainsKeywordsPredicate(String keywords) {
        requireNonNull(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return person.getPrevModules().stream()
                .anyMatch(prevMod -> StringUtil.containsWordIgnoreCase(keywords, prevMod.moduleName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrevModContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PrevModContainsKeywordsPredicate) other).keywords)); // state check
    }
}
