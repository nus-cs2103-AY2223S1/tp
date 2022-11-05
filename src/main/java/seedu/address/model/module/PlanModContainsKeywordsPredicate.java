package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code PlannedModule} matches the keyword given.
 */
public class PlanModContainsKeywordsPredicate implements Predicate<Person> {

    private final String keywords;

    /**
     * Constructor for the PlanModContainsKeywordsPredicate class.
     * @param keywords The name of the planned module(s) that the user wants to find in their contact list.
     */
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
