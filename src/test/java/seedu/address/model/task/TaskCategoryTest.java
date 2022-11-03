package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TaskCategoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskCategory(null));
    }


    @Test
    public void isValidTest() {
        // invalid name
        assertFalse(TaskCategory.isValidTaskCategoryName("")); // empty string
        assertFalse(TaskCategory.isValidTaskCategoryName(" ")); // spaces only
        assertFalse(TaskCategory.isValidTaskCategoryName("^")); // only non-alphanumeric characters
        assertFalse(TaskCategory.isValidTaskCategoryName("peter*")); // contains non-alphanumeric characters
        assertFalse(TaskCategory.isValidTaskCategoryName("homework")); // contains non valid task category
        assertFalse(TaskCategory.isValidTaskCategoryName("null")); // contains null

        // valid name
        assertTrue(TaskCategory.isValidTaskCategoryName("backend")); // alphabets only
        assertTrue(TaskCategory.isValidTaskCategoryName("backend")); // numbers only
        assertTrue(TaskCategory.isValidTaskCategoryName("uiux")); // alphanumeric characters
        assertTrue(TaskCategory.isValidTaskCategoryName("presentation")); // with capital letters
        assertTrue(TaskCategory.isValidTaskCategoryName("others")); // long names
    }

    @Test
    public void equals() {
        Optional<TaskCategoryType> categoryType1 = TaskCategoryType.getFromString("backend");
        TaskCategory test1 = new TaskCategory(categoryType1.get());
        Optional<TaskCategoryType> categoryType2 = TaskCategoryType.getFromString("uiux");
        TaskCategory test2 = new TaskCategory(categoryType2.get());
        Optional<TaskCategoryType> categoryType3 = TaskCategoryType.getFromString("backend");
        TaskCategory test3 = new TaskCategory(categoryType3.get());
        Optional<TaskCategoryType> categoryType4 = TaskCategoryType.getFromString("frontend");
        TaskCategory test4 = new TaskCategory(categoryType4.get());
        Optional<TaskCategoryType> categoryType5 = TaskCategoryType.getFromString("others");
        TaskCategory test5 = new TaskCategory(categoryType5.get());

        // same object -> returns true
        assertEquals(test1, test1);

        // null -> returns false
        assertNotEquals(null, test1);

        // different types -> returns false
        assertNotEquals(test1, categoryType1);

        // same taskCategoryType -> returns true
        assertEquals(test1, test3);

        // different taskCategoryType -> returns false
        assertNotEquals(test1, test4);
        assertNotEquals(test1, test5);
        assertNotEquals(test1, test2);
        assertNotEquals(test2, test3);
        assertNotEquals(test2, test4);
        assertNotEquals(test2, test5);
        assertNotEquals(test3, test4);
        assertNotEquals(test3, test5);
        assertNotEquals(test3, test2);
        assertNotEquals(test2, test1);
        assertNotEquals(test2, test3);
        assertNotEquals(test2, test4);
        assertNotEquals(test2, test5);
    }

}
