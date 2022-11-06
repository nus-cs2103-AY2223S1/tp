package seedu.taassist.model.student.predicate;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Student;

/**
 * Tests that a {@code Student} is part of a class.
 */
public class IsPartOfClassPredicate implements Predicate<Student> {
    private final ModuleClass targetClass;

    /**
     * Returns a predicate that checks if a Student object is part of the {@code targetClass}.
     *
     * @param targetClass Class to check against the Student object.
     */
    public IsPartOfClassPredicate(ModuleClass targetClass) {
        requireNonNull(targetClass);
        this.targetClass = targetClass;
    }

    /**
     * Tests if a Student object is part of the target class.
     *
     * @param student The Student object to test.
     * @return True, if the Student object is part of the target class.
     */
    @Override
    public boolean test(Student student) {
        return student.getModuleClasses().stream()
                .anyMatch(moduleClass -> moduleClass.isSame(targetClass));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsPartOfClassPredicate // instanceof handles nulls
                && targetClass.equals(((IsPartOfClassPredicate) other).targetClass)); // state check
    }
}
