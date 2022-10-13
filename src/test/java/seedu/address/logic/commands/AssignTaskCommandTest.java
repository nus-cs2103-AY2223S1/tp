package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AssignTaskCommandTest {

    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist. "
            + "There are less than %1$s tasks in your list.";
    public static final String MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS = "This member does not exist. "
            + "There are less than %1$s members in your team.";

    private static final int FIRST_TASK = 1;
    private static final int SECOND_TASK = 2;
    private static final String FIRST_PERSON = "Alex";
    private static final String SECOND_PERSON = "Bernice";

    @Test
    public void equals() {
        AssignTaskCommand assignTaskFirstCommand = new AssignTaskCommand(FIRST_TASK, FIRST_PERSON);
        AssignTaskCommand assignTaskSecondCommand = new AssignTaskCommand(SECOND_TASK, FIRST_PERSON);
        AssignTaskCommand assignTaskThirdCommand = new AssignTaskCommand(FIRST_TASK, SECOND_PERSON);

        // Same Assign Task Commands should be equal.
        assertTrue(assignTaskFirstCommand.equals(assignTaskFirstCommand));

        // Assign Task commands with same task but different members should not be considered equal.
        assertFalse(assignTaskFirstCommand.equals(assignTaskThirdCommand));

        // Assign Task commands with same member but different tasks should not be considered equal.
        assertFalse(assignTaskFirstCommand.equals(assignTaskSecondCommand));

        // Assign Task commands with wrong type should return false
        assertFalse(assignTaskFirstCommand.equals(2));

        // Assign Task command and null are not considered equal.
        assertFalse(assignTaskFirstCommand.equals(null));
    }
}
