package seedu.guest.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_ROOM_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.guest.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.guest.logic.commands.CommandTestUtil.showGuestAtIndex;
import static seedu.guest.testutil.TypicalGuests.getTypicalGuestBook;
import static seedu.guest.testutil.TypicalIndexes.INDEX_FIRST_GUEST;
import static seedu.guest.testutil.TypicalIndexes.INDEX_SECOND_GUEST;

import org.junit.jupiter.api.Test;

import seedu.guest.commons.core.Messages;
import seedu.guest.commons.core.index.Index;
import seedu.guest.logic.commands.EditCommand.EditGuestDescriptor;
import seedu.guest.model.GuestBook;
import seedu.guest.model.Model;
import seedu.guest.model.ModelManager;
import seedu.guest.model.UserPrefs;
import seedu.guest.model.guest.Guest;
import seedu.guest.testutil.EditGuestDescriptorBuilder;
import seedu.guest.testutil.GuestBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalGuestBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Guest editedGuest = new GuestBuilder().build();
        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder(editedGuest).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_GUEST, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_GUEST_SUCCESS, editedGuest);

        Model expectedModel = new ModelManager(new GuestBook(model.getGuestBook()), new UserPrefs());
        expectedModel.setGuest(model.getFilteredGuestList().get(0), editedGuest);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastGuest = Index.fromOneBased(model.getFilteredGuestList().size());
        Guest lastGuest = model.getFilteredGuestList().get(indexLastGuest.getZeroBased());

        GuestBuilder guestInList = new GuestBuilder(lastGuest);
        Guest editedGuest = guestInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).withRoom(VALID_ROOM_BOB)
                .build();

        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withRoom(VALID_ROOM_BOB).build();
        EditCommand editCommand = new EditCommand(indexLastGuest, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_GUEST_SUCCESS, editedGuest);

        Model expectedModel = new ModelManager(new GuestBook(model.getGuestBook()), new UserPrefs());
        expectedModel.setGuest(lastGuest, editedGuest);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_GUEST, new EditGuestDescriptor());
        Guest editedGuest = model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_GUEST_SUCCESS, editedGuest);

        Model expectedModel = new ModelManager(new GuestBook(model.getGuestBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showGuestAtIndex(model, INDEX_FIRST_GUEST);

        Guest guestInFilteredList = model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased());
        Guest editedGuest = new GuestBuilder(guestInFilteredList).withName(VALID_NAME_BOB).withRoom(VALID_ROOM_BOB)
                .build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_GUEST,
                new EditGuestDescriptorBuilder().withName(VALID_NAME_BOB).withRoom(VALID_ROOM_BOB)
                        .build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_GUEST_SUCCESS, editedGuest);

        Model expectedModel = new ModelManager(new GuestBook(model.getGuestBook()), new UserPrefs());
        expectedModel.setGuest(model.getFilteredGuestList().get(0), editedGuest);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateGuestUnfilteredList_failure() {
        Guest firstGuest = model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased());
        EditCommand.EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder(firstGuest).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_GUEST, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_GUEST);
    }

    @Test
    public void execute_duplicateGuestFilteredList_failure() {
        showGuestAtIndex(model, INDEX_FIRST_GUEST);

        // edit guest in filtered list into a duplicate in guest book
        Guest guestInList = model.getGuestBook().getGuestList().get(INDEX_SECOND_GUEST.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_GUEST,
                new EditGuestDescriptorBuilder(guestInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_GUEST);
    }

    @Test
    public void execute_roomOccupiedFilteredList_failure() {
        showGuestAtIndex(model, INDEX_FIRST_GUEST);

        // edit guest in filtered list into a duplicate in guest book
        Guest guestInList = model.getGuestBook().getGuestList().get(INDEX_SECOND_GUEST.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_GUEST,
                new EditGuestDescriptorBuilder(guestInList).withName("Anderson").build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_ROOM_OCCUPIED);
    }

    @Test
    public void execute_invalidGuestIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGuestList().size() + 1);
        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of guest book
     */
    @Test
    public void execute_invalidGuestIndexFilteredList_failure() {
        showGuestAtIndex(model, INDEX_FIRST_GUEST);
        Index outOfBoundIndex = INDEX_SECOND_GUEST;
        // ensures that outOfBoundIndex is still in bounds of guest book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getGuestBook().getGuestList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditGuestDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_GUEST, DESC_AMY);

        // same values -> returns true
        EditGuestDescriptor copyDescriptor = new EditCommand.EditGuestDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_GUEST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_GUEST, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_GUEST, DESC_BOB)));
    }

}
