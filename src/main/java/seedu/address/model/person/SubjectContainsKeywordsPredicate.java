package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class SubjectContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public SubjectContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> arrayListContainsWord(person.getSubject().getArrayOfSubjects(), keyword));
    }

    private boolean arrayListContainsWord(ArrayList<String> arrayList, String keyword) {
        for (String string: arrayList) {
            if (string.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SubjectContainsKeywordsPredicate) other).keywords)); // state check
    }

}