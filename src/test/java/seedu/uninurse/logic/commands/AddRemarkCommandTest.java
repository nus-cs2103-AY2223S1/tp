package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.AddRemarkCommand.COMMAND_TYPE;
import static seedu.uninurse.logic.commands.AddRemarkCommand.MESSAGE_SUCCESS;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;
import static seedu.uninurse.testutil.TypicalRemarks.REMARK_MEDICAL_ALLERGY;
import static seedu.uninurse.testutil.TypicalRemarks.REMARK_WHEELCHAIR;
import static seedu.uninurse.testutil.TypicalRemarks.TYPICAL_REMARK_MEDICAL_ALLERGY;

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
 * Contains integration tests (interaction with the Model) and unit tests for {@code AddRemarkCommand}.
 */
public class AddRemarkCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRemarkCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRemarkCommand(null, REMARK_MEDICAL_ALLERGY));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AddRemarkCommand addRemarkCommand = new AddRemarkCommand(INDEX_FIRST_PERSON, REMARK_MEDICAL_ALLERGY);
        assertThrows(NullPointerException.class, () -> addRemarkCommand.execute(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Remark remarkToAdd = new Remark(TYPICAL_REMARK_MEDICAL_ALLERGY);
        Patient patientToAddRemark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToAddRemark)
                .withRemarks(TYPICAL_REMARK_MEDICAL_ALLERGY).build();

        AddRemarkCommand addRemarkCommand = new AddRemarkCommand(INDEX_FIRST_PERSON, remarkToAdd);

        String expectedMessage = String.format(MESSAGE_SUCCESS, editedPatient.getName(), remarkToAdd);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToAddRemark, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(addRemarkCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddRemarkCommand addRemarkCommand = new AddRemarkCommand(outOfBoundIndex, REMARK_MEDICAL_ALLERGY);

        assertCommandFailure(addRemarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Remark remarkToAdd = new Remark(TYPICAL_REMARK_MEDICAL_ALLERGY);
        Patient patientToAddRemark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withRemarks(TYPICAL_REMARK_MEDICAL_ALLERGY).build();

        AddRemarkCommand addRemarkCommand = new AddRemarkCommand(INDEX_FIRST_PERSON, remarkToAdd);

        String expectedMessage = String.format(MESSAGE_SUCCESS, editedPatient.getName(), remarkToAdd);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPerson(patientToAddRemark, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(addRemarkCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        AddRemarkCommand addRemarkCommand = new AddRemarkCommand(outOfBoundIndex, REMARK_MEDICAL_ALLERGY);

        assertCommandFailure(addRemarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDuplicateRemark_throwsCommandException() {
        Patient patientToAddRemark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToAddRemark)
                .withRemarks(TYPICAL_REMARK_MEDICAL_ALLERGY).build();

        model.setPerson(patientToAddRemark, editedPatient);

        AddRemarkCommand addRemarkCommand = new AddRemarkCommand(INDEX_FIRST_PERSON, REMARK_MEDICAL_ALLERGY);

        assertCommandFailure(addRemarkCommand, model,
                String.format(Messages.MESSAGE_DUPLICATE_REMARK, patientToAddRemark.getName()));
    }

    @Test
    public void equals() {
        AddRemarkCommand addRemarkFirstMedicalAllergy =
                new AddRemarkCommand(INDEX_FIRST_PERSON, REMARK_MEDICAL_ALLERGY);
        AddRemarkCommand addRemarkSecondMedicalAllergy =
                new AddRemarkCommand(INDEX_SECOND_PERSON, REMARK_MEDICAL_ALLERGY);
        AddRemarkCommand addRemarkFirstWheelchair =
                new AddRemarkCommand(INDEX_FIRST_PERSON, REMARK_WHEELCHAIR);

        // same object -> returns true
        assertEquals(addRemarkFirstMedicalAllergy, addRemarkFirstMedicalAllergy);

        // same values -> returns true
        AddRemarkCommand addRemarkFirstMedicalAllergyCopy =
                new AddRemarkCommand(INDEX_FIRST_PERSON, REMARK_MEDICAL_ALLERGY);
        assertEquals(addRemarkFirstMedicalAllergy, addRemarkFirstMedicalAllergyCopy);

        // different types -> returns false
        assertNotEquals(1, addRemarkFirstMedicalAllergy);

        // null -> returns false
        assertNotEquals(null, addRemarkFirstMedicalAllergy);

        // different remark -> returns false
        assertNotEquals(addRemarkFirstMedicalAllergy, addRemarkFirstWheelchair);

        // different patient index -> returns false
        assertNotEquals(addRemarkFirstMedicalAllergy, addRemarkSecondMedicalAllergy);
    }
}
