package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_LARGE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class HomeworkListTest {

    private static final Homework HOMEWORK_STUB = new Homework("MATH: A");

    private static final Homework HOMEWORK_STUB_2 = new Homework("SCI: B");

    private HomeworkList homeworkList = new HomeworkList();

    @Test
    public void execute_index_failure() {
        // clear off list before starting test
        homeworkList.clearList();

        homeworkList.addHomework(HOMEWORK_STUB);
        homeworkList.addHomework(HOMEWORK_STUB_2);

        // index check for remove
        assertThrows(IllegalArgumentException.class , ()
                -> homeworkList.removeAtIndex(INDEX_LARGE));

        // index check for edit
        assertThrows(IllegalArgumentException.class , ()
                -> homeworkList.editAtIndex(INDEX_LARGE, HOMEWORK_STUB_2));

        // index check for mark
        assertThrows(IllegalArgumentException.class , ()
                -> homeworkList.markAtIndex(INDEX_LARGE));

        // index check for unmark
        assertThrows(IllegalArgumentException.class , ()
                -> homeworkList.unmarkAtIndex(INDEX_LARGE));

        // clear off list
        homeworkList.clearList();
    }

    @Test
    public void test_unmarkAndMarkAtIndex() {
        // clear off list before starting test
        homeworkList.clearList();

        homeworkList.addHomework(HOMEWORK_STUB);

        homeworkList.markAtIndex(Index.fromOneBased(1));
        assertTrue(HOMEWORK_STUB.getIsCompleted());

        homeworkList.unmarkAtIndex(Index.fromOneBased(1));
        assertFalse(HOMEWORK_STUB.getIsCompleted());

        // clear off list
        homeworkList.clearList();
    }
}
