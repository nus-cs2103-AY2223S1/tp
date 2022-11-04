package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
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
 * Contains integration tests (interaction with the Model) and unit tests for AddTagCommand.
 */
public class AddTagCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null, TAG_ELDERLY));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, TAG_ELDERLY);
        assertThrows(NullPointerException.class, () -> addTagCommand.execute(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToAddTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToAddTag).withTags(TYPICAL_TAG_ELDERLY).build();
        int lastTagIndex = editedPatient.getTags().size() - 1;
        Tag addedTag = editedPatient.getTags().get(lastTagIndex);

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, addedTag);

        String expectedMessage = String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS,
                editedPatient.getName(), addedTag);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToAddTag, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(addTagCommand, model, expectedMessage, AddTagCommand.ADD_TAG_COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, TAG_ELDERLY);

        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDuplicateTagUnfilteredList_throwsCommandException() {
        Patient patientToEdit = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Tag tag = new Tag("high-risk");
        AddTagCommand addTagCommand = new AddTagCommand(INDEX_SECOND_PERSON, tag);
        assertCommandFailure(addTagCommand, model,
                String.format(Messages.MESSAGE_DUPLICATE_TAG, patientToEdit.getName()));
    }

    @Test
    public void execute_validIndexFilteredList_success() {

        Patient patientToAddTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withTags(TYPICAL_TAG_ELDERLY).build();
        int lastTagIndex = editedPatient.getTags().size() - 1;
        Tag addedTag = editedPatient.getTags().get(lastTagIndex);

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, addedTag);

        String expectedMessage = String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS,
                editedPatient.getName(), addedTag);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToAddTag, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(addTagCommand, model, expectedMessage,
                AddTagCommand.ADD_TAG_COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, TAG_ELDERLY);

        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDuplicateTagFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);
        Patient patientToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Tag tag = new Tag("high-risk");
        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tag);
        assertCommandFailure(addTagCommand, model,
                String.format(Messages.MESSAGE_DUPLICATE_TAG, patientToEdit.getName()));
    }

    @Test
    public void equals() {
        AddTagCommand addTagFirstElderly = new AddTagCommand(INDEX_FIRST_PERSON, TAG_ELDERLY);
        AddTagCommand addTagSecondElderly = new AddTagCommand(INDEX_SECOND_PERSON, TAG_ELDERLY);
        AddTagCommand addTagFirstNursingHome = new AddTagCommand(INDEX_FIRST_PERSON, TAG_NURSING_HOME);

        // same object -> returns true
        assertEquals(addTagFirstElderly, addTagFirstElderly);

        // same values -> returns true
        AddTagCommand addTagFirstElderlyCopy = new AddTagCommand(INDEX_FIRST_PERSON, TAG_ELDERLY);
        assertEquals(addTagFirstElderly, addTagFirstElderlyCopy);

        // different types -> returns false
        assertNotEquals(1, addTagFirstElderly);

        // null -> returns false
        assertNotEquals(null, addTagFirstElderly);

        // different tag -> returns false
        assertNotEquals(addTagFirstElderly, addTagFirstNursingHome);

        // different patient index -> returns false
        assertNotEquals(addTagFirstElderly, addTagSecondElderly);
    }
}
