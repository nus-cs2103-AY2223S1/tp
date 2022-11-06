package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_NO_SUCH_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.StuNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindStuCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StuNameContainsKeywordsPredicate firstPredicate =
                new StuNameContainsKeywordsPredicate(Collections.singletonList("first"));
        StuNameContainsKeywordsPredicate secondPredicate =
                new StuNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindStuCommand findFirstCommand = new FindStuCommand(firstPredicate);
        FindStuCommand findSecondCommand = new FindStuCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStuCommand findFirstCommandCopy = new FindStuCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_NO_SUCH_STUDENT, 0);
        StuNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindStuCommand command = new FindStuCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        StuNameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindStuCommand command = new FindStuCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code StuNameContainsKeywordsPredicate}.
     */
    private StuNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new StuNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

