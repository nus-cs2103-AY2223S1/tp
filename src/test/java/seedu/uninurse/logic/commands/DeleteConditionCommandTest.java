package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.logic.commands.DeleteConditionCommand.DELETE_CONDITION_COMMAND_TYPE;
import static seedu.uninurse.logic.commands.DeleteConditionCommand.MESSAGE_DELETE_CONDITION_SUCCESS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteConditionCommand.
 */
public class DeleteConditionCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullConditionIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteConditionCommand(INDEX_SECOND_PERSON, null));
    }

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteConditionCommand(null, INDEX_FIRST_ATTRIBUTE));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        DeleteConditionCommand deleteConditionCommand =
                new DeleteConditionCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE);
        assertThrows(NullPointerException.class, () -> deleteConditionCommand.execute(null));
    }

    @Test
    void execute_validIndicesUnfilteredList_success() {
        // use third person in TypicalPersons since there is one condition to delete
        Patient patientToDeleteCondition = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToDeleteCondition).withConditions().build();
        Condition deletedCondition = patientToDeleteCondition.getConditions().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        DeleteConditionCommand deleteConditionCommand =
                new DeleteConditionCommand(INDEX_THIRD_PERSON, INDEX_FIRST_ATTRIBUTE);

        String expectedMessage = String.format(MESSAGE_DELETE_CONDITION_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName(), deletedCondition);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToDeleteCondition, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(deleteConditionCommand, model, expectedMessage, DELETE_CONDITION_COMMAND_TYPE,
                expectedModel);
    }

    @Test
    void execute_invalidPatientIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundPatientIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteConditionCommand deleteConditionCommand =
                new DeleteConditionCommand(outOfBoundPatientIndex, INDEX_FIRST_ATTRIBUTE);

        assertCommandFailure(deleteConditionCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_validIndicesFilteredList_success() {
        // use third person in TypicalPersons since there is one condition to delete

        Patient patientToDeleteCondition = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased()))
                .withConditions().build();
        Condition deletedCondition = patientToDeleteCondition.getConditions().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        DeleteConditionCommand deleteConditionCommand =
                new DeleteConditionCommand(INDEX_THIRD_PERSON, INDEX_FIRST_ATTRIBUTE);

        String expectedMessage = String.format(MESSAGE_DELETE_CONDITION_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName().toString(), deletedCondition);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToDeleteCondition, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(deleteConditionCommand, model, expectedMessage, DELETE_CONDITION_COMMAND_TYPE,
                expectedModel);
    }

    @Test
    void execute_invalidPatientIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        DeleteConditionCommand deleteConditionCommand =
                new DeleteConditionCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE);

        assertCommandFailure(deleteConditionCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_invalidConditionIndex_throwsCommandException() {
        Patient patient = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Index outOfBoundConditionIndex = Index.fromOneBased(patient.getConditions().size() + 1);
        DeleteConditionCommand deleteConditionCommand =
                new DeleteConditionCommand(INDEX_FIRST_PERSON, outOfBoundConditionIndex);

        assertCommandFailure(deleteConditionCommand, model, Messages.MESSAGE_INVALID_CONDITION_INDEX);
    }

    @Test
    public void equals() {
        DeleteConditionCommand deleteFirstPersonFirstCondition =
                new DeleteConditionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);
        DeleteConditionCommand deleteSecondPersonFirstCondition =
                new DeleteConditionCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE);
        DeleteConditionCommand deleteFirstPersonSecondCondition =
                new DeleteConditionCommand(INDEX_FIRST_PERSON, INDEX_SECOND_ATTRIBUTE);

        // same object -> returns true
        assertEquals(deleteFirstPersonFirstCondition, deleteFirstPersonFirstCondition);

        // same values -> returns true
        DeleteConditionCommand deleteFirstPersonFirstConditionCopy =
                new DeleteConditionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);
        assertEquals(deleteFirstPersonFirstCondition, deleteFirstPersonFirstConditionCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstPersonFirstCondition);

        // null -> returns false
        assertNotEquals(null, deleteFirstPersonFirstCondition);

        // different person index -> returns false
        assertNotEquals(deleteFirstPersonFirstCondition, deleteSecondPersonFirstCondition);

        // different condition index -> returns false
        assertNotEquals(deleteFirstPersonFirstCondition, deleteFirstPersonSecondCondition);
    }
}
