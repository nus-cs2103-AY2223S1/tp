package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTasks.TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_1_DUPLICATED;
import static seedu.address.testutil.TypicalTasks.TASK_2;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.model.person.exceptions.TaskNotFoundException;


class TaskListTest {

    @Test
    void contains() {
        TaskList taskList = new TaskList();
        taskList.add(TASK_1);
        assertTrue(taskList.contains(TASK_1));
        assertTrue(taskList.contains(TASK_1_DUPLICATED));
        assertFalse(taskList.contains(TASK_2));
    }

    @Test
    void add() {
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> taskList.add(TASK_1));
        assertThrows(DuplicateTaskException.class, () -> taskList.add(TASK_1_DUPLICATED));
    }

    @Test
    void remove() {
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> taskList.add(TASK_1));
        assertDoesNotThrow(() -> taskList.remove(TASK_1));
        assertThrows(TaskNotFoundException.class, () -> taskList.remove(TASK_1));
    }

    @Test
    void testEquals() {
        TaskList taskList1 = new TaskList();
        TaskList taskList2 = new TaskList();
        taskList1.add(TASK_1);
        taskList2.add(TASK_1);
        assertTrue(taskList1.equals(taskList2));
        taskList1.add(TASK_2);
        assertFalse(taskList1.equals(taskList2));
    }
}
