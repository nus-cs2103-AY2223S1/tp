package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.AddMedicationCommand.COMMAND_TYPE;
import static seedu.uninurse.logic.commands.AddMedicationCommand.MESSAGE_SUCCESS;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalMedications.MEDICATION_AMOXICILLIN;
import static seedu.uninurse.testutil.TypicalMedications.MEDICATION_AMPICILLIN;
import static seedu.uninurse.testutil.TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN;
import static seedu.uninurse.testutil.TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code AddMedicationCommand}.
 */
public class AddMedicationCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullMedication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMedicationCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMedicationCommand(null, MEDICATION_AMOXICILLIN));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AddMedicationCommand addMedicationCommand =
                new AddMedicationCommand(INDEX_FIRST_PERSON, MEDICATION_AMOXICILLIN);
        assertThrows(NullPointerException.class, () -> addMedicationCommand.execute(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Medication medicationToAdd = new Medication(TYPICAL_MEDICATION_AMOXICILLIN, TYPICAL_DOSAGE_AMOXICILLIN);
        Patient patientToAddMedication = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToAddMedication)
                .withMedications(medicationToAdd).build();

        AddMedicationCommand addMedicationCommand = new AddMedicationCommand(INDEX_FIRST_PERSON, medicationToAdd);

        String expectedMessage = String.format(MESSAGE_SUCCESS, editedPatient.getName(), medicationToAdd);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToAddMedication, editedPatient);

        assertCommandSuccess(addMedicationCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddMedicationCommand addMedicationCommand = new AddMedicationCommand(outOfBoundIndex, MEDICATION_AMOXICILLIN);

        assertCommandFailure(addMedicationCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Medication medicationToAdd = new Medication(TYPICAL_MEDICATION_AMOXICILLIN, TYPICAL_DOSAGE_AMOXICILLIN);
        Patient patientToAddMedication = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToAddMedication)
                .withMedications(medicationToAdd).build();

        AddMedicationCommand addMedicationCommand = new AddMedicationCommand(INDEX_FIRST_PERSON, medicationToAdd);

        String expectedMessage = String.format(MESSAGE_SUCCESS, editedPatient.getName(), medicationToAdd);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPerson(patientToAddMedication, editedPatient);

        assertCommandSuccess(addMedicationCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        AddMedicationCommand addMedicationCommand = new AddMedicationCommand(outOfBoundIndex, MEDICATION_AMOXICILLIN);

        assertCommandFailure(addMedicationCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateMedication_throwsCommandException() {
        Medication medicationToAdd = new Medication(TYPICAL_MEDICATION_AMOXICILLIN, TYPICAL_DOSAGE_AMOXICILLIN);
        Patient patientToAddMedication = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToAddMedication)
                .withMedications(medicationToAdd).build();
        model.setPerson(patientToAddMedication, editedPatient);

        AddMedicationCommand addMedicationCommand = new AddMedicationCommand(INDEX_FIRST_PERSON, medicationToAdd);

        assertCommandFailure(addMedicationCommand, model,
                String.format(Messages.MESSAGE_DUPLICATE_MEDICATION, editedPatient.getName().toString()));
    }

    @Test
    public void equals() {
        AddMedicationCommand addMedicationFirstAmoxicillin =
                new AddMedicationCommand(INDEX_FIRST_PERSON, MEDICATION_AMOXICILLIN);
        AddMedicationCommand addMedicationSecondAmoxicillin =
                new AddMedicationCommand(INDEX_SECOND_PERSON, MEDICATION_AMOXICILLIN);
        AddMedicationCommand addMedicationFirstAmpicillin =
                new AddMedicationCommand(INDEX_FIRST_PERSON, MEDICATION_AMPICILLIN);

        // same object -> returns true
        assertEquals(addMedicationFirstAmoxicillin, addMedicationFirstAmoxicillin);

        // same values -> returns true
        AddMedicationCommand addMedicationFirstAmoxicillinCopy =
                new AddMedicationCommand(INDEX_FIRST_PERSON, MEDICATION_AMOXICILLIN);
        assertEquals(addMedicationFirstAmoxicillin, addMedicationFirstAmoxicillinCopy);

        // different types -> returns false
        assertNotEquals(1, addMedicationFirstAmoxicillin);

        // null -> returns false
        assertNotEquals(null, addMedicationFirstAmoxicillin);

        // different medication -> returns false
        assertNotEquals(addMedicationFirstAmoxicillin, addMedicationFirstAmpicillin);

        // different patient index -> returns false
        assertNotEquals(addMedicationFirstAmoxicillin, addMedicationSecondAmoxicillin);
    }
}
