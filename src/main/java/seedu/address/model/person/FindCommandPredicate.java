package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindCommandPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FindCommandPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean checkName = keywords.stream()
                                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName,
                                                                                           keyword));
        boolean checkClass = keywords.stream()
                                     .anyMatch(
                                         keyword -> StringUtil.containsWordIgnoreCase(person.getStudentClass().value,
                                                                                      keyword));
        boolean checkSubject = keywords.stream()
                                       .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                                           person.getSubjectHandler().toString(), keyword));
        return checkName || checkClass || checkSubject;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof FindCommandPredicate // instanceof handles nulls
                   && keywords.equals(((FindCommandPredicate) other).keywords)); // state check
    }

}
