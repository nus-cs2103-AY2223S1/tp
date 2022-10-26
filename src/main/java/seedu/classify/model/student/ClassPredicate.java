package seedu.classify.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Class} matches the class name given.
 */
public class ClassPredicate implements Predicate<Student> {
    private Class className;

    public ClassPredicate(Class className) {
        this.className = className;
    }

    @Override
    public boolean test(Student student) {
        return student.getClassName().className.equalsIgnoreCase(this.className.className);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassPredicate // instanceof handles nulls
                && className.equals(((ClassPredicate) other).className)); // state check
    }

}
