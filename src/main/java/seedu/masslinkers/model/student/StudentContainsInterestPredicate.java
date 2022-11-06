package seedu.masslinkers.model.student;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Tests that a {@code Student}'s {@code Interest} matches all the interests (case-insensitive) specified by Student.
 */
public class StudentContainsInterestPredicate implements Predicate<Student> {
    private List<String> keywords;

    public StudentContainsInterestPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {

        if (keywords.isEmpty()) { //keywords = trimmed user input
            return false;
        }

        Set<String> studentInterestSet = student
                .getInterests()
                .stream()
                .map(interest -> interest.interestName)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        Set<String> lowerCaseKeywords = keywords //to be case-insensitive
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        return studentInterestSet.containsAll(lowerCaseKeywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentContainsInterestPredicate // instanceof handles nulls
                && keywords.equals(((StudentContainsInterestPredicate) other).keywords)); // state check
    }

}
