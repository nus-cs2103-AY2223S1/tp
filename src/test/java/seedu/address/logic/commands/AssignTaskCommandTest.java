package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

// TODO: Add implementation for tests
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
    }
}
