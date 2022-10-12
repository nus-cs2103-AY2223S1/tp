package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
import foodwhere.commons.core.index.Index;
import foodwhere.model.AddressBook;
import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.model.stall.Stall;
import foodwhere.testutil.EditStallDescriptorBuilder;
import foodwhere.testutil.StallBuilder;
import foodwhere.testutil.TypicalIndexes;
import foodwhere.testutil.TypicalStalls;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Stall editedStall = new StallBuilder().build();
        EditCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder(editedStall).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_STALL, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STALL_SUCCESS, editedStall);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStall(model.getFilteredStallList().get(0), editedStall);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastStall = Index.fromOneBased(model.getFilteredStallList().size());
        Stall lastStall = model.getFilteredStallList().get(indexLastStall.getZeroBased());

        StallBuilder stallInList = new StallBuilder(lastStall);
        Stall editedStall =
                stallInList.withName(CommandTestUtil.VALID_NAME_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();

        EditCommand.EditStallDescriptor descriptor =
                new EditStallDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastStall, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STALL_SUCCESS, editedStall);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStall(lastStall, editedStall);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand =
                new EditCommand(TypicalIndexes.INDEX_FIRST_STALL, new EditCommand.EditStallDescriptor());
        Stall editedStall = model.getFilteredStallList().get(TypicalIndexes.INDEX_FIRST_STALL.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STALL_SUCCESS, editedStall);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showStallAtIndex(model, TypicalIndexes.INDEX_FIRST_STALL);

        Stall stallInFilteredList =
                model.getFilteredStallList().get(TypicalIndexes.INDEX_FIRST_STALL.getZeroBased());
        Stall editedStall = new StallBuilder(stallInFilteredList).withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_STALL,
                new EditStallDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STALL_SUCCESS, editedStall);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStall(model.getFilteredStallList().get(0), editedStall);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStallUnfilteredList_failure() {
        Stall firstStall = model.getFilteredStallList().get(TypicalIndexes.INDEX_FIRST_STALL.getZeroBased());
        EditCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder(firstStall).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_SECOND_STALL, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STALL);
    }

    @Test
    public void execute_duplicateStallFilteredList_failure() {
        CommandTestUtil.showStallAtIndex(model, TypicalIndexes.INDEX_FIRST_STALL);

        // edit stall in filtered list into a duplicate in address book
        Stall stallInList =
                model.getAddressBook().getStallList().get(TypicalIndexes.INDEX_SECOND_STALL.getZeroBased());
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_STALL,
                new EditStallDescriptorBuilder(stallInList).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STALL);
    }

    @Test
    public void execute_invalidStallIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStallList().size() + 1);
        EditCommand.EditStallDescriptor descriptor =
                new EditStallDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStallIndexFilteredList_failure() {
        CommandTestUtil.showStallAtIndex(model, TypicalIndexes.INDEX_FIRST_STALL);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_STALL;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStallList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditStallDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand =
                new EditCommand(TypicalIndexes.INDEX_FIRST_STALL, CommandTestUtil.DESC_AMY);

        // same values -> returns true
        EditCommand.EditStallDescriptor copyDescriptor =
                new EditCommand.EditStallDescriptor(CommandTestUtil.DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(TypicalIndexes.INDEX_FIRST_STALL, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new EditCommand(TypicalIndexes.INDEX_SECOND_STALL, CommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(
                new EditCommand(TypicalIndexes.INDEX_FIRST_STALL, CommandTestUtil.DESC_BOB)));
    }

}
