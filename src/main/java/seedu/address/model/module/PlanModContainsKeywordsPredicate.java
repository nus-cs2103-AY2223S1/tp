package seedu.address.model.module;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Tests that a {@code Person}'s {@code PlannedModule} matches the keyword given.
 */
public class PlanModContainsKeywordsPredicate implements Predicate<Person> {

    private final String keywords;

    public PlanModContainsKeywordsPredicate(String keywords) {
        requireNonNull(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return person.getPlanModules().stream()
                .anyMatch(planMod -> StringUtil.containsWordIgnoreCase(keywords, planMod.moduleName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PlanModContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PlanModContainsKeywordsPredicate) other).keywords)); // state check
    }
}
