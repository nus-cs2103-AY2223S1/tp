package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.address.testutil.TypicalClients.getTypicalMyInsuRec;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ELEMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ELEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditClientDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.MyInsuRec;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.EditClientDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalMyInsuRec(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Client editedClient = new ClientBuilder().build();
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ELEMENT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new MyInsuRec(model.getMyInsuRec()), new UserPrefs());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
        Client lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());

        ClientBuilder clientInList = new ClientBuilder(lastClient);
        Client editedClient = clientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastClient, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new MyInsuRec(model.getMyInsuRec()), new UserPrefs());
        expectedModel.setClient(lastClient, editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ELEMENT, new EditClientDescriptor());
        Client editedClient = model.getFilteredClientList().get(INDEX_FIRST_ELEMENT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new MyInsuRec(model.getMyInsuRec()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_ELEMENT);

        Client clientInFilteredList = model.getFilteredClientList().get(INDEX_FIRST_ELEMENT.getZeroBased());
        Client editedClient = new ClientBuilder(clientInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ELEMENT,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new MyInsuRec(model.getMyInsuRec()), new UserPrefs());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateClientUnfilteredList_failure() {
        Client firstClient = model.getFilteredClientList().get(INDEX_FIRST_ELEMENT.getZeroBased());
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ELEMENT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_duplicateClientFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_ELEMENT);

        // edit client in filtered list into a duplicate in MyInsuRec
        Client clientInList = model.getMyInsuRec().getClientList().get(INDEX_SECOND_ELEMENT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ELEMENT,
                new EditClientDescriptorBuilder(clientInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of MyInsuRec
     */
    @Test
    public void execute_invalidClientIndexFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_ELEMENT);
        Index outOfBoundIndex = INDEX_SECOND_ELEMENT;
        // ensures that outOfBoundIndex is still in bounds of client list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMyInsuRec().getClientList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ELEMENT, DESC_AMY);

        // same values -> returns true
        EditClientDescriptor copyDescriptor = new EditClientDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ELEMENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_ELEMENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_ELEMENT, DESC_BOB)));
    }

}
