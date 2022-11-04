package seedu.address.model.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_EXAMONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EXAMONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CS2030;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExams.FINAL_EXAM;
import static seedu.address.testutil.TypicalExams.MIDTERM_EXAM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditExamCommand;
import seedu.address.model.exam.exceptions.NoLinkedTaskForExamException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.EditExamDescriptorBuilder;
import seedu.address.testutil.ExamBuilder;

public class ExamTest {

    @Test
    public void setNumOfCompletedTasks() {
        Exam expectedExam = new ExamBuilder(FINAL_EXAM).withNumOfCompletedTasks(2).build();
        assertTrue(FINAL_EXAM.setNumOfCompletedTasks(2).hasAllSameFields(expectedExam));
    }

    @Test
    public void setTotalNumOfTasks() {
        Exam expectedExam = new ExamBuilder(FINAL_EXAM).withTotalNumOfTasks(2).build();
        assertTrue(FINAL_EXAM.setTotalNumOfTasks(2).hasAllSameFields(expectedExam));
    }

    @Test
    public void hasTasks() {
        // no tasks
        assertFalse(FINAL_EXAM.hasTasks());

        // has tasks
        Exam linkedExam = new ExamBuilder(FINAL_EXAM).withTotalNumOfTasks(5).build();
        assertTrue(linkedExam.hasTasks());
    }

    @Test
    public void getPercentageCompleted() {
        // no linked tasks
        assertThrows(NoLinkedTaskForExamException.class, () -> FINAL_EXAM.getPercentageCompleted());

        // no completed tasks
        Exam examOne = new ExamBuilder(FINAL_EXAM)
            .withTotalNumOfTasks(5).build();
        assertEquals(0, examOne.getPercentageCompleted());

        // 2 out of 5 completed tasks
        Exam examTwo = new ExamBuilder(FINAL_EXAM)
            .withTotalNumOfTasks(5).withNumOfCompletedTasks(2).build();
        assertEquals(0.4, examTwo.getPercentageCompleted());
    }

    @Test
    public void generateProgressMessage_noTasks_success() {
        String expectedMessage = Exam.MESSAGE_NO_TASKS_FOR_EXAM;
        assertEquals(expectedMessage, FINAL_EXAM.generateProgressMessage());
    }

    @Test
    public void generateProgressMessage_hasTasks_success() {
        Exam exam = new ExamBuilder(FINAL_EXAM).withNumOfCompletedTasks(2).withTotalNumOfTasks(3).build();
        String expectedMessage = "2 / 3 task(s) completed";
        assertEquals(expectedMessage, exam.generateProgressMessage());
    }

    @Test
    public void edit_withEditExamDescriptor() {

        // null descriptor
        assertThrows(NullPointerException.class, () -> MIDTERM_EXAM.edit(null));

        // different module
        Exam exam = new ExamBuilder(MIDTERM_EXAM).withNumOfCompletedTasks(2).withTotalNumOfTasks(3).build();
        EditExamCommand.EditExamDescriptor descriptorModule = new EditExamDescriptorBuilder()
            .withModule(VALID_MODULE_CS2030).build();
        Exam expectedExam = new ExamBuilder(exam).withModule(VALID_MODULE_CS2030)
            .withNumOfCompletedTasks(0).withTotalNumOfTasks(0).build();
        assertEquals(expectedExam, exam.edit(descriptorModule));

        // different description
        EditExamCommand.EditExamDescriptor descriptorDescription = new EditExamDescriptorBuilder()
            .withDescription(VALID_DESCRIPTION_EXAMONE).build();
        Exam editedExam = new ExamBuilder(exam).withDescription(VALID_DESCRIPTION_EXAMONE).build();
        assertEquals(editedExam, exam.edit(descriptorDescription));
    }

    @Test
    public void edit_withModuleDescriptionAndDate() {
        Exam exam = new ExamBuilder(FINAL_EXAM).withNumOfCompletedTasks(0).withTotalNumOfTasks(2).build();

        // null module, description and date
        assertThrows(NullPointerException.class, () ->
            exam.edit(null, null, null));

        // module edited
        Module module = new Module(new ModuleCode(VALID_MODULE_CS2030));
        Exam finalExamEdited = new ExamBuilder(FINAL_EXAM).withModule(VALID_MODULE_CS2030).build();
        assertEquals(finalExamEdited, exam.edit(module, null, null));

        // description edited
        ExamDescription description = new ExamDescription(VALID_DESCRIPTION_EXAMONE);
        Exam finalExamDescriptionEdited = new ExamBuilder(FINAL_EXAM)
            .withDescription(VALID_DESCRIPTION_EXAMONE).build();
        assertEquals(finalExamDescriptionEdited, exam.edit(null, description, null));

        // date edited
        ExamDate date = new ExamDate(VALID_DATE_EXAMONE);
        Exam finalExamDateEdited = new ExamBuilder(FINAL_EXAM).withDate(VALID_DATE_EXAMONE).build();
        assertEquals(finalExamDateEdited, exam.edit(null, null, date));
    }

    @Test
    public void hasAllSameFields() {
        // same object -> returns true
        assertTrue(FINAL_EXAM.hasAllSameFields(FINAL_EXAM));

        // same fields -> return true
        Exam finalExamCopy = new ExamBuilder(FINAL_EXAM).build();
        assertTrue(FINAL_EXAM.hasAllSameFields(finalExamCopy));

        // null -> returns false
        assertThrows(NullPointerException.class, () -> FINAL_EXAM.hasAllSameFields(null));

        // different description -> returns false
        Exam examWithDifferentDescription = new ExamBuilder(FINAL_EXAM).withDescription("another exam").build();
        assertFalse(FINAL_EXAM.hasAllSameFields(examWithDifferentDescription));

        // different module -> returns false
        Exam examWithDifferentModule = new ExamBuilder(FINAL_EXAM).withModule("cs2100").build();
        assertFalse(FINAL_EXAM.hasAllSameFields(examWithDifferentModule));

        // different date -> returns false
        Exam examWithDifferentDate = new ExamBuilder(FINAL_EXAM).withDate("11-11-2025").build();
        assertFalse(FINAL_EXAM.hasAllSameFields(examWithDifferentDate));

        // different number of completed tasks -> returns false
        Exam examOne = new ExamBuilder(FINAL_EXAM).withNumOfCompletedTasks(1).build();
        Exam examWithDifferentNumOfCompletedTasks = new ExamBuilder(FINAL_EXAM).withNumOfCompletedTasks(2).build();
        assertFalse(examOne.hasAllSameFields(examWithDifferentNumOfCompletedTasks));

        // different date -> returns false
        Exam examTwo = new ExamBuilder(FINAL_EXAM).withTotalNumOfTasks(0).build();
        Exam examWithDifferentNumOfTotalTasks = new ExamBuilder(FINAL_EXAM).withTotalNumOfTasks(1).build();
        assertFalse(examTwo.hasAllSameFields(examWithDifferentNumOfTotalTasks));
    }

    @Test
    public void isEqual() {
        Exam finalExam = FINAL_EXAM;

        // same object -> returns true
        assertTrue(finalExam.equals(finalExam));

        // same fields -> return true
        Exam finalExamCopy = new ExamBuilder(FINAL_EXAM).build();
        assertTrue(finalExam.equals(finalExamCopy));

        // different types -> returns false
        assertFalse(finalExam.equals(5));

        // null -> returns false
        assertFalse(finalExam.equals(null));

        // different description -> returns false
        Exam examWithDifferentDescription = new ExamBuilder(finalExam).withDescription("another exam").build();
        assertFalse(finalExam.equals(examWithDifferentDescription));

        // different module -> returns false
        Exam examWithDifferentModule = new ExamBuilder(finalExam).withModule("cs2100").build();
        assertFalse(finalExam.equals(examWithDifferentModule));

        // different date -> returns false
        Exam examWithDifferentDate = new ExamBuilder(finalExam).withDate("11-11-2025").build();
        assertFalse(finalExam.equals(examWithDifferentDate));
    }

    @Test
    public void isCorrectString() {
        String expectedResult = String.format("Module: %s; ExamDescription: %s; ExamDate: %s",
            FINAL_EXAM.getModule(), FINAL_EXAM.getDescription(), FINAL_EXAM.getExamDate());
        assertEquals(expectedResult, FINAL_EXAM.toString());
    }
}
