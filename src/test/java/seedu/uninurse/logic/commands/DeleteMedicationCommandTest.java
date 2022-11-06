package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_MEDICATION_INDEX;
import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.logic.commands.DeleteMedicationCommand.COMMAND_TYPE;
import static seedu.uninurse.logic.commands.DeleteMedicationCommand.MESSAGE_SUCCESS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteMedicationCommand}.
 */
public class DeleteMedicationCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullMedicationIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteMedicationCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteMedicationCommand(null, INDEX_FIRST_ATTRIBUTE));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        DeleteMedicationCommand deleteMedicationCommand =
                new DeleteMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);
        assertThrows(NullPointerException.class, () -> deleteMedicationCommand.execute(null));
    }

    @Test
    void execute_validIndicesUnfilteredList_success() {
        // use second person in TypicalPersons since there is one medication to delete
        Patient patientToDeleteMedication = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToDeleteMedication).withMedications().build();
        Medication deletedMedication = patientToDeleteMedication.getMedications().get(
                INDEX_FIRST_ATTRIBUTE.getZeroBased());

        DeleteMedicationCommand deleteMedicationCommand =
                new DeleteMedicationCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE);

        String expectedMessage = String.format(MESSAGE_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName(), deletedMedication);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToDeleteMedication, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(deleteMedicationCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    void execute_invalidPatientIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundPatientIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteMedicationCommand deleteMedicationCommand =
                new DeleteMedicationCommand(outOfBoundPatientIndex, INDEX_FIRST_ATTRIBUTE);

        assertCommandFailure(deleteMedicationCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_validIndicesFilteredList_success() {
        // use second person in TypicalPersons since there is one medication to delete
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Patient patientToDeleteMedication = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withMedications().build();
        Medication deletedMedication = patientToDeleteMedication.getMedications().get(
                INDEX_FIRST_ATTRIBUTE.getZeroBased());

        DeleteMedicationCommand deleteMedicationCommand =
                new DeleteMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);

        String expectedMessage = String.format(MESSAGE_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName(), deletedMedication);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);
        expectedModel.setPerson(patientToDeleteMedication, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(deleteMedicationCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    void execute_invalidPatientIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        DeleteMedicationCommand deleteMedicationCommand =
                new DeleteMedicationCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE);

        assertCommandFailure(deleteMedicationCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_invalidMedicationIndex_throwsCommandException() {
        Patient patient = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Index outOfBoundMedicationIndex = Index.fromOneBased(patient.getMedications().size() + 1);
        DeleteMedicationCommand deleteMedicationCommand =
                new DeleteMedicationCommand(INDEX_FIRST_PERSON, outOfBoundMedicationIndex);

        assertCommandFailure(deleteMedicationCommand, model, MESSAGE_INVALID_MEDICATION_INDEX);
    }

    @Test
    public void equals() {
        DeleteMedicationCommand deleteFirstPersonFirstMedication =
                new DeleteMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);
        DeleteMedicationCommand deleteSecondPersonFirstMedication =
                new DeleteMedicationCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE);
        DeleteMedicationCommand deleteFirstPersonSecondMedication =
                new DeleteMedicationCommand(INDEX_FIRST_PERSON, INDEX_SECOND_ATTRIBUTE);

        // same object -> returns true
        assertEquals(deleteFirstPersonFirstMedication, deleteFirstPersonFirstMedication);

        // same values -> returns true
        DeleteMedicationCommand deleteFirstPersonFirstMedicationCopy =
                new DeleteMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);
        assertEquals(deleteFirstPersonFirstMedication, deleteFirstPersonFirstMedicationCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstPersonFirstMedication);

        // null -> returns false
        assertNotEquals(null, deleteFirstPersonFirstMedication);

        // different person index -> returns false
        assertNotEquals(deleteFirstPersonFirstMedication, deleteSecondPersonFirstMedication);

        // different medication index -> returns false
        assertNotEquals(deleteFirstPersonFirstMedication, deleteFirstPersonSecondMedication);
    }
}
