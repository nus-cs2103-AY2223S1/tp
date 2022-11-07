package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code GradeCommand}.
 */
public class GradeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndexPerson_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        GradeCommand gradeCommand = new GradeCommand(outOfBoundIndex, Index.fromOneBased(1), "0/0");

        assertCommandFailure(gradeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_notStudent_throwsCommandException() {
        Index nonStudentTIndex = Index.fromOneBased(1);
        GradeCommand gradeCommand = new GradeCommand(nonStudentTIndex, Index.fromOneBased(1), "0/0");

        assertCommandFailure(gradeCommand, model, GradeCommand.MESSAGE_PERSON_NOT_STUDENT);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        GradeCommand gradeCommand = new GradeCommand(outOfBoundIndex, Index.fromOneBased(1), "0/0");

        assertCommandFailure(gradeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        GradeCommand gradeFirstCommand = new GradeCommand(INDEX_FIRST_PERSON, Index.fromOneBased(1), "0/0");
        GradeCommand gradeSecondCommand = new GradeCommand(INDEX_SECOND_PERSON, Index.fromOneBased(1), "0/0");

        // same object -> returns true
        assertTrue(gradeFirstCommand.equals(gradeFirstCommand));

        // same values -> returns true
        GradeCommand gradeFirstCommandCopy = new GradeCommand(INDEX_FIRST_PERSON, Index.fromOneBased(1), "0/0");
        assertTrue(gradeFirstCommand.equals(gradeFirstCommandCopy));

        // different types -> returns false
        assertFalse(gradeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(gradeFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(gradeFirstCommand.equals(gradeSecondCommand));
    }

}
