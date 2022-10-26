package seedu.classify.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Id} matches the id provided.
 */
public class NamePredicate implements Predicate<Student> {
    private Name name;

    public NamePredicate(Name name) {
        this.name = name;
    }

    @Override
    public boolean test(Student student) {
        return student.getStudentName().equals(name);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof NamePredicate && name.equals(((NamePredicate) other).name));
    }
}
