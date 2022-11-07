package seedu.classify.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Id} matches the id provided.
 */
public class IdPredicate implements Predicate<Student> {
    private Id id;

    public IdPredicate(Id id) {
        this.id = id;
    }

    @Override
    public boolean test(Student student) {
        return student.getId().equals(id);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof IdPredicate && id.equals(((IdPredicate) other).id));
    }
}
