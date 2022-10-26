package seedu.classify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.commons.core.Messages.MESSAGE_PERSONS_LISTED_IN_CLASS;
import static seedu.classify.commons.core.Messages.MESSAGE_SINGLE_PERSON_LISTED_IN_CLASS;
import static seedu.classify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.classify.testutil.TypicalStudents.DANIEL;
import static seedu.classify.testutil.TypicalStudents.ELLE;
import static seedu.classify.testutil.TypicalStudents.GEORGE;
import static seedu.classify.testutil.TypicalStudents.getTypicalStudentRecord;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.classify.model.Model;
import seedu.classify.model.ModelManager;
import seedu.classify.model.UserPrefs;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.ClassPredicate;

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
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_IN_CLASS, 0);
        ClassPredicate predicate = preparePredicate("XXX");
        ViewClassCommand command = new ViewClassCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_IN_CLASS, 2);
        ClassPredicate predicate = preparePredicate("3A1");
        ViewClassCommand command = new ViewClassCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE), model.getFilteredStudentList());
    }

    @Test
    public void execute_singlePersonFound() {
        String expectedMessage = String.format(MESSAGE_SINGLE_PERSON_LISTED_IN_CLASS, 1);
        ClassPredicate predicate = preparePredicate("17S68");
        ViewClassCommand command = new ViewClassCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code ClassPredicate}.
     */
    private ClassPredicate preparePredicate(String userInput) {
        return new ClassPredicate(new Class(userInput));
    }
}
