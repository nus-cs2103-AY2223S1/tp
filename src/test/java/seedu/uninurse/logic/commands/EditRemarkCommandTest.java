package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.logic.commands.EditRemarkCommand.COMMAND_TYPE;
import static seedu.uninurse.logic.commands.EditRemarkCommand.MESSAGE_SUCCESS;
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
 * Contains integration tests (interaction with the Model) and unit tests for {@code EditRemarkCommand}.
 */
class EditRemarkCommandTest {
    private static final String REMARK_STUB = "Some remark";

    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditRemarkCommand(null, INDEX_FIRST_ATTRIBUTE, new Remark(REMARK_STUB)));
    }

    @Test
    public void constructor_nullRemarkIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditRemarkCommand(INDEX_FIRST_PERSON, null, new Remark(REMARK_STUB)));
    }

    @Test
    public void constructor_nullRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditRemarkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        EditRemarkCommand editRemarkCommand =
                new EditRemarkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, new Remark(REMARK_STUB));
        assertThrows(NullPointerException.class, () -> editRemarkCommand.execute(null));
    }

    @Test
    public void execute_validIndicesUnfilteredList_success() {
        // Use second patient as the first patient in typical persons does not have a remark
        Patient patientToEdit = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        Remark initialRemark = patientToEdit.getRemarks().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());
        Remark editedRemark = new Remark(REMARK_STUB);

        Patient editedPatient = new PersonBuilder(patientToEdit).withRemarks(REMARK_STUB).build();

        EditRemarkCommand editRemarkCommand =
                new EditRemarkCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, editedRemark);

        String expectedMessage = String.format(MESSAGE_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName().toString(), initialRemark, editedRemark);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToEdit, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(editRemarkCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditRemarkCommand editRemarkCommand =
                new EditRemarkCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE, new Remark(REMARK_STUB));

        assertCommandFailure(editRemarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_validIndicesFilteredList_success() {
        // use second person in TypicalPersons since there is a remark to edit
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Patient patientToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Remark initialRemark = patientToEdit.getRemarks().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());
        Remark editedRemark = new Remark(REMARK_STUB);

        Patient editedPatient = new PersonBuilder(patientToEdit).withRemarks(REMARK_STUB).build();

        EditRemarkCommand editRemarkCommand =
                new EditRemarkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, editedRemark);

        String expectedMessage = String.format(MESSAGE_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName().toString(), initialRemark, editedRemark);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);
        expectedModel.setPerson(patientToEdit, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(editRemarkCommand, model, expectedMessage, COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        EditRemarkCommand editRemarkCommand =
                new EditRemarkCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE, new Remark(REMARK_STUB));

        assertCommandFailure(editRemarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidRemarkIndex_throwsCommandException() {
        Patient patient = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Index outOfBoundIndex = Index.fromOneBased(patient.getRemarks().size() + 1);
        EditRemarkCommand editRemarkCommand =
                new EditRemarkCommand(INDEX_FIRST_PERSON, outOfBoundIndex, new Remark(REMARK_STUB));

        assertCommandFailure(editRemarkCommand, model, Messages.MESSAGE_INVALID_REMARK_INDEX);
    }

    @Test
    public void execute_duplicateRemark_throwsCommandException() {
        Patient patient = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        Remark initialRemark = patient.getRemarks().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        Remark duplicateRemark = new Remark(initialRemark.getValue());

        EditRemarkCommand editRemarkCommand =
                new EditRemarkCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, duplicateRemark);

        assertCommandFailure(editRemarkCommand, model,
                String.format(Messages.MESSAGE_DUPLICATE_REMARK, patient.getName().toString()));
    }

    @Test
    public void equals() {
        EditRemarkCommand editFirstPersonFirstRemark =
                new EditRemarkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, new Remark(REMARK_STUB));
        EditRemarkCommand editSecondPersonFirstRemark =
                new EditRemarkCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, new Remark(REMARK_STUB));
        EditRemarkCommand editFirstPersonSecondRemark =
                new EditRemarkCommand(INDEX_FIRST_PERSON, INDEX_SECOND_ATTRIBUTE, new Remark(REMARK_STUB));

        // same object -> returns true
        assertEquals(editFirstPersonFirstRemark, editFirstPersonFirstRemark);

        // same values -> returns true
        EditRemarkCommand editFirstPersonFirstRemarkCopy =
                new EditRemarkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, new Remark(REMARK_STUB));
        assertEquals(editFirstPersonFirstRemark, editFirstPersonFirstRemarkCopy);

        // different types -> returns false
        assertNotEquals(1, editFirstPersonFirstRemark);

        // null -> returns false
        assertNotEquals(null, editFirstPersonFirstRemark);

        // different person index -> returns false
        assertNotEquals(editFirstPersonFirstRemark, editSecondPersonFirstRemark);

        // different remark index -> returns false
        assertNotEquals(editFirstPersonFirstRemark, editFirstPersonSecondRemark);
    }
}
