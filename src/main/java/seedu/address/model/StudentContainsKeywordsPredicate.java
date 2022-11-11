package seedu.address.model;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_OR_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashMap;
import java.util.function.Predicate;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.student.Student;

/**
 * Tests that a {@code Student}'s fields matches the respective keywords given.
 */
public class StudentContainsKeywordsPredicate<T> implements Predicate<T> {
    private final HashMap<Prefix, String> keywords;

    public StudentContainsKeywordsPredicate(HashMap<Prefix, String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T t) {
        if (t instanceof Student) {
            Student student = (Student) t;
            return student.getName().fullName.toLowerCase().contains(keywords.get(PREFIX_NAME).toLowerCase())
                    && student.getAddress().value.toLowerCase().contains(keywords.get(PREFIX_ADDRESS).toLowerCase())
                    && student.getEmail().value.toLowerCase().contains(keywords.get(PREFIX_EMAIL).toLowerCase())
                    && student.getPhone().value.toLowerCase().contains(keywords.get(PREFIX_PHONE).toLowerCase())
                    && student.getSchool().school.toLowerCase()
                    .contains(keywords.get(PREFIX_SUBJECT_OR_SCHOOL).toLowerCase())
                    && student.getLevel().level.toLowerCase().contains(keywords.get(PREFIX_LEVEL).toLowerCase())
                    && (student.getTags().stream().anyMatch(tag -> tag.tagName.toLowerCase()
                    .contains(keywords.get(PREFIX_TAG).toLowerCase()))
                    || (student.getTags().isEmpty() && keywords.get(PREFIX_TAG).equals("")));
        }
        throw new ClassCastException("StudentContainsKeywords predicate can only be applied to Student.");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StudentContainsKeywordsPredicate<?>) other).keywords)); // state check
    }
}
