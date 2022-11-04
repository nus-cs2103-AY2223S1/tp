package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.uninurse.logic.commands.DeleteTagCommand.DELETE_TAG_COMMAND_TYPE;
import static seedu.uninurse.logic.commands.DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS;
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
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteTagCommand.
 */
public class DeleteTagCommandTest {
    private final Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());

    @Test
    public void constructor_nullTagIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteTagCommand(INDEX_SECOND_PERSON, null));
    }

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteTagCommand(null, INDEX_FIRST_ATTRIBUTE));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        DeleteTagCommand deleteTagCommand =
                new DeleteTagCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE);
        assertThrows(NullPointerException.class, () -> deleteTagCommand.execute(null));
    }

    @Test
    void execute_validIndicesUnfilteredList_success() {
        // use third person in TypicalPersons since there is one tag to delete
        Patient patientToDeleteTag = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(patientToDeleteTag).withTags().build();
        Tag deletedTag = patientToDeleteTag.getTags().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        DeleteTagCommand deleteTagCommand =
                new DeleteTagCommand(INDEX_THIRD_PERSON, INDEX_FIRST_ATTRIBUTE);

        String expectedMessage = String.format(MESSAGE_DELETE_TAG_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName(), deletedTag);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToDeleteTag, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, DELETE_TAG_COMMAND_TYPE,
                expectedModel);
    }

    @Test
    void execute_invalidPatientIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundPatientIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteTagCommand deleteTagCommand =
                new DeleteTagCommand(outOfBoundPatientIndex, INDEX_FIRST_ATTRIBUTE);

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_validIndicesFilteredList_success() {
        // use third person in TypicalPersons since there is one tag to delete

        Patient patientToDeleteTag = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        Patient editedPatient = new PersonBuilder(model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased()))
                .withTags().build();
        Tag deletedTag = patientToDeleteTag.getTags().get(INDEX_FIRST_ATTRIBUTE.getZeroBased());

        DeleteTagCommand deleteTagCommand =
                new DeleteTagCommand(INDEX_THIRD_PERSON, INDEX_FIRST_ATTRIBUTE);

        String expectedMessage = String.format(MESSAGE_DELETE_TAG_SUCCESS, INDEX_FIRST_ATTRIBUTE.getOneBased(),
                editedPatient.getName().toString(), deletedTag);

        Model expectedModel = new ModelManager(new UninurseBook(model.getUninurseBook()), new UserPrefs());
        expectedModel.setPerson(patientToDeleteTag, editedPatient);
        expectedModel.setPatientOfInterest(editedPatient);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, DELETE_TAG_COMMAND_TYPE,
                expectedModel);
    }

    @Test
    void execute_invalidPatientIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of uninurse book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUninurseBook().getPersonList().size());

        DeleteTagCommand deleteTagCommand =
                new DeleteTagCommand(outOfBoundIndex, INDEX_FIRST_ATTRIBUTE);

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_invalidTagIndex_throwsCommandException() {
        Patient patient = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Index outOfBoundTagIndex = Index.fromOneBased(patient.getTags().size() + 1);
        DeleteTagCommand deleteTagCommand =
                new DeleteTagCommand(INDEX_FIRST_PERSON, outOfBoundTagIndex);

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_TAG_INDEX);
    }

    @Test
    public void equals() {
        DeleteTagCommand deleteFirstPersonFirstTag =
                new DeleteTagCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);
        DeleteTagCommand deleteSecondPersonFirstTag =
                new DeleteTagCommand(INDEX_SECOND_PERSON, INDEX_FIRST_ATTRIBUTE);
        DeleteTagCommand deleteFirstPersonSecondTag =
                new DeleteTagCommand(INDEX_FIRST_PERSON, INDEX_SECOND_ATTRIBUTE);

        // same object -> returns true
        assertEquals(deleteFirstPersonFirstTag, deleteFirstPersonFirstTag);

        // same values -> returns true
        DeleteTagCommand deleteFirstPersonFirstTagCopy =
                new DeleteTagCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE);
        assertEquals(deleteFirstPersonFirstTag, deleteFirstPersonFirstTagCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstPersonFirstTag);

        // null -> returns false
        assertNotEquals(null, deleteFirstPersonFirstTag);

        // different person index -> returns false
        assertNotEquals(deleteFirstPersonFirstTag, deleteSecondPersonFirstTag);

        // different tag index -> returns false
        assertNotEquals(deleteFirstPersonFirstTag, deleteFirstPersonSecondTag);
    }
}
