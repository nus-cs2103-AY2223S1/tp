package seedu.studmap.model.student;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.studmap.commons.util.StringUtil;
import seedu.studmap.model.tag.Tag;




/**
 * Tests that a {@code Student}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        Set<Tag> tagSet = student.getTags();
        for (Tag t : tagSet) {
            if (keywords.stream().anyMatch(keywords ->
                    StringUtil.containsWordIgnoreCase(t.tagName, keywords))) {
                return true;
            }

        }
        return false;
    }

}
