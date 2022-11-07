package seedu.condonery.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_DESC_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_DESC_BOB;
import static seedu.condonery.testutil.TypicalClients.getTypicalClientDirectory;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.CommandTestUtil;
import seedu.condonery.logic.commands.client.EditClientCommand.EditClientDescriptor;
import seedu.condonery.logic.commands.property.ClearPropertyCommand;
import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.ClientDirectory;
import seedu.condonery.model.property.PropertyDirectory;
import seedu.condonery.testutil.ClientBuilder;
import seedu.condonery.testutil.EditClientDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditClientCommand.
 */
public class EditClientCommandTest {

    private final Model model = new ModelManager(getTypicalPropertyDirectory(),
            getTypicalClientDirectory(), new UserPrefs());
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Client editedClient = new ClientBuilder().build();
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient).build();
        EditClientCommand editCommand = new EditClientCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);
        Path userImageDirectory = model.getUserPrefs().getUserImageDirectoryPath();

        Model expectedModel = new ModelManager(
            new PropertyDirectory(model.getPropertyDirectory(), userImageDirectory),
            new ClientDirectory(model.getClientDirectory(), userImageDirectory),
            new UserPrefs()
        );
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
        Client lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());

        ClientBuilder personInList = new ClientBuilder(lastClient);
        Client editedPerson = personInList.withName(CommandTestUtil.CLIENT_VALID_NAME_BOB)
                .withTags(CommandTestUtil.CLIENT_VALID_TAG_HUSBAND).build();

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(CommandTestUtil.CLIENT_VALID_NAME_BOB)
                .withTags(CommandTestUtil.CLIENT_VALID_TAG_HUSBAND)
                .build();
        EditClientCommand editCommand = new EditClientCommand(indexLastClient, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedPerson);
        Path userImageDirectory = model.getUserPrefs().getUserImageDirectoryPath();

        Model expectedModel = new ModelManager(
            new PropertyDirectory(model.getPropertyDirectory(), userImageDirectory),
            new ClientDirectory(model.getClientDirectory(), userImageDirectory),
            new UserPrefs()
        );
        expectedModel.setClient(lastClient, editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditClientCommand editCommand = new EditClientCommand(INDEX_FIRST, new EditClientDescriptor());
        Client editedPerson = model.getFilteredClientList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedPerson);
        Path userImageDirectory = model.getUserPrefs().getUserImageDirectoryPath();

        Model expectedModel = new ModelManager(
            new PropertyDirectory(model.getPropertyDirectory(), model.getUserPrefs().getUserImageDirectoryPath()),
            new ClientDirectory(model.getClientDirectory(), userImageDirectory),
            new UserPrefs()
        );

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showPropertyAtIndex(model, INDEX_FIRST);

        Client personInFilteredList = model.getFilteredClientList().get(INDEX_FIRST.getZeroBased());
        Client editedPerson = new ClientBuilder(personInFilteredList)
                .withName(CommandTestUtil.CLIENT_VALID_NAME_BOB).build();
        EditClientCommand editCommand = new EditClientCommand(INDEX_FIRST,
                new EditClientDescriptorBuilder().withName(CommandTestUtil.CLIENT_VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedPerson);
        Path userImageDirectory = model.getUserPrefs().getUserImageDirectoryPath();

        Model expectedModel = new ModelManager(
            new PropertyDirectory(model.getPropertyDirectory(), model.getUserPrefs().getUserImageDirectoryPath()),
            new ClientDirectory(model.getClientDirectory(), userImageDirectory),
            new UserPrefs()
        );
        expectedModel.setClient(model.getFilteredClientList().get(0), editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Client firstClient = model.getFilteredClientList().get(INDEX_FIRST.getZeroBased());
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient).build();
        EditClientCommand editCommand = new EditClientCommand(INDEX_SECOND, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        CommandTestUtil.showPropertyAtIndex(model, INDEX_FIRST);

        // edit person in filtered list into a duplicate in address book
        Client clientInList = model.getClientDirectory().getClientList()
                .get(INDEX_SECOND.getZeroBased());
        EditClientCommand editCommand = new EditClientCommand(INDEX_FIRST,
                new EditClientDescriptorBuilder(clientInList).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPropertyList().size() + 1);
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(CommandTestUtil.CLIENT_VALID_NAME_BOB).build();
        EditClientCommand editCommand = new EditClientCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidClientIndexFilteredList_failure() {
        CommandTestUtil.showPropertyAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = Index.fromZeroBased(model.getFilteredClientList().size());
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getClientDirectory().getClientList().size() + 1);

        EditClientCommand editCommand = new EditClientCommand(outOfBoundIndex,
                new EditClientDescriptorBuilder().withName(CommandTestUtil.CLIENT_VALID_NAME_BOB).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditClientCommand standardCommand =
                new EditClientCommand(INDEX_FIRST, CLIENT_DESC_AMY);

        // same values -> returns true
        EditClientDescriptor copyDescriptor = new EditClientDescriptor(CLIENT_DESC_AMY);
        EditClientCommand commandWithSameValues =
                new EditClientCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.getEditClientDescriptor()
                .equals(commandWithSameValues.getEditClientDescriptor()));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearPropertyCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditClientCommand(INDEX_SECOND, CLIENT_DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditClientCommand(INDEX_FIRST, CLIENT_DESC_BOB)));
    }

}
