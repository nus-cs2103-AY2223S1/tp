package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.model.person.exceptions.TaskNotFoundException;


class TaskListTest {

    @Test
    void contains() {
        TaskList taskList = new TaskList();
        taskList.add(new Task("task"));
        assertTrue(taskList.contains(new Task("task")));
        assertFalse(taskList.contains(new Task("task123")));
    }

    @Test
    void add() {
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> taskList.add(new Task("task")));
        assertThrows(DuplicateTaskException.class, () -> taskList.add(new Task("task")));
    }

    @Test
    void remove() {
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> taskList.add(new Task("task")));
        assertDoesNotThrow(() -> taskList.remove(new Task("task")));
        assertThrows(TaskNotFoundException.class, () -> taskList.remove(new Task("task")));
    }

    @Test
    void testEquals() {
        TaskList taskList1 = new TaskList();
        TaskList taskList2 = new TaskList();
        Task task = new Task("task");
        taskList1.add(task);
        taskList2.add(task);
        assertTrue(taskList1.equals(taskList2));
        taskList1.add(new Task("task 123"));
        assertFalse(taskList1.equals(taskList2));
    }
}
