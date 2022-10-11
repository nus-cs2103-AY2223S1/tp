package seedu.address.model.student;

import java.util.function.Predicate;

public class IdMatchesPredicate implements Predicate<Student> {

    private final String id;

    public IdMatchesPredicate(String id) {
        this.id = id;
    }
    @Override
    public boolean test(Student student) {
        return student.getId().equals(this.id);
    }
}
