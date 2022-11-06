package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.person.Patient;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewPatientCommand.
 */
public class ViewPatientCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getUninurseBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToView = model.getPatient(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        ViewPatientCommand viewPatientCommand = new ViewPatientCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(ViewPatientCommand.MESSAGE_SUCCESS, patientToView.getName());

        assertCommandSuccess(viewPatientCommand, model, expectedMessage,
                ViewPatientCommand.COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ViewPatientCommand viewPatientCommand = new ViewPatientCommand(outOfBoundIndex);

        assertCommandFailure(viewPatientCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        Patient patientToView = model.getPatient(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        ViewPatientCommand viewPatientCommand = new ViewPatientCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(ViewPatientCommand.MESSAGE_SUCCESS, patientToView.getName());

        assertCommandSuccess(viewPatientCommand, model, expectedMessage,
                ViewPatientCommand.COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        ViewPatientCommand viewPatientCommand = new ViewPatientCommand(outOfBoundIndex);

        assertCommandFailure(viewPatientCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewPatientCommand viewPatientFirstCommand = new ViewPatientCommand(INDEX_FIRST_PERSON);
        ViewPatientCommand viewPatientSecondCommand = new ViewPatientCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(viewPatientFirstCommand, viewPatientFirstCommand);

        // same values -> returns true
        ViewPatientCommand viewPatientFirstCommandCopy = new ViewPatientCommand(INDEX_FIRST_PERSON);
        assertEquals(viewPatientFirstCommand, viewPatientFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, viewPatientFirstCommand);

        // null -> returns false
        assertNotEquals(null, viewPatientFirstCommand);

        // different person -> returns false
        assertNotEquals(viewPatientFirstCommand, viewPatientSecondCommand);
    }
}
