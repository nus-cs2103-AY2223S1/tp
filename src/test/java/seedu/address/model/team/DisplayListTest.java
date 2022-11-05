package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTasks.TASK_CODE;
import static seedu.address.testutil.TypicalTasks.TASK_REVIEW;

import java.util.Collections;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.transformation.FilteredList;

public class DisplayListTest {
    private DisplayList<Task> displayList;
    private TaskList taskList;
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.add(TASK_CODE);
        taskList.add(TASK_REVIEW);
        displayList =  new DisplayList(taskList.asUnmodifiableObservableList());
    }

    @Test
    public void setPredicate_noPredicate_returnSucess() {
        TaskNameContainsKeywordsPredicate predicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("code"));
        displayList.setPredicate(predicate);
        FilteredList<Task> filtered = new FilteredList<>(taskList.asUnmodifiableObservableList(), predicate);
        assertEquals(filtered, displayList.getFilteredDisplayList());
    }

    @Test
    public void setComparator_noComparator_returnSucess() {
        TaskList reverseSortedTaskList = new TaskList();
        reverseSortedTaskList.add(TASK_REVIEW);
        reverseSortedTaskList.add(TASK_CODE);
        Comparator<Task> comparator =  (t1, t2) -> t2.getName().toString().compareTo(t1.getName().toString());
        displayList.setComparator(comparator);
        FilteredList<Task> filtered = new FilteredList<>(reverseSortedTaskList.asUnmodifiableObservableList());
        assertEquals(filtered, displayList.getFilteredDisplayList());
    }

    @Test
    public void equalsTest() {
        assertTrue(displayList.equals(displayList));
        assertFalse(displayList.equals(2));
    }

}