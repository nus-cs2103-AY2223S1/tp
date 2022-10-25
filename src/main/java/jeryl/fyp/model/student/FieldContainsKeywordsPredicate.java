package jeryl.fyp.model.student;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s particular field matches any of the keywords given.
 */
public abstract class FieldContainsKeywordsPredicate implements Predicate<Student> {
    protected final List<String> keywords;

    public FieldContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public abstract boolean test(Student student);

    public abstract boolean equals(Object other);
}
