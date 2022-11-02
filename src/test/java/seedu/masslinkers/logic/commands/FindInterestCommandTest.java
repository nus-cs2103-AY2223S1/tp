package seedu.masslinkers.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_ONE_STUDENT_LISTED_OVERVIEW;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.masslinkers.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.masslinkers.testutil.TypicalStudents.ALICE;
import static seedu.masslinkers.testutil.TypicalStudents.BENSON;
import static seedu.masslinkers.testutil.TypicalStudents.DANIEL;
import static seedu.masslinkers.testutil.TypicalStudents.getTypicalMassLinkers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.ModelManager;
import seedu.masslinkers.model.UserPrefs;
import seedu.masslinkers.model.student.StudentContainsInterestPredicate;
//@@author chm252
/**
 * Contains integration tests (interaction with the Model) for {@code FindInterestCommandTest}.
 */
public class FindInterestCommandTest {
    private Model model = new ModelManager(getTypicalMassLinkers(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalMassLinkers(), new UserPrefs());

    @Test
    public void equals() {
        StudentContainsInterestPredicate firstPredicate =
                new StudentContainsInterestPredicate(Collections.singletonList("first"));
        StudentContainsInterestPredicate secondPredicate =
                new StudentContainsInterestPredicate(Collections.singletonList("second"));

        FindInterestCommand findInterestFirstCommand = new FindInterestCommand(firstPredicate);
        FindInterestCommand findInterestSecondCommand = new FindInterestCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findInterestFirstCommand.equals(findInterestFirstCommand));

        // same values -> returns true
        FindInterestCommand findFirstCommandCopy = new FindInterestCommand(firstPredicate);
        assertTrue(findInterestFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findInterestFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findInterestFirstCommand.equals(null));

        // different interest -> returns false
        assertFalse(findInterestFirstCommand.equals(findInterestSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        StudentContainsInterestPredicate predicate = preparePredicate(" ");
        FindInterestCommand command = new FindInterestCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel,
                false, false, true, false);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_oneKeyword_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        StudentContainsInterestPredicate predicate = preparePredicate("AI");
        FindInterestCommand command = new FindInterestCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel,
                false, false, true, false);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_oneStudentFound() {
        String expectedMessage = String.format(MESSAGE_ONE_STUDENT_LISTED_OVERVIEW, 1);
        StudentContainsInterestPredicate predicate = preparePredicate("AI SWE");
        FindInterestCommand command = new FindInterestCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel,
                false, false, true, false);
        assertEquals(List.of(BENSON), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleCasingKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        StudentContainsInterestPredicate predicate = preparePredicate("ai AI Ai");
        FindInterestCommand command = new FindInterestCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel,
                false, false, true, false);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code StudentContainsInterestPredicate}.
     */
    private StudentContainsInterestPredicate preparePredicate(String userInput) {
        return new StudentContainsInterestPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
