package seedu.masslinkers.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.masslinkers.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.masslinkers.testutil.TypicalStudents.ALICE;
import static seedu.masslinkers.testutil.TypicalStudents.BENSON;
import static seedu.masslinkers.testutil.TypicalStudents.ELLE;
import static seedu.masslinkers.testutil.TypicalStudents.getTypicalMassLinkers;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.ModelManager;
import seedu.masslinkers.model.UserPrefs;
import seedu.masslinkers.model.student.ModContainsKeywordsPredicate;
//@author chm252
/**
 * Contains integration tests (interaction with the Model) for {@code ModFindCommand}.
 */
public class ModFindCommandTest {

    private Model model = new ModelManager(getTypicalMassLinkers(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalMassLinkers(), new UserPrefs());

    @Test
    public void equals() {
        ModContainsKeywordsPredicate firstPredicate =
                new ModContainsKeywordsPredicate(Collections.singletonList("first"));
        ModContainsKeywordsPredicate secondPredicate =
                new ModContainsKeywordsPredicate(Collections.singletonList("second"));

        ModFindCommand modFindFirstCommand = new ModFindCommand(firstPredicate);
        ModFindCommand modFindSecondCommand = new ModFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(modFindFirstCommand.equals(modFindFirstCommand));

        // same values -> returns true
        ModFindCommand modFindFirstCommandCopy = new ModFindCommand(firstPredicate);
        assertTrue(modFindFirstCommand.equals(modFindFirstCommand));

        // different types -> returns false
        assertFalse(modFindFirstCommand.equals(1));

        // null -> returns false
        assertFalse(modFindFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(modFindFirstCommand.equals(modFindSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        ModContainsKeywordsPredicate predicate = preparePredicate(" ");
        ModFindCommand command = new ModFindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel,
                false, false, true, false);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_keywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        ModContainsKeywordsPredicate predicate = preparePredicate("cs1231s");
        ModFindCommand command = new ModFindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel,
                false, false, true, false);
        assertEquals(Arrays.asList(ALICE, BENSON, ELLE), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code ModContainsKeywordsPredicate}.
     */
    private ModContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ModContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }


}
