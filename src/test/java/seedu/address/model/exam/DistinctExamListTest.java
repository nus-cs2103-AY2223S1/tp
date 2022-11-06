package seedu.address.model.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_EXAMTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EXAMTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_EXAMTWO;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExams.EXAMONE;

import org.junit.jupiter.api.Test;

import seedu.address.model.exam.exceptions.DuplicateExamException;
import seedu.address.model.exam.exceptions.ExamNotFoundException;
import seedu.address.testutil.ExamBuilder;

public class DistinctExamListTest {

    private final DistinctExamList distinctExamList = new DistinctExamList();

    @Test
    public void contains_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctExamList.contains(null));
    }

    @Test
    public void contains_examNotInList_returnsFalse() {
        assertFalse(distinctExamList.contains(EXAMONE));
    }

    @Test
    public void contains_examInList_returnsTrue() {
        distinctExamList.addExam(EXAMONE);
        assertTrue(distinctExamList.contains(EXAMONE));
    }

    @Test
    public void contains_examWithSameIdentityFieldsInList_returnsTrue() {
        distinctExamList.addExam(EXAMONE);
        Exam editedExam = new ExamBuilder(EXAMONE).withDate(VALID_DATE_EXAMTWO)
                .withDescription(VALID_DESCRIPTION_EXAMTWO)
                .withModule(VALID_MODULE_EXAMTWO).build();
        distinctExamList.replaceExam(distinctExamList.examList.get(0), editedExam, false);
        assertTrue(distinctExamList.contains(editedExam));
    }

    @Test
    public void add_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctExamList.addExam(null));
    }

    @Test
    public void add_duplicateExam_throwsDuplicateExamException() {
        distinctExamList.addExam(EXAMONE);
        assertThrows(DuplicateExamException.class, () -> distinctExamList.addExam(EXAMONE));
    }

    @Test
    public void remove_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctExamList.remove(null));
    }

    @Test
    public void remove_examDoesNotExist_throwsExamNotFoundException() {
        assertThrows(ExamNotFoundException.class, () -> distinctExamList.remove(EXAMONE));
    }

    @Test
    public void remove_existingExam_removesExam() {
        distinctExamList.addExam(EXAMONE);
        distinctExamList.remove(EXAMONE);
        DistinctExamList expectedDistinctExamList = new DistinctExamList();
        assertEquals(expectedDistinctExamList, distinctExamList);
    }

}
