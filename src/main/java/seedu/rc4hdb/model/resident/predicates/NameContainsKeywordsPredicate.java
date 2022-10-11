package seedu.rc4hdb.model.resident.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.rc4hdb.commons.util.StringUtil;
import seedu.rc4hdb.model.resident.Resident;

/**
 * Tests that a {@code Resident}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Resident> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Resident resident) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(resident.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
