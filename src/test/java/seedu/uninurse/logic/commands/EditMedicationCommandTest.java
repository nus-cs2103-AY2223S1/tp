package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.logic.commands.EditMedicationCommand.COMMAND_TYPE;
import static seedu.uninurse.logic.commands.EditMedicationCommand.MESSAGE_SUCCESS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.EditMedicationCommand.EditMedicationDescriptor;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code EditMedicationCommand}.
 */
class EditMedicationCommandTest {
    private static final String MEDICATION_TYPE_STUB = "Some medication";
    private static final String MEDICATION_DOSAGE_STUB = "Some dosage";
    private static final EditMedicationDescriptor DESC_MEDICATION_STUB =
            new EditMedicationDescriptor(Optional.of(MEDICATION_TYPE_STUB), Optional.of(MEDICATION_DOSAGE_STUB));
    private static final EditMedicationDescriptor DESC_MEDICATION_STUB_OTHER =
            new EditMedicationDescriptor(Optional.empty(), Optional.empty());

    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditMedicationCommand(null, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_STUB));
    }

    @Test
    public void constructor_nullMedicationIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditMedicationCommand(INDEX_FIRST_PERSON, null, DESC_MEDICATION_STUB));
    }

    @Test
    public void constructor_nullEditMedicationDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        EditMedicationCommand editMedicationCommand =
                new EditMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_STUB);
        assertThrows(NullPointerException.class, () -> editMedicationCommand.execute(null));
    }

    @Test
    public void execute_validIndicesUnfilteredList_success() {
        // Use second patient as the first patient in typical persons does not have a medication
        Patient patientToEdit = model.getPatient(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()));

        Medication initialMedication = patientToEdit.getMedications().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());
        Medication editedMedication = new Medication(MEDICATION_TYPE_STUB, MEDICATION_DOSAGE_STUB);

        Patient editedPatient = new PersonBuilder(patientToEdit).withMedications(patientToEdit.getMedications()
                .edit(INDEX_FIRST_ATTRIBUTE.getZeroBased(), editedMedication)
                .getInternalList().toArray(Medication[]::new)).build();

        EditMedicationCommand editMedicationCommand =
                new EditMedicationCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_STUB);

        String expectedMessage = String.format(MESSAGE_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName().toString(), initialMedication, editedMedication);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToEdit, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(editMedicationCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditMedicationCommand editMedicationCommand =
                new EditMedicationCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_STUB);

        assertCommandFailure(editMedicationCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_validIndicesFilteredList_success() {
        // use second person in TypicalPersons since there is a medication to edit
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Patient patientToEdit = model.getPatient(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));

        Medication initialMedication = patientToEdit.getMedications().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());
        Medication editedMedication = new Medication(MEDICATION_TYPE_STUB, MEDICATION_DOSAGE_STUB);

        Patient editedPatient = new PersonBuilder(patientToEdit).withMedications(patientToEdit.getMedications()
                .edit(INDEX_FIRST_ATTRIBUTE.getZeroBased(), editedMedication)
                .getInternalList().toArray(Medication[]::new)).build();

        EditMedicationCommand editMedicationCommand =
                new EditMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_STUB);

        String expectedMessage = String.format(MESSAGE_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName().toString(), initialMedication, editedMedication);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);
        expectedModel.setPerson(patientToEdit, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(editMedicationCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        EditMedicationCommand editMedicationCommand =
                new EditMedicationCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_STUB);

        assertCommandFailure(editMedicationCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidMedicationIndex_throwsCommandException() {
        Patient patient = model.getPatient(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));

        Index outOfBoundIndex = Index.fromOneBased(patient.getMedications().size() + 1);
        EditMedicationCommand editMedicationCommand =
                new EditMedicationCommand(INDEX_FIRST_PERSON, outOfBoundIndex, DESC_MEDICATION_STUB);

        assertCommandFailure(editMedicationCommand, model, Messages.MESSAGE_INVALID_MEDICATION_INDEX);
    }

    @Test
    public void execute_duplicateMedication_throwsCommandException() {
        Patient patient = model.getPatient(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()));

        Medication initialMedication = patient.getMedications().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        EditMedicationDescriptor duplicateMedicationDescriptor = new EditMedicationDescriptor(
                Optional.of(initialMedication.getType()), Optional.of(initialMedication.getDosage()));

        EditMedicationCommand editMedicationCommand =
                new EditMedicationCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, duplicateMedicationDescriptor);

        assertCommandFailure(editMedicationCommand, model,
                String.format(Messages.MESSAGE_DUPLICATE_MEDICATION, patient.getName().toString()));
    }

    @Test
    public void equals() {
        EditMedicationCommand editFirstPersonFirstMedicationDescriptorStub =
                new EditMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_STUB);
        EditMedicationCommand editSecondPersonFirstMedicationDescriptorStub =
                new EditMedicationCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_STUB);
        EditMedicationCommand editFirstPersonSecondMedicationDescriptorStub =
                new EditMedicationCommand(INDEX_FIRST_PERSON, INDEX_SECOND_ATTRIBUTE, DESC_MEDICATION_STUB);
        EditMedicationCommand editFirstPersonFirstMedicationDescriptorStubOther =
                new EditMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_STUB_OTHER);

        // same object -> returns true
        assertEquals(editFirstPersonFirstMedicationDescriptorStub, editFirstPersonFirstMedicationDescriptorStub);

        // same values -> returns true
        EditMedicationCommand editFirstPersonFirstMedicationDescriptorStubCopy =
                new EditMedicationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, DESC_MEDICATION_STUB);
        assertEquals(editFirstPersonFirstMedicationDescriptorStub, editFirstPersonFirstMedicationDescriptorStubCopy);

        // different types -> returns false
        assertNotEquals(1, editFirstPersonFirstMedicationDescriptorStub);

        // null -> returns false
        assertNotEquals(null, editFirstPersonFirstMedicationDescriptorStub);

        // different person index -> returns false
        assertNotEquals(editFirstPersonFirstMedicationDescriptorStub, editSecondPersonFirstMedicationDescriptorStub);

        // different medication index -> returns false
        assertNotEquals(editFirstPersonFirstMedicationDescriptorStub, editFirstPersonSecondMedicationDescriptorStub);

        // different descriptor -> returns false
        assertNotEquals(editFirstPersonFirstMedicationDescriptorStub,
                editFirstPersonFirstMedicationDescriptorStubOther);
    }
}
