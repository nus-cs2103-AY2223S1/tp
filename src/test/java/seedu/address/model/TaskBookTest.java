package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalToDos.FIRST;
import static seedu.address.testutil.TypicalToDos.getTypicalTaskBookWithToDos;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class TaskBookTest {

    private final TaskBook taskBook = new TaskBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskBook.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskBook_replacesData() {
        TaskBook newData = getTypicalTaskBookWithToDos();
        taskBook.resetData(newData);
        assertEquals(newData, taskBook);
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskBook.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskBook_returnsFalse() {
        assertFalse(taskBook.hasTask(FIRST));
    }

    @Test
    public void hasTask_taskInTaskBook_returnsTrue() {
        taskBook.addTask(FIRST);
        assertTrue(taskBook.hasTask(FIRST));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskBook.getTaskList().remove(0));
    }

}
