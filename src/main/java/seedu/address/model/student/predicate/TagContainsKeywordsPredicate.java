package seedu.address.model.student.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.model.student.Student;


/**
 * Tests that a {@code Student}'s {@code Tag} matches the keywords given
 */
public class TagContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        List<String> tagList = student.getTags().stream()
                .map(tag -> tag.tagName)
                .collect(Collectors.toList());
        /*
        There is only a need to check whether the tagList (the current tags of the student) contains the keyword.
        If it does not then it is false.

        If the tagList only has 1 tag and there are 2 keywords to search for, it will also fail since this is
        technically O(n^2) solution so all the cases will be covered.
         */
        for (String keyword : keywords) {
            if (!tagList.contains(keyword)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
