package friday.model.student;

import java.util.List;
import java.util.function.Predicate;

import friday.commons.util.StringUtil;


/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getName().fullName, keyword)
                        || StringUtil.containsWordIgnoreCase(student.getTelegramHandle().value, keyword)
                        || StringUtil.containsWordIgnoreCase(student.getMasteryCheck().toString(), keyword)
                        || StringUtil.containsWordIgnoreCase(student.getConsultation().toString(), keyword)
                        || StringUtil.containsWordIgnoreCase(student.getRemark().value, keyword)
                        || student.getTags().stream().anyMatch(tagword ->
                        StringUtil.containsWordIgnoreCase(tagword.toSearchString(), keyword))
                        || student.getGradesList().getGradesArrayList().stream().anyMatch(tagword ->
                        StringUtil.containsWordIgnoreCase(tagword.getScore(), keyword))
                        || student.getGradesList().getGradesArrayList().stream().anyMatch(tagword ->
                        StringUtil.containsWordIgnoreCase(tagword.getExamName(), keyword))
                        || student.getGradesList().getGradesArrayList().stream().anyMatch(tagword ->
                        StringUtil.containsWordIgnoreCase(tagword.toString()
                                .replace("[", "").replace("]", "").replace(",", "").replace(": ", ":"), keyword))
                );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
