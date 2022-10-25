package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.logic.commands.EditConditionCommand.EDIT_CONDITION_COMMAND_TYPE;
import static seedu.uninurse.logic.commands.EditConditionCommand.MESSAGE_EDIT_CONDITION_SUCCESS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalConditions.CONDITION_DIABETES;
import static seedu.uninurse.testutil.TypicalConditions.CONDITION_OSTEOPOROSIS;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;

import org.junit.jupiter.api.Test;

import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code EditConditionCommand}.
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
        // Use second patient as the first patient in typical persons does not have a condition
        Patient patientToEdit = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        Condition initialCondition = patientToEdit.getConditions().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());
        ConditionList editedConditionList = patientToEdit.getConditions()
                .edit(INDEX_FIRST_ATTRIBUTE.getZeroBased(), CONDITION_DIABETES);

        // convert condition list to array of strings
        String[] editedConditionListStrings = editedConditionList.getInternalList().stream()
                .map(Condition::toString).toArray(String[]::new);

        Patient editedPatient = new PersonBuilder(patientToEdit).withConditions(editedConditionListStrings).build();

        EditConditionCommand editConditionCommand =
                new EditConditionCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, CONDITION_DIABETES);

        String expectedMessage = String.format(MESSAGE_EDIT_CONDITION_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName(), initialCondition, CONDITION_DIABETES);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToEdit, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(editConditionCommand, model, expectedMessage, EDIT_CONDITION_COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidPatientIndexUnfilteredList_throwsCommandException() {

    }

    @Test
    public void execute_invalidConditionIndexUnfilteredList_throwsCommandException() {

    }

    @Test
    public void execute_invalidConditionUnfilteredList_throwsCommandException() {

    }

    @Test
    public void execute_duplicateConditionUnfilteredList_throwsCommandException() {

    }


    @Test
    public void execute_validArgsFilteredList_success() {
        // Use second patient in typical persons since there is a condition to edit
        showPersonAtIndex(model, INDEX_SECOND_PERSON);
        Patient patientToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Condition initialCondition = patientToEdit.getConditions().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());
        ConditionList editedConditionList = patientToEdit.getConditions()
                .edit(INDEX_FIRST_ATTRIBUTE.getZeroBased(), CONDITION_DIABETES);

        // convert condition list to array of strings
        String[] editedConditionListStrings = editedConditionList.getInternalList().stream()
                .map(Condition::toString).toArray(String[]::new);

        Patient editedPatient = new PersonBuilder(patientToEdit).withConditions(editedConditionListStrings).build();

        EditConditionCommand editConditionCommand =
                new EditConditionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, CONDITION_DIABETES);

        String expectedMessage = String.format(MESSAGE_EDIT_CONDITION_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName(), initialCondition, CONDITION_DIABETES);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);
        expectedModel.setPerson(patientToEdit, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(editConditionCommand, model, expectedMessage, EDIT_CONDITION_COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidPatientIndexFilteredList_throwsCommandException() {

    }

    @Test
    public void execute_invalidConditionIndexFilteredList_throwsCommandException() {

    }

    @Test
    public void execute_invalidConditionFilteredList_throwsCommandException() {

    }

    @Test
    public void execute_duplicateConditionFilteredList_throwsCommandException() {

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
