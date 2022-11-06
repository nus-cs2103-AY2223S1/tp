package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTeammateAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAMMATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAMMATE;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;
import static seedu.address.testutil.TypicalTeammates.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditTeammateDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskPanel;
import seedu.address.model.UserPrefs;
import seedu.address.model.teammate.Teammate;
import seedu.address.testutil.EditTeammateDescriptorBuilder;
//import seedu.address.testutil.TeammateBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    //    @Test
    //    public void execute_allFieldsSpecifiedUnfilteredList_success() {
    //        Teammate editedTeammate = new TeammateBuilder().build();
    //        EditTeammateDescriptor descriptor = new EditTeammateDescriptorBuilder(editedTeammate).build();
    //        EditCommand editCommand = new EditCommand(INDEX_FIRST_TEAMMATE, descriptor);
    //
    //        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TEAMMATE_SUCCESS, editedTeammate);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
    //            new TaskPanel(model.getTaskPanel()), new UserPrefs());
    //        expectedModel.setTeammate(model.getFilteredTeammateList().get(0), editedTeammate);
    //
    //        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    //    }

    //    @Test
    //    public void execute_someFieldsSpecifiedUnfilteredList_success() {
    //        Index indexLastTeammate = Index.fromOneBased(model.getFilteredTeammateList().size());
    //        Teammate lastTeammate = model.getFilteredTeammateList().get(indexLastTeammate.getZeroBased());
    //
    //        TeammateBuilder teammateInList = new TeammateBuilder(lastTeammate);
    //        Teammate editedTeammate = teammateInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
    //                .withTags(VALID_TAG_HUSBAND).build();
    //
    //        EditTeammateDescriptor descriptor = new EditTeammateDescriptorBuilder().withName(VALID_NAME_BOB)
    //                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
    //        EditCommand editCommand = new EditCommand(indexLastTeammate, descriptor);
    //
    //        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TEAMMATE_SUCCESS, editedTeammate);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
    //            new TaskPanel(model.getTaskPanel()), new UserPrefs());
    //        expectedModel.setTeammate(lastTeammate, editedTeammate);
    //
    //        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TEAMMATE, new EditCommand.EditTeammateDescriptor());
        Teammate editedTeammate = model.getFilteredTeammateList().get(INDEX_FIRST_TEAMMATE.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TEAMMATE_SUCCESS, editedTeammate);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
            new TaskPanel(model.getTaskPanel()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    //    @Test
    //    public void execute_filteredList_success() {
    //        showTeammateAtIndex(model, INDEX_FIRST_TEAMMATE);
    //
    //        Teammate teammateInFilteredList =
    //              model.getFilteredTeammateList().get(INDEX_FIRST_TEAMMATE.getZeroBased());
    //        Teammate editedTeammate = new TeammateBuilder(teammateInFilteredList).withName(VALID_NAME_BOB).build();
    //        EditCommand editCommand = new EditCommand(INDEX_FIRST_TEAMMATE,
    //                new EditTeammateDescriptorBuilder().withName(VALID_NAME_BOB).build());
    //
    //        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TEAMMATE_SUCCESS, editedTeammate);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
    //            new TaskPanel(model.getTaskPanel()), new UserPrefs());
    //        expectedModel.setTeammate(model.getFilteredTeammateList().get(0), editedTeammate);
    //
    //        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_duplicateTeammateUnfilteredList_failure() {
        Teammate firstTeammate = model.getFilteredTeammateList().get(INDEX_FIRST_TEAMMATE.getZeroBased());
        EditTeammateDescriptor descriptor = new EditTeammateDescriptorBuilder(firstTeammate).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_TEAMMATE, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TEAMMATE);
    }

    @Test
    public void execute_duplicateTeammateFilteredList_failure() {
        showTeammateAtIndex(model, INDEX_FIRST_TEAMMATE);

        // edit teammate in filtered list into a duplicate in address book
        Teammate teammateInList = model.getAddressBook().getTeammateList().get(INDEX_SECOND_TEAMMATE.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TEAMMATE,
                new EditTeammateDescriptorBuilder(teammateInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TEAMMATE);
    }

    @Test
    public void execute_invalidTeammateIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTeammateList().size() + 1);
        EditTeammateDescriptor descriptor = new EditTeammateDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidTeammateIndexFilteredList_failure() {
        showTeammateAtIndex(model, INDEX_FIRST_TEAMMATE);
        Index outOfBoundIndex = INDEX_SECOND_TEAMMATE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTeammateList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditTeammateDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_TEAMMATE, DESC_AMY);

        // same values -> returns true
        EditCommand.EditTeammateDescriptor copyDescriptor = new EditTeammateDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_TEAMMATE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_TEAMMATE, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_TEAMMATE, DESC_BOB)));
    }

}
