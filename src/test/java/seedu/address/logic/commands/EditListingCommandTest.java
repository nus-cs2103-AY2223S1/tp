package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LISTING_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LISTING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_4_BEDROOM;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showListingAtIndex;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;
import static seedu.address.testutil.TypicalListings.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditListingCommand.EditListingDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.listing.Listing;
import seedu.address.testutil.EditListingDescriptorBuilder;
import seedu.address.testutil.ListingBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditListingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Listing editedListing = new ListingBuilder().build();
        EditListingDescriptor descriptor = new EditListingDescriptorBuilder(editedListing).build();
        EditListingCommand editListingCommand = new EditListingCommand(FIRST_INDEX, descriptor);

        String expectedMessage = String.format(EditListingCommand.MESSAGE_EDIT_LISTING_SUCCESS, editedListing);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setListing(model.getFilteredListingList().get(0), editedListing);

        assertCommandSuccess(editListingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastListing = Index.fromOneBased(model.getFilteredListingList().size());
        Listing lastListing = model.getFilteredListingList().get(indexLastListing.getZeroBased());

        ListingBuilder listingInList = new ListingBuilder(lastListing);
        Listing editedListing = listingInList.withOwner(VALID_NAME_BOB).withId(VALID_ID_BOB)
                .withTags(VALID_TAG_4_BEDROOM).build();

        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withName(VALID_NAME_BOB)
                .withId(VALID_ID_BOB).withTags(VALID_TAG_4_BEDROOM).build();
        EditListingCommand editListingCommand = new EditListingCommand(indexLastListing, descriptor);

        String expectedMessage = String.format(EditListingCommand.MESSAGE_EDIT_LISTING_SUCCESS, editedListing);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setListing(lastListing, editedListing);

        assertCommandSuccess(editListingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditListingCommand editListingCommand = new EditListingCommand(FIRST_INDEX, new EditListingDescriptor());
        Listing editedListing = model.getFilteredListingList().get(FIRST_INDEX.getZeroBased());

        String expectedMessage = String.format(EditListingCommand.MESSAGE_EDIT_LISTING_SUCCESS, editedListing);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editListingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showListingAtIndex(model, FIRST_INDEX);

        Listing listingInFilteredList = model.getFilteredListingList().get(FIRST_INDEX.getZeroBased());
        Listing editedListing = new ListingBuilder(listingInFilteredList)
                .withOwner(VALID_NAME_BOB).build();
        EditListingCommand editListingCommand = new EditListingCommand(FIRST_INDEX,
                new EditListingDescriptorBuilder().withName(VALID_NAME_BOB).withTags(VALID_TAG_4_BEDROOM).build());

        String expectedMessage = String.format(EditListingCommand.MESSAGE_EDIT_LISTING_SUCCESS, editedListing);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setListing(model.getFilteredListingList().get(0), editedListing);

        assertCommandSuccess(editListingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateListingUnfilteredList_failure() {
        Listing firstListing = model.getFilteredListingList().get(FIRST_INDEX.getZeroBased());
        EditListingDescriptor descriptor = new EditListingDescriptorBuilder(firstListing).build();
        EditListingCommand editListingCommand = new EditListingCommand(SECOND_INDEX, descriptor);

        assertCommandFailure(editListingCommand, model, EditListingCommand.MESSAGE_DUPLICATE_LISTING);
    }

    @Test
    public void execute_duplicateListingFilteredList_failure() {
        showListingAtIndex(model, FIRST_INDEX);

        // edit listing in filtered list into a duplicate in address book
        Listing listingInList = model.getAddressBook().getListingList().get(SECOND_INDEX.getZeroBased());
        EditListingCommand editListingCommand = new EditListingCommand(FIRST_INDEX,
                new EditListingDescriptorBuilder(listingInList).build());

        assertCommandFailure(editListingCommand, model, EditListingCommand.MESSAGE_DUPLICATE_LISTING);
    }

    @Test
    public void execute_invalidListingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredListingList().size() + 1);
        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditListingCommand editListingCommand = new EditListingCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editListingCommand, model, Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidListingIndexFilteredList_failure() {
        showListingAtIndex(model, FIRST_INDEX);
        Index outOfBoundIndex = SECOND_INDEX;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getListingList().size());

        EditListingCommand editListingCommand = new EditListingCommand(outOfBoundIndex,
                new EditListingDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editListingCommand, model, Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditListingCommand standardCommand = new EditListingCommand(FIRST_INDEX, DESC_LISTING_AMY);

        // same values -> returns true
        EditListingDescriptor copyDescriptor = new EditListingDescriptor(DESC_LISTING_AMY);
        EditListingCommand commandWithSameValues = new EditListingCommand(FIRST_INDEX, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditListingCommand(SECOND_INDEX, DESC_LISTING_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditListingCommand(FIRST_INDEX, DESC_LISTING_BOB)));
    }

}
