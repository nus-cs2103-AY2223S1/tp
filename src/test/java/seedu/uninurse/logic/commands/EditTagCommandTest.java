package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.logic.commands.EditTagCommand.EDIT_TAG_COMMAND_TYPE;
import static seedu.uninurse.logic.commands.EditTagCommand.MESSAGE_EDIT_TAG_SUCCESS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;
import static seedu.uninurse.testutil.TypicalTags.TAG_ELDERLY;
import static seedu.uninurse.testutil.TypicalTags.TAG_NURSING_HOME;
import static seedu.uninurse.testutil.TypicalTags.TYPICAL_TAG_ELDERLY;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditTagCommand.
 */
public class EditTagCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTagCommand(null, INDEX_FIRST_ATTRIBUTE, TAG_ELDERLY));
    }

    @Test
    public void constructor_nullTagIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTagCommand(INDEX_FIRST_PERSON, null, TAG_ELDERLY));
    }

    @Test
    public void constructor_nullEditTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTagCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        EditTagCommand editTagCommand =
                new EditTagCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, TAG_ELDERLY);
        assertThrows(NullPointerException.class, () -> editTagCommand.execute(null));
    }

    @Test
    public void execute_validArgsUnfilteredList_success() {
        // Use third patient in typical persons because it only has one tag, so we only have to
        // replace it rather than retrieve the other unedited tags
        Patient patientToEdit = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());

        Tag initialTag = patientToEdit.getTags().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        Patient editedPatient = new PersonBuilder(patientToEdit).withTags(TYPICAL_TAG_ELDERLY).build();

        EditTagCommand editTagCommand =
                new EditTagCommand(INDEX_THIRD_PERSON, INDEX_FIRST_ATTRIBUTE, TAG_ELDERLY);

        String expectedMessage = String.format(MESSAGE_EDIT_TAG_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName(), initialTag, TAG_ELDERLY);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToEdit, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(editTagCommand, model, expectedMessage, EDIT_TAG_COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidPatientIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundPatientIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditTagCommand editTagCommand =
                new EditTagCommand(outOfBoundPatientIndex, INDEX_FIRST_ATTRIBUTE, TAG_ELDERLY);

        assertCommandFailure(editTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validArgsFilteredList_success() {
        showPersonAtIndex(model, INDEX_THIRD_PERSON);
        Patient patientToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Tag initialTag = patientToEdit.getTags().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        Patient editedPatient = new PersonBuilder(patientToEdit).withTags(TYPICAL_TAG_ELDERLY).build();

        EditTagCommand editTagCommand =
                new EditTagCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, TAG_ELDERLY);

        String expectedMessage = String.format(MESSAGE_EDIT_TAG_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName(), initialTag, TAG_ELDERLY);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());

        showPersonAtIndex(expectedModel, INDEX_THIRD_PERSON);
        expectedModel.setPerson(patientToEdit, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(editTagCommand, model, expectedMessage, EDIT_TAG_COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidPatientIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        EditTagCommand editTagCommand =
                new EditTagCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE, TAG_ELDERLY);

        assertCommandFailure(editTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateTag_throwsCommandException() {
        Patient patientToEdit = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Tag tag = new Tag("high-risk");
        EditTagCommand editTagCommand =
                new EditTagCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, tag);
        assertCommandFailure(editTagCommand, model,
                String.format(Messages.MESSAGE_DUPLICATE_TAG, patientToEdit.getName()));
    }

    @Test
    public void execute_invalidTagIndex_throwsCommandException() {
        Patient patient = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Index outOfBoundTagIndex = Index.fromOneBased(patient.getTags().size() + 1);
        EditTagCommand editTagCommand = new EditTagCommand(INDEX_FIRST_PERSON, outOfBoundTagIndex, TAG_ELDERLY);

        assertCommandFailure(editTagCommand, model, Messages.MESSAGE_INVALID_TAG_INDEX);
    }

    @Test
    public void equals() {
        EditTagCommand editFirstPersonFirstTagElderly =
                new EditTagCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, TAG_ELDERLY);
        EditTagCommand editSecondPersonFirstTagElderly =
                new EditTagCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE, TAG_ELDERLY);
        EditTagCommand editFirstPersonSecondTagElderly =
                new EditTagCommand(INDEX_FIRST_PERSON, INDEX_SECOND_ATTRIBUTE, TAG_ELDERLY);
        EditTagCommand editFirstPersonFirstTagNursingHome =
                new EditTagCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, TAG_NURSING_HOME);

        // same object -> returns true
        assertEquals(editFirstPersonFirstTagElderly, editFirstPersonFirstTagElderly);

        // same values -> returns true
        EditTagCommand editFirstPersonFirstTagElderlyCopy =
                new EditTagCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, TAG_ELDERLY);
        assertEquals(editFirstPersonFirstTagElderly, editFirstPersonFirstTagElderlyCopy);

        // different types -> returns false
        assertNotEquals(1, editFirstPersonFirstTagElderly);

        // null -> returns false
        assertNotEquals(null, editFirstPersonFirstTagElderly);

        // different person index -> returns false
        assertNotEquals(editFirstPersonFirstTagElderly, editSecondPersonFirstTagElderly);

        // different tag index -> returns false
        assertNotEquals(editFirstPersonFirstTagElderly, editFirstPersonSecondTagElderly);

        // different tag -> returns false
        assertNotEquals(editFirstPersonFirstTagElderly, editFirstPersonFirstTagNursingHome);

    }
}
