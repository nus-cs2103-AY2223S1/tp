package seedu.classify.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.classify.testutil.StudentBuilder;

public class GradeLessThanMeanPredicateTest {

    private Student s1 = new StudentBuilder().withClassName("4A").withExams("CA1 10").build();
    private Student s2 = new StudentBuilder().withClassName("4A").withExams("CA1 40").build();
    private Student s3 = new StudentBuilder().withClassName("4B").withExams("CA1 40").build();

    @Test
    public void equals() {
        Class firstClassName = new Class("4A");
        Class secondClassName = new Class("4B");

        GradeLessThanMeanPredicate firstPredicate = new GradeLessThanMeanPredicate(
                firstClassName, 10.00, "SA1");
        GradeLessThanMeanPredicate secondPredicate = new GradeLessThanMeanPredicate(
                secondClassName, 10.00, "SA1");
        GradeLessThanMeanPredicate thirdPredicate = new GradeLessThanMeanPredicate(
                firstClassName, 20.00, "SA1");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GradeLessThanMeanPredicate firstPredicateCopy = new GradeLessThanMeanPredicate(
                firstClassName, 10.00, "SA1");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Class -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // different mean -> returns false
        assertFalse(firstPredicate.equals(thirdPredicate));
    }

    @Test
    public void test_gradeLessThanMean_returnsTrue() {
        GradeLessThanMeanPredicate predicate = new GradeLessThanMeanPredicate(
                new Class("4A"), 20.00, "CA1");
        assertTrue(predicate.test(s1));
    }

    @Test
    public void test_gradeMoreThanMean_returnsFalse() {
        GradeLessThanMeanPredicate predicate = new GradeLessThanMeanPredicate(
                new Class("4A"), 20.00, "CA1");
        assertFalse(predicate.test(s2));
    }

    @Test
    public void test_differentClass_returnsFalse() {
        GradeLessThanMeanPredicate predicate = new GradeLessThanMeanPredicate(
                new Class("4A"), 20.00, "CA1");
        assertFalse(predicate.test(s3));
    }
}
