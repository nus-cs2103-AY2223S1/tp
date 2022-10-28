package seedu.watson.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_REMARK_COFFEE;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_REMARK_ICE_CREAM;
import static seedu.watson.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.watson.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.watson.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.watson.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.watson.testutil.TypicalStudents.getTypicalDatabase;

import org.junit.jupiter.api.Test;

import seedu.watson.commons.core.Messages;
import seedu.watson.commons.core.index.Index;
import seedu.watson.model.Model;
import seedu.watson.model.ModelManager;
import seedu.watson.model.UserPrefs;
import seedu.watson.model.student.Remark;

public class RemarkCommandTest {
    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_ICE_CREAM));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of watson book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getPersonList().size());
        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_ICE_CREAM));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK_ICE_CREAM));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_SECOND_PERSON, new Remark(VALID_REMARK_ICE_CREAM))));

        // different remarks -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK_COFFEE))));
    }
}
