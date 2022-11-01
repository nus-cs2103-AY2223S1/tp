package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.logic.commands.CommandTestUtil.DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.DESC_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static tuthub.logic.commands.CommandTestUtil.assertCommandFailure;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.logic.commands.CommandTestUtil.showTutorAtIndex;
import static tuthub.testutil.TypicalIndexes.INDEX_FIRST_TUTOR;
import static tuthub.testutil.TypicalIndexes.INDEX_SECOND_TUTOR;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import org.junit.jupiter.api.Test;

import tuthub.commons.core.Messages;
import tuthub.commons.core.index.Index;
import tuthub.logic.commands.EditCommand.EditTutorDescriptor;
import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.Tuthub;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.Tutor;
import tuthub.testutil.EditTutorDescriptorBuilder;
import tuthub.testutil.TutorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalTuthub(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Tutor editedTutor = new TutorBuilder().build();
        EditTutorDescriptor descriptor = new EditTutorDescriptorBuilder(editedTutor).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_TUTOR, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TUTOR_SUCCESS, editedTutor);

        Model expectedModel = new ModelManager(new Tuthub(model.getTuthub()), new UserPrefs());
        expectedModel.setTutor(model.getFilteredTutorList().get(1), editedTutor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTutor = Index.fromOneBased(model.getFilteredTutorList().size());
        Tutor lastTutor = model.getFilteredTutorList().get(indexLastTutor.getZeroBased());

        TutorBuilder tutorInList = new TutorBuilder(lastTutor);
        Tutor editedTutor = tutorInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditTutorDescriptor descriptor = new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastTutor, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TUTOR_SUCCESS, editedTutor);

        Model expectedModel = new ModelManager(new Tuthub(model.getTuthub()), new UserPrefs());
        expectedModel.setTutor(lastTutor, editedTutor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TUTOR, new EditTutorDescriptor());
        Tutor editedTutor = model.getFilteredTutorList().get(INDEX_FIRST_TUTOR.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TUTOR_SUCCESS, editedTutor);

        Model expectedModel = new ModelManager(new Tuthub(model.getTuthub()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showTutorAtIndex(model, INDEX_FIRST_TUTOR);

        Tutor tutorInFilteredList = model.getFilteredTutorList().get(INDEX_FIRST_TUTOR.getZeroBased());
        Tutor editedTutor = new TutorBuilder(tutorInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TUTOR,
                new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TUTOR_SUCCESS, editedTutor);

        Model expectedModel = new ModelManager(new Tuthub(model.getTuthub()), new UserPrefs());
        expectedModel.setTutor(model.getFilteredTutorList().get(0), editedTutor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTutorUnfilteredList_failure() {
        Tutor firstTutor = model.getFilteredTutorList().get(INDEX_FIRST_TUTOR.getZeroBased());
        EditTutorDescriptor descriptor = new EditTutorDescriptorBuilder(firstTutor).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_TUTOR, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TUTOR);
    }

    @Test
    public void execute_duplicateTutorFilteredList_failure() {
        showTutorAtIndex(model, INDEX_FIRST_TUTOR);

        // edit TUTOR in filtered list into a duplicate in tuthub
        Tutor tutorInList = model.getTuthub().getTutorList().get(INDEX_SECOND_TUTOR.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TUTOR,
                new EditTutorDescriptorBuilder(tutorInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TUTOR);
    }

    @Test
    public void execute_invalidTutorIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorList().size() + 1);
        EditTutorDescriptor descriptor = new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of tuthub
     */
    @Test
    public void execute_invalidTutorIndexFilteredList_failure() {
        showTutorAtIndex(model, INDEX_FIRST_TUTOR);
        Index outOfBoundIndex = INDEX_SECOND_TUTOR;
        // ensures that outOfBoundIndex is still in bounds of tuthub list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTuthub().getTutorList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_TUTOR, DESC_AMY);

        // same values -> returns true
        EditTutorDescriptor copyDescriptor = new EditTutorDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_TUTOR, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_TUTOR, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_TUTOR, DESC_BOB)));
    }

}
