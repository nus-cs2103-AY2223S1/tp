package seedu.address.model.module;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code PlannedModule} matches the keyword given.
 */
public class PlanModContainsKeywordsPredicate implements Predicate<Person> {

    private final String keyword;

    public PlanModContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return person.getPlanModules().stream()
                .anyMatch(planMod -> StringUtil.containsWordIgnoreCase(keyword, planMod.moduleName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PlanModContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((PlanModContainsKeywordsPredicate) other).keyword)); // state check
    }
}
