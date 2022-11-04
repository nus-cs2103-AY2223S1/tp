package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.logic.commands.EditConditionCommand.EDIT_CONDITION_COMMAND_TYPE;
import static seedu.uninurse.logic.commands.EditConditionCommand.MESSAGE_EDIT_CONDITION_SUCCESS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalConditions.CONDITION_DIABETES;
import static seedu.uninurse.testutil.TypicalConditions.CONDITION_OSTEOPOROSIS;
import static seedu.uninurse.testutil.TypicalConditions.TYPICAL_CONDITION_DIABETES;
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
 * Contains integration tests (interaction with the Model) and unit tests for EditConditionCommand.
 */
public class EditConditionCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditConditionCommand(null, INDEX_FIRST_ATTRIBUTE, CONDITION_DIABETES));
    }

    @Test
    public void constructor_nullConditionIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditConditionCommand(INDEX_FIRST_PERSON, null, CONDITION_DIABETES));
    }

    @Test
    public void constructor_nullEditCondition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditConditionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        EditConditionCommand editConditionCommand =
                new EditConditionCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, CONDITION_DIABETES);
        assertThrows(NullPointerException.class, () -> editConditionCommand.execute(null));
    }

    @Test
    public void execute_validArgsUnfilteredList_success() {
        // Use third patient in typical persons because it only has one condition, so we only have to
        // replace it rather than retrieve the other unedited conditions
        Patient patientToEdit = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());

        Condition initialCondition = patientToEdit.getConditions().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        Patient editedPatient = new PersonBuilder(patientToEdit).withConditions(TYPICAL_CONDITION_DIABETES).build();

        EditConditionCommand editConditionCommand =
                new EditConditionCommand(INDEX_THIRD_PERSON, INDEX_FIRST_ATTRIBUTE, CONDITION_DIABETES);

        String expectedMessage = String.format(MESSAGE_EDIT_CONDITION_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName(), initialCondition, CONDITION_DIABETES);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToEdit, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(editConditionCommand, model, expectedMessage, EDIT_CONDITION_COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidPatientIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundPatientIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditConditionCommand editConditionCommand =
                new EditConditionCommand(outOfBoundPatientIndex, INDEX_FIRST_ATTRIBUTE, CONDITION_DIABETES);

        assertCommandFailure(editConditionCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validArgsFilteredList_success() {
        showPersonAtIndex(model, INDEX_THIRD_PERSON);
        Patient patientToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Condition initialCondition = patientToEdit.getConditions().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        Patient editedPatient = new PersonBuilder(patientToEdit).withConditions(TYPICAL_CONDITION_DIABETES).build();

        EditConditionCommand editConditionCommand =
                new EditConditionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, CONDITION_DIABETES);

        String expectedMessage = String.format(MESSAGE_EDIT_CONDITION_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName(), initialCondition, CONDITION_DIABETES);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());

        showPersonAtIndex(expectedModel, INDEX_THIRD_PERSON);
        expectedModel.setPerson(patientToEdit, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(editConditionCommand, model, expectedMessage, EDIT_CONDITION_COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidPatientIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        EditConditionCommand editConditionCommand =
                new EditConditionCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE, CONDITION_DIABETES);

        assertCommandFailure(editConditionCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateCondition_throwsCommandException() {
        Patient patientToEdit = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Condition condition = new Condition("H1N1");
        EditConditionCommand editConditionCommand =
                new EditConditionCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, condition);
        assertCommandFailure(editConditionCommand, model,
                String.format(Messages.MESSAGE_DUPLICATE_CONDITION, patientToEdit.getName()));
    }

    @Test
    public void execute_invalidConditionIndex_throwsCommandException() {
        Patient patient = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Index outOfBoundConditionIndex = Index.fromOneBased(patient.getConditions().size() + 1);
        EditConditionCommand editConditionCommand =
                new EditConditionCommand(INDEX_FIRST_PERSON, outOfBoundConditionIndex, CONDITION_DIABETES);

        assertCommandFailure(editConditionCommand, model, Messages.MESSAGE_INVALID_CONDITION_INDEX);
    }

    @Test
    public void equals() {
        EditConditionCommand editFirstPersonFirstConditionDiabetes =
                new EditConditionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, CONDITION_DIABETES);
        EditConditionCommand editSecondPersonFirstConditionDiabetes =
                new EditConditionCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, CONDITION_DIABETES);
        EditConditionCommand editFirstPersonSecondConditionDiabetes =
                new EditConditionCommand(INDEX_FIRST_PERSON, INDEX_SECOND_ATTRIBUTE, CONDITION_DIABETES);
        EditConditionCommand editFirstPersonFirstConditionOsteoporosis =
                new EditConditionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, CONDITION_OSTEOPOROSIS);

        // same object -> returns true
        assertEquals(editFirstPersonFirstConditionDiabetes, editFirstPersonFirstConditionDiabetes);

        // same values -> returns true
        EditConditionCommand editFirstPersonFirstConditionDiabetesCopy =
                new EditConditionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, CONDITION_DIABETES);
        assertEquals(editFirstPersonFirstConditionDiabetes, editFirstPersonFirstConditionDiabetesCopy);

        // different types -> returns false
        assertNotEquals(1, editFirstPersonFirstConditionDiabetes);

        // null -> returns false
        assertNotEquals(null, editFirstPersonFirstConditionDiabetes);

        // different person index -> returns false
        assertNotEquals(editFirstPersonFirstConditionDiabetes, editSecondPersonFirstConditionDiabetes);

        // different condition index -> returns false
        assertNotEquals(editFirstPersonFirstConditionDiabetes, editFirstPersonSecondConditionDiabetes);

        // different condition -> returns false
        assertNotEquals(editFirstPersonFirstConditionDiabetes, editFirstPersonFirstConditionOsteoporosis);

    }
}
