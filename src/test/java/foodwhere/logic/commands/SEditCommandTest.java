package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandFailure;
import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static foodwhere.logic.commands.SEditCommand.MESSAGE_INVALID_INDEX_ERROR;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.index.Index;
import foodwhere.model.AddressBook;
import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.model.stall.Stall;
import foodwhere.model.stall.StallBuilder;
import foodwhere.testutil.EditStallDescriptorBuilder;
import foodwhere.testutil.TypicalIndexes;
import foodwhere.testutil.TypicalStalls;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SEditCommand.
 */
public class SEditCommandTest {

    private Model model = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Stall editedStall = new StallBuilder().build();
        SEditCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder(editedStall).build();
        SEditCommand sEditCommand = new SEditCommand(TypicalIndexes.INDEX_FIRST_STALL, descriptor);

        String expectedMessage = String.format(SEditCommand.MESSAGE_EDIT_STALL_SUCCESS, editedStall);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStall(model.getFilteredStallList().get(0), editedStall);

        assertCommandSuccess(sEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastStall = Index.fromOneBased(model.getFilteredStallList().size());
        Stall lastStall = model.getFilteredStallList().get(indexLastStall.getZeroBased());

        StallBuilder stallInList = new StallBuilder(lastStall);
        Stall editedStall =
                stallInList.withName(CommandTestUtil.VALID_NAME_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();

        SEditCommand.EditStallDescriptor descriptor =
                new EditStallDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        SEditCommand sEditCommand = new SEditCommand(indexLastStall, descriptor);

        String expectedMessage = String.format(SEditCommand.MESSAGE_EDIT_STALL_SUCCESS, editedStall);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStall(lastStall, editedStall);

        assertCommandSuccess(sEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        SEditCommand sEditCommand =
                new SEditCommand(TypicalIndexes.INDEX_FIRST_STALL, new SEditCommand.EditStallDescriptor());

        assertCommandFailure(sEditCommand, model, SEditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showStallAtIndex(model, TypicalIndexes.INDEX_FIRST_STALL);

        Stall stallInFilteredList =
                model.getFilteredStallList().get(TypicalIndexes.INDEX_FIRST_STALL.getZeroBased());
        Stall editedStall = new StallBuilder(stallInFilteredList).withName(CommandTestUtil.VALID_NAME_BOB).build();
        SEditCommand sEditCommand = new SEditCommand(TypicalIndexes.INDEX_FIRST_STALL,
                new EditStallDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(SEditCommand.MESSAGE_EDIT_STALL_SUCCESS, editedStall);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStall(model.getFilteredStallList().get(0), editedStall);

        assertCommandSuccess(sEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStallUnfilteredList_failure() {
        Stall firstStall = model.getFilteredStallList().get(TypicalIndexes.INDEX_FIRST_STALL.getZeroBased());
        SEditCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder(firstStall).build();
        SEditCommand sEditCommand = new SEditCommand(TypicalIndexes.INDEX_SECOND_STALL, descriptor);

        assertCommandFailure(sEditCommand, model, SEditCommand.MESSAGE_DUPLICATE_STALL);
    }

    @Test
    public void execute_duplicateStallFilteredList_failure() {
        CommandTestUtil.showStallAtIndex(model, TypicalIndexes.INDEX_FIRST_STALL);

        // edit stall in filtered list into a duplicate in address book
        Stall stallInList =
                model.getAddressBook().getStallList().get(TypicalIndexes.INDEX_SECOND_STALL.getZeroBased());
        SEditCommand sEditCommand = new SEditCommand(TypicalIndexes.INDEX_FIRST_STALL,
                new EditStallDescriptorBuilder(stallInList).build());

        assertCommandFailure(sEditCommand, model, SEditCommand.MESSAGE_DUPLICATE_STALL);
    }

    @Test
    public void execute_invalidStallIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStallList().size() + 1);
        SEditCommand.EditStallDescriptor descriptor =
                new EditStallDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build();
        SEditCommand sEditCommand = new SEditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(sEditCommand, model, MESSAGE_INVALID_INDEX_ERROR);
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

        SEditCommand sEditCommand = new SEditCommand(outOfBoundIndex,
                new EditStallDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        assertCommandFailure(sEditCommand, model, MESSAGE_INVALID_INDEX_ERROR);
    }

    @Test
    public void equals() {
        final SEditCommand standardCommand =
                new SEditCommand(TypicalIndexes.INDEX_FIRST_STALL, CommandTestUtil.DESC_AMY);

        // same values -> returns true
        SEditCommand.EditStallDescriptor copyDescriptor =
                new SEditCommand.EditStallDescriptor(CommandTestUtil.DESC_AMY);
        SEditCommand commandWithSameValues = new SEditCommand(TypicalIndexes.INDEX_FIRST_STALL, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new SEditCommand(TypicalIndexes.INDEX_SECOND_STALL, CommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(
                new SEditCommand(TypicalIndexes.INDEX_FIRST_STALL, CommandTestUtil.DESC_BOB)));
    }

}
