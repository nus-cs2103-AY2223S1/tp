package seedu.watson.model.student;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.watson.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class FindCommandPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public FindCommandPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        boolean checkName = keywords.get(0) == ""
                ? false
                : Arrays.asList(keywords.get(0)).stream()
                                  .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getName().fullName,
                                                                                           keyword));
        boolean checkClass = keywords.get(1) == ""
                ? false
                : Arrays.asList(keywords.get(1)).stream()
                                     .anyMatch(
                                         keyword -> StringUtil.containsWordIgnoreCase(student.getStudentClass().value,
                                                                                      keyword));
        boolean checkSubject = keywords.get(2) == ""
                ? false
                : Arrays.asList(keywords.get(2)).stream()
                                       .anyMatch(keyword ->
                student.getSubjectsTaken().toString().toLowerCase().contains(keyword));
        return checkName || checkClass || checkSubject;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof FindCommandPredicate // instanceof handles nulls
                   && keywords.equals(((FindCommandPredicate) other).keywords)); // state check
    }
}
