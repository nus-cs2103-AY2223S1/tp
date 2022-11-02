package seedu.classify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.commons.core.Messages.MESSAGE_CLASS_SORTED_BY_GRADE;
import static seedu.classify.commons.core.Messages.MESSAGE_DISPLAY_MEAN;
import static seedu.classify.commons.core.Messages.MESSAGE_STUDENT_CLASS_NOT_FOUND;
import static seedu.classify.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.classify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.classify.testutil.TypicalStudents.ALICE;
import static seedu.classify.testutil.TypicalStudents.getTypicalStudentRecord;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.classify.model.Model;
import seedu.classify.model.ModelManager;
import seedu.classify.model.UserPrefs;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.ClassPredicate;
import seedu.classify.model.student.GradeComparator;
import seedu.classify.model.student.GradeLessThanMeanPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewStatsCommand}.
 */
class ViewStatsCommandTest {
    private Model model = new ModelManager(getTypicalStudentRecord(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStudentRecord(), new UserPrefs());

    @Test
    public void equals() {
        Class firstClassName = new Class("4a");
        Class secondClassName = new Class("4b");

        ViewStatsCommand firstCommand = new ViewStatsCommand(firstClassName, "ca1", true);
        ViewStatsCommand secondCommand = new ViewStatsCommand(secondClassName, "ca1", true);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ViewStatsCommand firstCommandCopy = new ViewStatsCommand(firstClassName, "ca1", true);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different classes -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_noClassFound_throwsCommandException() {
        Class invalidClass = new Class("fsdfsdfds");
        ViewStatsCommand viewStatsCommand = new ViewStatsCommand(invalidClass, "ca1", true);
        assertCommandFailure(viewStatsCommand, model, MESSAGE_STUDENT_CLASS_NOT_FOUND);
    }

    @Test
    public void execute_gradesMissing_throwsCommandException() {
        String expectedMessage = "There are missing grades for this particular exam"
                + "\nMean cannot be calculated.";
        ViewStatsCommand command = new ViewStatsCommand(
                new Class("3A1"), "SA1", false);
        expectedModel.updateFilteredStudentList(new ClassPredicate(new Class("3A1")));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validArgsUnfilterGrades_success() {
        String expectedMessage = String.format(MESSAGE_CLASS_SORTED_BY_GRADE, "4A1")
                + String.format(MESSAGE_DISPLAY_MEAN, "SA1", "4A1", 60.00);
        ViewStatsCommand command = new ViewStatsCommand(
                new Class("4A1"), "SA1", false);
        expectedModel.updateFilteredStudentList(new ClassPredicate(new Class("4A1")));
        expectedModel.sortStudentRecord(new GradeComparator("SA1", new Class("4A1")));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredStudentList());
    }

    @Test
    public void execute_validArgsFilteredGrades_success() {
        String expectedMessage = String.format(MESSAGE_CLASS_SORTED_BY_GRADE, "4A1")
                + String.format(MESSAGE_DISPLAY_MEAN, "SA1", "4A1", 60.00);
        ViewStatsCommand command = new ViewStatsCommand(
                new Class("4A1"), "SA1", true);
        expectedModel.updateFilteredStudentList(new ClassPredicate(new Class("4A1")));
        expectedModel.sortStudentRecord(new GradeComparator("SA1", new Class("4A1")));
        expectedModel.updateFilteredStudentList(
                new GradeLessThanMeanPredicate(new Class("4A1"), 60.00, "SA1"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredStudentList());
    }

}
