package jeryl.fyp.model.student;

import static jeryl.fyp.testutil.Assert.assertThrows;
import static jeryl.fyp.testutil.TypicalDeadlines.TASK_A;
import static jeryl.fyp.testutil.TypicalDeadlines.TASK_B;
import static jeryl.fyp.testutil.TypicalDeadlines.TASK_C;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import jeryl.fyp.model.student.exceptions.DeadlineNotFoundException;
import jeryl.fyp.model.student.exceptions.DuplicateDeadlineException;

public class DeadlineListTest {

    private final DeadlineList deadlineList = new DeadlineList();

    @Test
    public void contains_nullDeadline_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deadlineList.contains(null));
    }

    @Test
    public void contains_deadlineNotInList_returnsFalse() {
        deadlineList.add(TASK_B);
        assertFalse(deadlineList.contains(TASK_A));
    }

    @Test
    public void contains_deadlineInList_returnsTrue() {
        deadlineList.add(TASK_A);
        assertTrue(deadlineList.contains(TASK_A));
    }

    @Test
    public void add_nullDeadline_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deadlineList.add(null));
    }

    @Test
    public void add_duplicateDeadline_throwsDuplicateDeadlineException() {
        deadlineList.add(TASK_A);
        assertThrows(DuplicateDeadlineException.class, () -> deadlineList.add(TASK_A));
    }

    @Test
    public void setDeadline_nullTargetDeadline_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deadlineList.setDeadline(null, TASK_A));
    }

    @Test
    public void setDeadline_nullEditedDeadline_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deadlineList.setDeadline(TASK_A, null));
    }

    @Test
    public void setDeadline_targetDeadlineNotInList_throwsDeadlineNotFoundException() {
        assertThrows(DeadlineNotFoundException.class, () -> deadlineList.setDeadline(TASK_A, TASK_A));
    }

    @Test
    public void setDeadline_editedDeadlineIsSameDeadline_success() {
        deadlineList.add(TASK_A);
        deadlineList.setDeadline(TASK_A, TASK_A);
        DeadlineList expectedDeadlineList = new DeadlineList();
        expectedDeadlineList.add(TASK_A);
        assertEquals(expectedDeadlineList, deadlineList);
    }
    @Test
    public void setDeadline_editedDeadlineHasDifferentIdentity_success() {
        deadlineList.add(TASK_A);
        deadlineList.setDeadline(TASK_A, TASK_C);
        DeadlineList expectedDeadlineList = new DeadlineList();
        expectedDeadlineList.add(TASK_C);
        assertEquals(expectedDeadlineList, deadlineList);
    }

    @Test
    public void setDeadline_editedDeadlineHasNonUniqueIdentity_throwsDuplicateDeadlineException() {
        deadlineList.add(TASK_A);
        deadlineList.add(TASK_B);
        assertThrows(DuplicateDeadlineException.class, () -> deadlineList.setDeadline(TASK_A, TASK_B));
    }

    @Test
    public void remove_nullDeadline_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deadlineList.remove(null));
    }

    @Test
    public void remove_deadlineDoesNotExist_throwsDeadlineNotFoundException() {
        assertThrows(DeadlineNotFoundException.class, () -> deadlineList.remove(TASK_A));
    }

    @Test
    public void remove_existingDeadline_removesDeadline() {
        deadlineList.add(TASK_A);
        deadlineList.remove(TASK_A);
        DeadlineList expectedDeadlineList = new DeadlineList();
        assertEquals(expectedDeadlineList, deadlineList);
    }

    @Test
    public void setDeadlines_nullDeadlineList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deadlineList.setDeadlines((DeadlineList) null));
    }

    @Test
    public void setStudents_deadlineList_replacesOwnListWithProvidedDeadlineList() {
        deadlineList.add(TASK_A);
        DeadlineList expectedDeadlineList = new DeadlineList();
        expectedDeadlineList.add(TASK_B);
        deadlineList.setDeadlines(expectedDeadlineList);
        assertEquals(expectedDeadlineList, deadlineList);
    }

    @Test
    public void setDeadlines_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deadlineList.setDeadlines((List<Deadline>) null));
    }

    @Test
    public void setDeadlines_list_replacesOwnListWithProvidedList() {
        deadlineList.add(TASK_A);
        List<Deadline> deadlines = Collections.singletonList(TASK_B);
        deadlineList.setDeadlines(deadlines);
        DeadlineList expectedDeadlineList = new DeadlineList();
        expectedDeadlineList.add(TASK_B);
        assertEquals(expectedDeadlineList, deadlineList);
    }

    @Test
    public void setDeadlines_listWithDuplicateDeadlines_throwsDuplicateDeadlineException() {
        List<Deadline> listWithDuplicateDeadlines = Arrays.asList(TASK_A, TASK_A);
        assertThrows(DuplicateDeadlineException.class, () -> deadlineList.setDeadlines(listWithDuplicateDeadlines));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> deadlineList.asUnmodifiableObservableList().remove(0));
    }
}
