package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;

public class AssignmentTest {
    private LocalDateTime time = LocalDateTime.now();
    private Deadline validDeadline = new Deadline(time);
    private Deadline validDeadline1 = new Deadline(time.plusDays(10));
    private Assignment assignment = new Assignment("Assignment");
    private Assignment assignDifferent = new Assignment("Different");
    private Assignment assignSame = new Assignment("Assignment");
    private Assignment assignmentWD = new Assignment("Assignment", Workload.HIGH, validDeadline);
    private Assignment assignDiffD = new Assignment("Assignment", Workload.HIGH, validDeadline1);
    private Assignment assignDiffW = new Assignment("Assignment", Workload.LOW, validDeadline);

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
    public void toStringTest_withWorkloadAndDeadline() {
        assertFalse(assignmentWD.toString().equals("Assignment"));
        assertFalse(assignmentWD.toString().equals(assignDiffW.toString()));
        assertFalse(assignmentWD.toString().equals(assignDiffD.toString()));
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
