package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.position.Student;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.StudentBuilder;

public class AddAssignmentsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentsCommand(null));
    }

    @Test
    public void execute_addValidAssignments_andSuccessful() throws Exception {
        Student student = new StudentBuilder().build();

        ArrayList<Assignment> assignmentsList = new ArrayList<>();
        Assignment assignment1 = new AssignmentBuilder().withName("Assignment 1").withGrade("0/0")
                .withWeightage("15").build();
        Assignment assignment2 = new AssignmentBuilder().withName("Assignment 2").withGrade("0/0")
                .withWeightage("15").build();
        Assignment midterms = new AssignmentBuilder().withName("Midterms").withGrade("0/0")
                .withWeightage("30").build();
        Assignment finals = new AssignmentBuilder().withName("Finals").withGrade("0/0")
                .withWeightage("40").build();
        assignmentsList.add(assignment1);
        assignmentsList.add(assignment2);
        assignmentsList.add(midterms);
        assignmentsList.add(finals);

        ArrayList<Assignment> assignmentsAdded = new ArrayList<>(assignmentsList);
        String assignments = "Assignment 1 w/15, Assignment 2 w/15, Midterms w/30, Finals w/40";

        CommandResult commandResult = new AddAssignmentsCommand(assignments).execute(model);

        assertEquals(String.format(AddAssignmentsCommand.MESSAGE_ADD_ASSIGNMENTS_SUCCESS, assignments),
                commandResult.getFeedbackToUser());
        assertEquals(student.setAssignments(assignments), assignmentsAdded);
    }


    @Test
    public void equals() {
        String assignments1 = "Assignment 1 w/15, Assignment 2 w/15, Midterms w/30, Finals w/40";
        String assignments2 = "Midterms w/50, Finals w/50";
        AddAssignmentsCommand addAssignmentsCommand1 = new AddAssignmentsCommand(assignments1);
        AddAssignmentsCommand addAssignmentsCommand2 = new AddAssignmentsCommand(assignments2);

        // same object -> returns true
        assertTrue(addAssignmentsCommand1.equals(addAssignmentsCommand1));

        // same values -> returns true
        AddAssignmentsCommand addAssignmentsCommand1Copy = new AddAssignmentsCommand(assignments1);
        assertTrue(addAssignmentsCommand1Copy.equals(addAssignmentsCommand1));

        // different types -> returns false
        assertFalse(addAssignmentsCommand1.equals(1));

        // null -> returns false
        assertFalse(addAssignmentsCommand1.equals(null));

        // different patient -> returns false
        assertFalse(addAssignmentsCommand1.equals(addAssignmentsCommand2));
    }
}
