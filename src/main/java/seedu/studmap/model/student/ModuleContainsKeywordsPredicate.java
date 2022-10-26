package seedu.studmap.model.student;

import java.util.List;
import java.util.function.Predicate;

import seedu.studmap.commons.util.StringUtil;

public class ModuleContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public ModuleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }
    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getModule().value, keyword));
    }
}
