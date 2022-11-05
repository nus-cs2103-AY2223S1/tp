package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalTasks.TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_1_DETAILS;
import static seedu.address.testutil.TypicalTasks.TASK_2;
import static seedu.address.testutil.TypicalTeams.TYPICAL_MEMBERS;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    private Task taskUnderTest = TASK_1_DETAILS;

    @Test
    public void null_constructor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null, List.of(), false, null));
    }

    @Test
    public void valid_toString_equalNames() {
        assertEquals("[ ] task (Not assigned to any member yet) ", TASK_1.toString());
    }

    @Test
    public void checkAssignee_assigneeNotAssigned_returnsFalse() {
        assertFalse(taskUnderTest.checkAssignee(CARL));
    }

    @Test
    public void checkAssignee_assigneeAssigned_returnsTrue() {
        Task assignedTask = taskUnderTest.assignTo(CARL);
        assertTrue(assignedTask.checkAssignee(CARL));
    }

    @Test
    public void markTask_unmarked_returnsMarked() {
        Task markedTask = new TaskBuilder(TASK_1_DETAILS).withCompletionStatus(true).build();
        assertEquals(markedTask, taskUnderTest.mark(true));
    }

    @Test
    public void unmarkTask_marked_returnsUnmark() {
        Task markedTask = new TaskBuilder(TASK_1_DETAILS).withCompletionStatus(true).build();
        assertEquals(taskUnderTest, markedTask.mark(false));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task editedFirst = new TaskBuilder(TASK_1_DETAILS).build();
        assertTrue(TASK_1_DETAILS.equals(editedFirst));

        // same object -> returns true
        assertTrue(TASK_1_DETAILS.equals(TASK_1_DETAILS));

        // null -> returns false
        assertFalse(TASK_1_DETAILS.equals(null));

        // different type -> returns false
        assertFalse(TASK_1_DETAILS.equals(5));

        // different task -> returns false
        assertFalse(TASK_1_DETAILS.equals(TASK_2));

        // different name -> returns false
        Task firstDifferent = new TaskBuilder(TASK_1_DETAILS).withName("otherName").build();
        assertFalse(TASK_1_DETAILS.equals(firstDifferent));

        // different deadline returns false
        firstDifferent = new TaskBuilder(TASK_1_DETAILS).withDeadline("2024-12-25 23:59").build();
        assertFalse(TASK_1_DETAILS.equals(firstDifferent));

        // different completion status -> returns false
        firstDifferent = new TaskBuilder(TASK_1_DETAILS).withCompletionStatus(true).build();
        assertFalse(TASK_1_DETAILS.equals(firstDifferent));

        // different assignees -> returns false
        List<Person> members = new ArrayList<>();
        for (Person p : TYPICAL_MEMBERS) {
            members.add(p);
        }
        members.add(CARL);
        firstDifferent = new TaskBuilder(TASK_1_DETAILS).withAssignees(members).build();
        assertFalse(TASK_1_DETAILS.equals(firstDifferent));
    }
}
