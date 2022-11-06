package seedu.masslinkers.model.student;

import java.util.List;
import java.util.function.Predicate;

import seedu.masslinkers.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code Details} matches any of the keywords given.
 */
public class DetailsContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public DetailsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream().anyMatch(
                keyword -> StringUtil.containsWordIgnoreCase(student.getName().fullName, keyword))
                || keywords.stream().anyMatch(
                        keyword -> StringUtil.containsPartialWordIgnoreCase(student.getTelegram().toString(), keyword))
                || (student.getGitHub() != null && keywords.stream().anyMatch(
                        keyword -> StringUtil.containsPartialWordIgnoreCase(student.getGitHub().toString(), keyword)))
                || (student.getEmail() != null && keywords.stream().anyMatch(
                        keyword -> StringUtil.containsPartialWordIgnoreCase(student.getEmail().toString(), keyword)))
                || (student.getPhone() != null && keywords.stream().anyMatch(
                        keyword -> StringUtil.containsPartialWordIgnoreCase(student.getPhone().toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DetailsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
