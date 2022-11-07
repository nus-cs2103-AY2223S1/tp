package seedu.taassist.model.student.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1101S;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalStudents.ALICE;
import static seedu.taassist.testutil.TypicalStudents.BENSON;

import org.junit.jupiter.api.Test;

class IsPartOfClassPredicateTest {

    @Test
    void test() {
        // student has one class
        assertTrue(new IsPartOfClassPredicate(CS1231S).test(ALICE));

        // student has multiple classes
        assertTrue(new IsPartOfClassPredicate(CS1231S).test(BENSON));

        // student is not part of class
        assertFalse(new IsPartOfClassPredicate(CS1101S).test(ALICE));
    }

    @Test
    void equals() {
        IsPartOfClassPredicate firstPredicate = new IsPartOfClassPredicate(CS1231S);
        IsPartOfClassPredicate secondPredicate = new IsPartOfClassPredicate(CS1101S);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same class -> returns true
        IsPartOfClassPredicate firstPredicateCopy = new IsPartOfClassPredicate(CS1231S);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different class -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}