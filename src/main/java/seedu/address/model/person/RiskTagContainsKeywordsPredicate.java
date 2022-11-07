package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class RiskTagContainsKeywordsPredicate extends FindPredicate {
    private final List<String> riskTags;

    /**
     * Constructs a {@code RiskTagContainsKeywordsPredicate}.
     *
     * @param riskTags RiskTags to be tested against.
     */
    public RiskTagContainsKeywordsPredicate(List<String> riskTags) {
        super(riskTags);
        this.riskTags = riskTags.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
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
