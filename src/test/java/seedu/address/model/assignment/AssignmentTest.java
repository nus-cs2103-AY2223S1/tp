package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;

public class AssignmentTest {
    private Assignment assignment = new Assignment("Assignment");
    private Assignment assignDifferent = new Assignment("Different");
    private Assignment assignSame = new Assignment("Assignment");

    @Test
    public void isValidAssignmentTest() {
        assertTrue(assignment.isValidAssignment("abc"));
        assertFalse(assignDifferent.isValidAssignment(" "));
    }

    @Test
    public void getAssignmentTest() {
        assertTrue(assignment.getAssignment().equals("Assignment"));
        assertTrue(assignment.getAssignment().equals(assignSame.getAssignment()));
        assertFalse(assignment.getAssignment().equals(assignDifferent.getAssignment()));
    }

    @Test
    public void toStringTest() {
        assertTrue(assignment.toString().equals("Assignment"));
        assertTrue(assignment.toString().equals(assignSame.toString()));
        assertFalse(assignment.toString().equals(assignDifferent.toString()));
    }

    @Test
    public void hashCodeTest() {
        assertTrue(assignment.hashCode() == assignSame.hashCode());
        assertTrue(assignment.hashCode() == assignment.hashCode());
        assertFalse(assignment.hashCode() == assignDifferent.hashCode());
    }

    @Test
    public void equalsTest() {
        // same object -> returns true
        assertTrue(assignment.equals(assignment));

        // null -> returns false
        assertFalse(assignment.equals(null));

        // everything same
        assertTrue(assignment.equals(assignSame));

        // different assignment name
        assertFalse(assignment.equals(assignDifferent));
    }

    @Test
    public void assignmentNotFoundExceptionTest() {
        assertTrue((new AssignmentNotFoundException().getMessage()).equals("Assignment is not found in this person!"));
    }
}
