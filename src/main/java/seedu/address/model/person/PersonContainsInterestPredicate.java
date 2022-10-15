package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Tests that a {@code Person}'s {@code Interest} matches all the interests (case-insensitive) specified by Student.
 */
public class PersonContainsInterestPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonContainsInterestPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {

        if (keywords.isEmpty()) { //keywords = trimmed user input
            return false;
        }

        Set<String> personInterestSet = person
                .getInterests()
                .stream()
                .map(interest -> interest.interestName)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        Set<String> lowerCaseKeywords = keywords //to be case-insensitive
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        return personInterestSet.containsAll(lowerCaseKeywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsInterestPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsInterestPredicate) other).keywords)); // state check
    }

}
