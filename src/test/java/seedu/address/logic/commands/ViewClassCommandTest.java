package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_IN_CLASS_;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.DANIEL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentRecord;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Class;
import seedu.address.model.student.ClassPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewClassCommand}.
 */
public class ViewClassCommandTest {
    private Model model = new ModelManager(getTypicalStudentRecord(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStudentRecord(), new UserPrefs());

    @Test
    public void equals() {
        ClassPredicate firstPredicate =
                new ClassPredicate(new Class("First"));
        ClassPredicate secondPredicate =
                new ClassPredicate(new Class("Second"));

        ViewClassCommand firstCommand = new ViewClassCommand(firstPredicate);
        ViewClassCommand secondCommand = new ViewClassCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ViewClassCommand firstCommandCopy = new ViewClassCommand(firstPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different classes -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_IN_CLASS_, 0);
        ClassPredicate predicate = preparePredicate("XXX");
        ViewClassCommand command = new ViewClassCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_IN_CLASS_, 2);
        ClassPredicate predicate = preparePredicate("3A1");
        ViewClassCommand command = new ViewClassCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code ClassPredicate}.
     */
    private ClassPredicate preparePredicate(String userInput) {
        return new ClassPredicate(new Class(userInput));
    }
}
