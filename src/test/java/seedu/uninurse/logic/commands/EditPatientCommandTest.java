package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalPatients.getTypicalUninurseBook;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.testutil.EditPatientDescriptorBuilder;
import seedu.uninurse.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPatientCommand.
 */
public class EditPatientCommandTest {
    private Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Patient editedPatient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(editedPatient).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPatient(model.getPatient(model.getFilteredPersonList().get(0)), editedPatient);

        assertCommandSuccess(editPatientCommand, model, expectedMessage,
                EditPatientCommand.COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPatient = Index.fromOneBased(model.getFilteredPersonList().size());
        Patient lastPatient = model.getPatient(model.getFilteredPersonList().get(indexLastPatient.getZeroBased()));

        PatientBuilder patientInList = new PatientBuilder(lastPatient);
        Patient editedPatient = patientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(indexLastPatient, descriptor);

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPatient(lastPatient, editedPatient);

        assertCommandSuccess(editPatientCommand, model, expectedMessage,
                EditPatientCommand.COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST_PERSON, new EditPatientDescriptor());
        Patient editedPatient = model.getPatient(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());

        assertCommandSuccess(editPatientCommand, model, expectedMessage,
                EditPatientCommand.COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Patient patientInFilteredList =
                model.getPatient(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        Patient editedPatient = new PatientBuilder(patientInFilteredList).withName(VALID_NAME_BOB).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST_PERSON,
                new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPatient(model.getPatient(model.getFilteredPersonList().get(0)), editedPatient);

        assertCommandSuccess(editPatientCommand, model, expectedMessage,
                EditPatientCommand.COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_duplicatePatientUnfilteredList_failure() {
        Patient firstPatient = model.getPatient(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(firstPatient).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editPatientCommand, model, Messages.MESSAGE_DUPLICATE_PATIENT);
    }

    @Test
    public void execute_duplicatePatientFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit patient in filtered list into a duplicate in uninurse book
        Patient patientInList = model.getPatient(
                model.getUninurseBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased()));
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST_PERSON,
                new EditPatientDescriptorBuilder(patientInList).build());

        assertCommandFailure(editPatientCommand, model, Messages.MESSAGE_DUPLICATE_PATIENT);
    }

    @Test
    public void execute_invalidPatientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editPatientCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of uninurse book
     */
    @Test
    public void execute_invalidPatientIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPatientList().size());

        EditPatientCommand editPatientCommand = new EditPatientCommand(outOfBoundIndex,
                new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editPatientCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditPatientCommand standardCommand = new EditPatientCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPatientDescriptor copyDescriptor = new EditPatientDescriptor(DESC_AMY);
        EditPatientCommand commandWithSameValues = new EditPatientCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(5));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPatientCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPatientCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }
}
