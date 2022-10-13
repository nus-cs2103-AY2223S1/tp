package seedu.phu.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.phu.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.phu.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.phu.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.phu.testutil.TypicalIndexes.INDEXES_FIRST_AND_SECOND_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEXES_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEXES_SECOND_AND_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternshipBook;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.phu.commons.core.Messages;
import seedu.phu.commons.core.index.Index;
import seedu.phu.commons.core.index.Indexes;
import seedu.phu.logic.commands.exceptions.CommandException;
import seedu.phu.model.Model;
import seedu.phu.model.ModelManager;
import seedu.phu.model.UserPrefs;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.UniqueInternshipList;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());

    @Test
    public void execute_oneValidIndexUnfilteredList_success() {
        Internship internshipToDelete = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEXES_FIRST_INTERNSHIP);

        UniqueInternshipList listOfInternshipsToDelete = new UniqueInternshipList();
        listOfInternshipsToDelete.add(internshipToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INTERNSHIP_SUCCESS,
                listOfInternshipsToDelete);

        ModelManager expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
        expectedModel.deleteInternship(internshipToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleValidIndexesUnfilteredList_success() {
        Internship firstInternshipToDelete = model.getFilteredInternshipList()
                .get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship secondInternshipToDelete = model.getFilteredInternshipList()
                .get(INDEX_SECOND_INTERNSHIP.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEXES_FIRST_AND_SECOND_INTERNSHIP);

        ModelManager expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
        expectedModel.deleteInternship(firstInternshipToDelete);
        expectedModel.deleteInternship(secondInternshipToDelete);

        try {
            deleteCommand.execute(model);
            assertEquals(model, expectedModel);
        } catch (CommandException ce) {
            fail("Should not have thrown any exception.");
        }
    }

    @Test
    public void execute_invalidOneIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(new Indexes(Set.of(outOfBoundIndex)));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidMultipleIndexesUnfilteredList_throwsCommandException() {
        Index firstIndex = Index.fromOneBased(1);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(
                new Indexes(Set.of(firstIndex, outOfBoundIndex)));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validOneIndexFilteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship internshipToDelete = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEXES_FIRST_INTERNSHIP);

        UniqueInternshipList listOfInternshipsToDelete = new UniqueInternshipList();
        listOfInternshipsToDelete.add(internshipToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INTERNSHIP_SUCCESS,
                listOfInternshipsToDelete);

        Model expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
        expectedModel.deleteInternship(internshipToDelete);
        showNoInternship(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleIndexesFilteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP, INDEX_SECOND_INTERNSHIP);

        Internship firstInternshipToDelete = model.getFilteredInternshipList()
                .get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship secondInternshipToDelete = model.getFilteredInternshipList()
                .get(INDEX_SECOND_INTERNSHIP.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEXES_FIRST_AND_SECOND_INTERNSHIP);

        Model expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
        expectedModel.deleteInternship(firstInternshipToDelete);
        expectedModel.deleteInternship(secondInternshipToDelete);
        showNoInternship(expectedModel);

        try {
            deleteCommand.execute(model);
            assertEquals(model, expectedModel);
        } catch (CommandException ce) {
            fail("Should not have thrown any exception.");
        }
    }

    @Test
    public void execute_invalidOneIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of internship book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipBook().getInternshipList().size());

        DeleteCommand deleteCommand = new DeleteCommand(new Indexes(Set.of(outOfBoundIndex)));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidMultipleIndexesFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Index validIndex = INDEX_FIRST_INTERNSHIP;
        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of internship book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipBook().getInternshipList().size());

        DeleteCommand deleteCommand = new DeleteCommand(
                new Indexes(Set.of(validIndex, outOfBoundIndex)));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEXES_FIRST_INTERNSHIP);
        DeleteCommand deleteFirstAndSecondCommand = new DeleteCommand(INDEXES_FIRST_AND_SECOND_INTERNSHIP);
        DeleteCommand deleteSecondAndFirstCommand = new DeleteCommand(INDEXES_SECOND_AND_FIRST_INTERNSHIP);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEXES_FIRST_INTERNSHIP);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different internships -> returns false
        assertFalse(deleteFirstCommand.equals(deleteFirstAndSecondCommand));

        // different order but same internships -> return true
        assertTrue(deleteFirstAndSecondCommand.equals(deleteSecondAndFirstCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoInternship(Model model) {
        model.updateFilteredInternshipList(p -> false);

        assertTrue(model.getFilteredInternshipList().isEmpty());
    }
}
