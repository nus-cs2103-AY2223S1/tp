package seedu.classify.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.classify.testutil.StudentBuilder;

public class ClassPredicateTest {

    @Test
    public void equals() {

        ClassPredicate firstPredicate = new ClassPredicate(new Class("CLASS1"));
        ClassPredicate secondPredicate = new ClassPredicate(new Class("CLASS2"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ClassPredicate firstPredicateCopy = new ClassPredicate(new Class("CLASS1"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different class -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_classPredicate_returnsTrue() {
        // Match single word
        ClassPredicate predicate = new ClassPredicate(new Class("4A1"));
        assertTrue(predicate.test(new StudentBuilder().withClassName("4A1").build()));

        // Match multiple word
        predicate = new ClassPredicate(new Class("CLASS 4A1"));
        assertTrue(predicate.test(new StudentBuilder().withClassName("CLASS 4A1").build()));

        // Match numbers
        predicate = new ClassPredicate(new Class("12345"));
        assertTrue(predicate.test(new StudentBuilder().withClassName("12345").build()));

        // Mixed-case keywords
        predicate = new ClassPredicate(new Class("4A1"));
        assertTrue(predicate.test(new StudentBuilder().withClassName("4a1").build()));
    }

    @Test
    public void test_classPredicate_returnsFalse() {
        // Non-matching keyword
        ClassPredicate predicate = new ClassPredicate(new Class("CLASS21"));
        assertFalse(predicate.test(new StudentBuilder().withStudentName("Alice Bob").build()));

        // Part of keyword matches
        predicate = new ClassPredicate(new Class("17S68"));
        assertFalse(predicate.test(new StudentBuilder().withStudentName("Alice").withPhone("12345")
                .withId("901B").withEmail("alice@gmail.com").withClassName("17S").build()));
    }
}
