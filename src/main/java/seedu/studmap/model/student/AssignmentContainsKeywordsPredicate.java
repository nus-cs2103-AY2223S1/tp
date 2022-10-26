package seedu.studmap.model.student;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.studmap.commons.util.StringUtil;

public class AssignmentContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public AssignmentContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        Set<Assignment> assignmentSet = student.getAssignments();
        for (Assignment assignment : assignmentSet) {
            if (keywords.stream().anyMatch(keywords ->
                    StringUtil.containsWordIgnoreCase(assignment.assignmentName, keywords))){
                return true;
            }
        }
        return false;
    }
}
