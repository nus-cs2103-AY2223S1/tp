package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.AddConditionCommand.ADD_CONDITION_COMMAND_TYPE;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalConditions.CONDITION_DIABETES;
import static seedu.uninurse.testutil.TypicalConditions.CONDITION_OSTEOPOROSIS;
import static seedu.uninurse.testutil.TypicalConditions.TYPICAL_CONDITION_DIABETES;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
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
 * Contains integration tests (interaction with the Model) and unit tests for {@code AddConditionCommand}.
 */
public class AddConditionCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullCondition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddConditionCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddConditionCommand(null, CONDITION_DIABETES));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AddConditionCommand addConditionCommand = new AddConditionCommand(INDEX_FIRST_PERSON, CONDITION_DIABETES);
        assertThrows(NullPointerException.class, () -> addConditionCommand.execute(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToAddCondition = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToAddCondition)
                .withConditions(TYPICAL_CONDITION_DIABETES).build();
        int lastConditionIndex = editedPatient.getConditions().size() - 1;
        Condition addedCondition = editedPatient.getConditions().get(lastConditionIndex);

        AddConditionCommand addConditionCommand = new AddConditionCommand(INDEX_FIRST_PERSON, addedCondition);

        String expectedMessage = String.format(AddConditionCommand.MESSAGE_ADD_CONDITION_SUCCESS,
                editedPatient.getName(), addedCondition);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToAddCondition, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(addConditionCommand, model, expectedMessage, ADD_CONDITION_COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddConditionCommand addConditionCommand = new AddConditionCommand(outOfBoundIndex, CONDITION_DIABETES);

        assertCommandFailure(addConditionCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {

        Patient patientToAddCondition = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withConditions(TYPICAL_CONDITION_DIABETES).build();
        int lastConditionIndex = editedPatient.getConditions().size() - 1;
        Condition addedCondition = editedPatient.getConditions().get(lastConditionIndex);

        AddConditionCommand addConditionCommand = new AddConditionCommand(INDEX_FIRST_PERSON, addedCondition);

        String expectedMessage = String.format(AddConditionCommand.MESSAGE_ADD_CONDITION_SUCCESS,
                editedPatient.getName(), addedCondition);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToAddCondition, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(addConditionCommand, model, expectedMessage, ADD_CONDITION_COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        AddConditionCommand addConditionCommand = new AddConditionCommand(outOfBoundIndex, CONDITION_DIABETES);

        assertCommandFailure(addConditionCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateCondition_throwsCommandException() {
        Patient patientToEdit = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Condition condition = new Condition("H1N1");
        AddConditionCommand addConditionCommand = new AddConditionCommand(INDEX_SECOND_PERSON, condition);
        assertCommandFailure(addConditionCommand, model,
                String.format(Messages.MESSAGE_DUPLICATE_CONDITION, patientToEdit.getName()));
    }

    @Test
    public void equals() {
        AddConditionCommand addConditionFirstDiabetes = new AddConditionCommand(INDEX_FIRST_PERSON, CONDITION_DIABETES);
        AddConditionCommand addConditionSecondDiabetes =
                new AddConditionCommand(INDEX_SECOND_PERSON, CONDITION_DIABETES);
        AddConditionCommand addConditionFirstOsteoporosis =
                new AddConditionCommand(INDEX_FIRST_PERSON, CONDITION_OSTEOPOROSIS);

        // same object -> returns true
        assertEquals(addConditionFirstDiabetes, addConditionFirstDiabetes);

        // same values -> returns true
        AddConditionCommand addConditionFirstDiabetesCopy =
                new AddConditionCommand(INDEX_FIRST_PERSON, CONDITION_DIABETES);
        assertEquals(addConditionFirstDiabetes, addConditionFirstDiabetesCopy);

        // different types -> returns false
        assertNotEquals(1, addConditionFirstDiabetes);

        // null -> returns false
        assertNotEquals(null, addConditionFirstDiabetes);

        // different condition -> returns false
        assertNotEquals(addConditionFirstDiabetes, addConditionFirstOsteoporosis);

        // different patient index -> returns false
        assertNotEquals(addConditionFirstDiabetes, addConditionSecondDiabetes);
    }
}
