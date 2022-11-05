package seedu.realtime.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.realtime.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.realtime.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.realtime.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.realtime.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.realtime.testutil.TypicalClients.getTypicalRealTime;
import static seedu.realtime.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.realtime.testutil.TypicalIndexes.SECOND_INDEX;

import org.junit.jupiter.api.Test;

import seedu.realtime.commons.core.Messages;
import seedu.realtime.commons.core.index.Index;
import seedu.realtime.model.Model;
import seedu.realtime.model.ModelManager;
import seedu.realtime.model.RealTime;
import seedu.realtime.model.UserPrefs;
import seedu.realtime.model.person.Client;
import seedu.realtime.testutil.ClientBuilder;
import seedu.realtime.testutil.EditClientDescriptorBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditClientCommandTest {

    private Model model = new ModelManager(getTypicalRealTime(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Client editedClient = new ClientBuilder().build();
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient).build();
        EditClientCommand editClientCommand = new EditClientCommand(FIRST_INDEX, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new RealTime(model.getRealTime()), new UserPrefs());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
        Client lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());

        ClientBuilder clientInList = new ClientBuilder(lastClient);
        Client editedClient = clientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditClientCommand editClientCommand = new EditClientCommand(indexLastClient, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new RealTime(model.getRealTime()), new UserPrefs());
        expectedModel.setClient(lastClient, editedClient);

        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditClientCommand editClientCommand = new EditClientCommand(
                FIRST_INDEX, new EditClientCommand.EditClientDescriptor());
        Client editedClient = model.getFilteredClientList().get(FIRST_INDEX.getZeroBased());

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new RealTime(model.getRealTime()), new UserPrefs());

        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showClientAtIndex(model, FIRST_INDEX);

        Client clientInFilteredList = model.getFilteredClientList().get(FIRST_INDEX.getZeroBased());
        Client editedClient = new ClientBuilder(clientInFilteredList).withName(VALID_NAME_BOB).build();
        EditClientCommand editClientCommand = new EditClientCommand(FIRST_INDEX,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new RealTime(model.getRealTime()), new UserPrefs());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateClientUnfilteredList_failure() {
        Client firstClient = model.getFilteredClientList().get(FIRST_INDEX.getZeroBased());
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient).build();
        EditClientCommand editClientCommand = new EditClientCommand(SECOND_INDEX, descriptor);

        assertCommandFailure(editClientCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_duplicateClientFilteredList_failure() {
        showClientAtIndex(model, FIRST_INDEX);

        // edit client in filtered list into a duplicate in address book
        Client clientInList = model.getRealTime().getClientList().get(SECOND_INDEX.getZeroBased());
        EditClientCommand editClientCommand = new EditClientCommand(FIRST_INDEX,
                new EditClientDescriptorBuilder(clientInList).build());

        assertCommandFailure(editClientCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditClientCommand editClientCommand = new EditClientCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editClientCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidClientIndexFilteredList_failure() {
        showClientAtIndex(model, FIRST_INDEX);
        Index outOfBoundIndex = SECOND_INDEX;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getRealTime().getClientList().size());

        EditClientCommand editClientCommand = new EditClientCommand(outOfBoundIndex,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editClientCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditClientCommand standardCommand = new EditClientCommand(FIRST_INDEX, DESC_AMY);

        // same values -> returns true
        EditClientCommand.EditClientDescriptor copyDescriptor = new EditClientCommand.EditClientDescriptor(DESC_AMY);
        EditClientCommand commandWithSameValues = new EditClientCommand(FIRST_INDEX, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditClientCommand(SECOND_INDEX, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditClientCommand(FIRST_INDEX, DESC_BOB)));
    }

}
