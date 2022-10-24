package seedu.address.model.person;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Hospital Wing} matches any of the keywords given.
 */
public class HospitalWingContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructor for HospitalWingContainsKeywordsPredicate.
     * @param keywords patients' hospital wings
     */
    public HospitalWingContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if the input argument matches the person's details
     * @param person the input argument
     * @return the test result
     */
    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getHospitalWing().get().value, keyword));
    }

    /**
     * Tests the similarity of two objects
     * @param other the object to be tested
     * @return true if the objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HospitalWingContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((HospitalWingContainsKeywordsPredicate) other).keywords)); // state check
    }

}

