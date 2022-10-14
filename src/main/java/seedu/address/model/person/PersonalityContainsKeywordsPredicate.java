package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonalityContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonalityContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> setContainsWord(person.getRemarks(), keyword));
    }

    private boolean setContainsWord(Set<Remark> set, String keyword) {
        for (Remark remark: set) {
            if (remark.value.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonalityContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonalityContainsKeywordsPredicate) other).keywords)); // state check
    }

}
