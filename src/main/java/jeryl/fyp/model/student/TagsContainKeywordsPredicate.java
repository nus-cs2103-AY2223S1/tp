package jeryl.fyp.model.student;

import java.util.List;

import jeryl.fyp.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code tags} matches any of the keywords given.
 */
public class TagsContainKeywordsPredicate extends FieldContainsKeywordsPredicate {

    public TagsContainKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> student.getTags().stream().anyMatch(
                        tagName -> StringUtil.containsPartialWordIgnoreCase(String.valueOf(tagName), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagsContainKeywordsPredicate) other).keywords)); // state check
    }
}
