package seedu.rc4hdb.logic.commands.venuecommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.DESC_AMY;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.DESC_BOB;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.VALID_NAME_BOB;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.VALID_PHONE_BOB;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.showResidentAtIndex;
import static seedu.rc4hdb.testutil.TypicalIndexes.INDEX_FIRST_RESIDENT;
import static seedu.rc4hdb.testutil.TypicalIndexes.INDEX_SECOND_RESIDENT;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;
import static seedu.rc4hdb.testutil.TypicalVenues.getTypicalVenueBook;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.commons.core.Messages;
import seedu.rc4hdb.commons.core.index.Index;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.testutil.ResidentBuilder;
import seedu.rc4hdb.testutil.ResidentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for BookCommand.
 */
public class BookCommandTest {

    private Model model = new ModelManager(getTypicalResidentBook(), getTypicalVenueBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Resident editedResident = new ResidentBuilder().build();
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder(editedResident).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_RESIDENT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RESIDENT_SUCCESS, editedResident);

        Model expectedModel = new ModelManager(new ResidentBook(model.getResidentBook()),
                new VenueBook(), new UserPrefs());
        expectedModel.setResident(model.getFilteredResidentList().get(0), editedResident);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastResident = Index.fromOneBased(model.getFilteredResidentList().size());
        Resident lastResident = model.getFilteredResidentList().get(indexLastResident.getZeroBased());

        ResidentBuilder residentInList = new ResidentBuilder(lastResident);
        Resident editedResident = residentInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastResident, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RESIDENT_SUCCESS, editedResident);

        Model expectedModel = new ModelManager(new ResidentBook(model.getResidentBook()),
                new VenueBook(), new UserPrefs());
        expectedModel.setResident(lastResident, editedResident);

        assertCommandFailure(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_RESIDENT, new ResidentDescriptor());
        Resident editedResident = model.getFilteredResidentList().get(INDEX_FIRST_RESIDENT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RESIDENT_SUCCESS, editedResident);

        Model expectedModel = new ModelManager(new ResidentBook(model.getResidentBook()),
                new VenueBook(), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showResidentAtIndex(model, INDEX_FIRST_RESIDENT);

        Resident residentInFilteredList = model.getFilteredResidentList().get(INDEX_FIRST_RESIDENT.getZeroBased());
        Resident editedResident = new ResidentBuilder(residentInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_RESIDENT,
                new ResidentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RESIDENT_SUCCESS, editedResident);

        Model expectedModel = new ModelManager(new ResidentBook(model.getResidentBook()),
                new VenueBook(), new UserPrefs());
        expectedModel.setResident(model.getFilteredResidentList().get(0), editedResident);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateResidentUnfilteredList_failure() {
        Resident firstResident = model.getFilteredResidentList().get(INDEX_FIRST_RESIDENT.getZeroBased());
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder(firstResident).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_RESIDENT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_RESIDENT);
    }

    @Test
    public void execute_duplicateResidentFilteredList_failure() {
        showResidentAtIndex(model, INDEX_FIRST_RESIDENT);

        // edit resident in filtered list into a duplicate in address book
        Resident residentInList = model.getResidentBook().getResidentList().get(INDEX_SECOND_RESIDENT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_RESIDENT,
                new ResidentDescriptorBuilder(residentInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_RESIDENT);
    }

    @Test
    public void execute_invalidResidentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredResidentList().size() + 1);
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidResidentIndexFilteredList_failure() {
        showResidentAtIndex(model, INDEX_FIRST_RESIDENT);
        Index outOfBoundIndex = INDEX_SECOND_RESIDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResidentBook().getResidentList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new ResidentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_RESIDENT, DESC_AMY);

        // same values -> returns true
        ResidentDescriptor copyDescriptor = new ResidentDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_RESIDENT, copyDescriptor);

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_RESIDENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_RESIDENT, DESC_BOB)));
    }

}
