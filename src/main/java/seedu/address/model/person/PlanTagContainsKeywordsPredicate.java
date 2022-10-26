package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.NormalTag;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PlanTagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> planTags;

    public PlanTagContainsKeywordsPredicate(List<String> planTags) {
        this.planTags = planTags;
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> personTags = person.getSpecialTags();
        for (String predicateTagName : planTags) {
            for (Tag personsTag : personTags) {
                if (personsTag.tagName.equalsIgnoreCase(predicateTagName)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PlanTagContainsKeywordsPredicate // instanceof handles nulls
                && planTags.equals(((PlanTagContainsKeywordsPredicate) other).planTags)); // state check
    }

}
