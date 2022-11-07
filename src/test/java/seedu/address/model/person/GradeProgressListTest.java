package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_LARGE;

import org.junit.jupiter.api.Test;

public class GradeProgressListTest {

    private static final GradeProgress GRADE_PROGRESS_STUB = new GradeProgress("MATH: A");

    private static final GradeProgress GRADE_PROGRESS_STUB_2 = new GradeProgress("SCI: B");

    private GradeProgressList gradeProgressList = new GradeProgressList();


    @Test
    public void execute_index_failure() {
        // clear list
        gradeProgressList.clearList();

        gradeProgressList.addGradeProgress(GRADE_PROGRESS_STUB);
        gradeProgressList.addGradeProgress(GRADE_PROGRESS_STUB_2);

        // index check for remove
        assertThrows(IllegalArgumentException.class , ()
                -> gradeProgressList.removeAtIndex(INDEX_LARGE));

        // index check for edit
        assertThrows(IllegalArgumentException.class , ()
                -> gradeProgressList.editAtIndex(INDEX_LARGE, GRADE_PROGRESS_STUB_2));

        // clear list
        gradeProgressList.clearList();
    }

    @Test
    public void test_toString() {
        // clear list
        gradeProgressList.clearList();

        gradeProgressList.addGradeProgress(GRADE_PROGRESS_STUB);
        gradeProgressList.addGradeProgress(GRADE_PROGRESS_STUB_2);
        String expectedToString = "1. MATH: A, 2. SCI: B";
        assertEquals(gradeProgressList.toString(), expectedToString);

        // clear list
        gradeProgressList.clearList();
    }
}
