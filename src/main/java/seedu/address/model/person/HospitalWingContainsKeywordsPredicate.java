package seedu.address.model.person;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Hospital Wing} matches any of the keywords given.
 */
public class HospitalWingContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public HospitalWingContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getHospitalWing().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HospitalWingContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((HospitalWingContainsKeywordsPredicate) other).keywords)); // state check
    }

}

