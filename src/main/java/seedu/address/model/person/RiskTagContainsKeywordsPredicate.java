package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class RiskTagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> riskTags;

    public RiskTagContainsKeywordsPredicate(List<String> riskTags) {
        this.riskTags= riskTags;
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> personTags = person.getSpecialTags();
        for (String predicateTagName : riskTags) {
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
                || (other instanceof RiskTagContainsKeywordsPredicate // instanceof handles nulls
                && riskTags.equals(((RiskTagContainsKeywordsPredicate) other).riskTags)); // state check
    }

}
