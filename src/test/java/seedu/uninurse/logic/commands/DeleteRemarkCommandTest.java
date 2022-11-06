package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.logic.commands.DeleteRemarkCommand.COMMAND_TYPE;
import static seedu.uninurse.logic.commands.DeleteRemarkCommand.MESSAGE_SUCCESS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.remark.Remark;
import seedu.uninurse.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteRemarkCommand}.
 */
public class DeleteRemarkCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullRemarkIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteRemarkCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteRemarkCommand(null, INDEX_FIRST_ATTRIBUTE));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        DeleteRemarkCommand deleteRemarkCommand =
                new DeleteRemarkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);
        assertThrows(NullPointerException.class, () -> deleteRemarkCommand.execute(null));
    }

    @Test
    void execute_validIndicesUnfilteredList_success() {
        // use second person in TypicalPersons since there is one remark to delete
        Patient patientToDeleteRemark = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToDeleteRemark).withRemarks().build();
        Remark deletedRemark = patientToDeleteRemark.getRemarks().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        DeleteRemarkCommand deleteRemarkCommand =
                new DeleteRemarkCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE);

        String expectedMessage = String.format(MESSAGE_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName(), deletedRemark);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToDeleteRemark, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(deleteRemarkCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    void execute_invalidPatientIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundPatientIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteRemarkCommand deleteRemarkCommand =
                new DeleteRemarkCommand(outOfBoundPatientIndex, INDEX_FIRST_ATTRIBUTE);

        assertCommandFailure(deleteRemarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_validIndicesFilteredList_success() {
        // use second person in TypicalPersons since there is one remark to delete
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Patient patientToDeleteRemark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withRemarks().build();
        Remark deletedRemark = patientToDeleteRemark.getRemarks().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        DeleteRemarkCommand deleteRemarkCommand =
                new DeleteRemarkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);

        String expectedMessage = String.format(MESSAGE_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName().toString(), deletedRemark);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);
        expectedModel.setPerson(patientToDeleteRemark, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(deleteRemarkCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    void execute_invalidPatientIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        DeleteRemarkCommand deleteRemarkCommand =
                new DeleteRemarkCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE);

        assertCommandFailure(deleteRemarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_invalidRemarkIndex_throwsCommandException() {
        Patient patient = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Index outOfBoundRemarkIndex = Index.fromOneBased(patient.getRemarks().size() + 1);
        DeleteRemarkCommand deleteRemarkCommand =
                new DeleteRemarkCommand(INDEX_FIRST_PERSON, outOfBoundRemarkIndex);

        assertCommandFailure(deleteRemarkCommand, model, Messages.MESSAGE_INVALID_REMARK_INDEX);
    }

    @Test
    public void equals() {
        DeleteRemarkCommand deleteFirstPersonFirstRemark =
                new DeleteRemarkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);
        DeleteRemarkCommand deleteSecondPersonFirstRemark =
                new DeleteRemarkCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE);
        DeleteRemarkCommand deleteFirstPersonSecondRemark =
                new DeleteRemarkCommand(INDEX_FIRST_PERSON, INDEX_SECOND_ATTRIBUTE);

        // same object -> returns true
        assertEquals(deleteFirstPersonFirstRemark, deleteFirstPersonFirstRemark);

        // same values -> returns true
        DeleteRemarkCommand deleteFirstPersonFirstRemarkCopy =
                new DeleteRemarkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);
        assertEquals(deleteFirstPersonFirstRemark, deleteFirstPersonFirstRemarkCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstPersonFirstRemark);

        // null -> returns false
        assertNotEquals(null, deleteFirstPersonFirstRemark);

        // different person index -> returns false
        assertNotEquals(deleteFirstPersonFirstRemark, deleteSecondPersonFirstRemark);

        // different remark index -> returns false
        assertNotEquals(deleteFirstPersonFirstRemark, deleteFirstPersonSecondRemark);
    }
}
