//package seedu.address.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
//import static seedu.address.testutil.TypicalClients.getTypicalJeeqTracker;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.core.Messages;
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.EditTransactionCommand.EditTransactionDescriptor;
//import seedu.address.model.JeeqTracker;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.client.Client;
//import seedu.address.model.transaction.Transaction;
//import seedu.address.testutil.EditTransactionDescriptorBuilder;
//import seedu.address.testutil.TransactionBuilder;
//
///**
// * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
// */
//public class EditTransactionCommandTest {
//
//    private Model model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());
//
//    @Test
//    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        Transaction editedTransaction = new TransactionBuilder().build();
//        EditTransactionCommand.EditTransactionDescriptor descriptor =
//        new EditTransactionDescriptorBuilder(editedTransaction).build();
//        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(INDEX_FIRST_CLIENT, descriptor);
//
//        String expectedMessage =
//        String.format(EditTransactionCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedTransaction);
//
//        Model expectedModel = new ModelManager(new JeeqTracker(model.getJeeqTracker()), new UserPrefs());
//        Client expectedClient = model.getFilteredClientList().get(0);
//        expectedClient.getTransactions().setTransaction(INDEX_FIRST_CLIENT.getOneBased(), editedTransaction);
//        expectedModel.setClient(expectedClient, expectedClient);
//
//        assertCommandSuccess(editTransactionCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_someFieldsSpecifiedUnfilteredList_success() {
//        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
//        Transaction lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());
//
//        ClientBuilder clientInList = new ClientBuilder(lastClient);
//        Transaction editedTransaction = clientInList.withName(VALID_NAME_BOB)
//                .withTags(VALID_TAG_HUSBAND).build();
//
//        EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder().withName(VALID_NAME_BOB)
//                .withTags(VALID_TAG_HUSBAND).build();
//        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(indexLastClient, descriptor);
//
//        String expectedMessage = String.format(EditTransactionCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedTransaction);
//
//        Model expectedModel = new ModelManager(new JeeqTracker(model.getJeeqTracker()), new UserPrefs());
//        expectedModel.setClient(lastClient, editedTransaction);
//
//        assertCommandSuccess(editTransactionCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_noFieldSpecifiedUnfilteredList_success() {
//        EditTransactionCommand editTransactionCommand =
//        new EditTransactionCommand(INDEX_FIRST_CLIENT, new EditTransactionDescriptor());
//        Transaction editedTransaction = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
//
//        String expectedMessage = String.format(EditTransactionCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedTransaction);
//
//        Model expectedModel = new ModelManager(new JeeqTracker(model.getJeeqTracker()), new UserPrefs());
//
//        assertCommandSuccess(editTransactionCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_filteredList_success() {
//        showClientAtIndex(model, INDEX_FIRST_CLIENT);
//
//        Transaction clientInFilteredList = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
//        Transaction editedTransaction = new ClientBuilder(clientInFilteredList).withName(VALID_NAME_BOB).build();
//        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(INDEX_FIRST_CLIENT,
//                new EditTransactionDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        String expectedMessage = String.format(EditTransactionCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedTransaction);
//
//        Model expectedModel = new ModelManager(new JeeqTracker(model.getJeeqTracker()), new UserPrefs());
//        expectedModel.setClient(model.getFilteredClientList().get(0), editedTransaction);
//
//        assertCommandSuccess(editTransactionCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_duplicateClientUnfilteredList_failure() {
//        Transaction firstClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
//        EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder(firstClient).build();
//        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(INDEX_SECOND_CLIENT, descriptor);
//
//        assertCommandFailure(editTransactionCommand, model, EditTransactionCommand.MESSAGE_DUPLICATE_CLIENT);
//    }
//
//    @Test
//    public void execute_duplicateClientFilteredList_failure() {
//        showClientAtIndex(model, INDEX_FIRST_CLIENT);
//
//        // edit client in filtered list into a duplicate in address book
//        Transaction clientInList = model.getJeeqTracker().getClientList().get(INDEX_SECOND_CLIENT.getZeroBased());
//        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(INDEX_FIRST_CLIENT,
//                new EditTransactionDescriptorBuilder(clientInList).build());
//
//        assertCommandFailure(editTransactionCommand, model, EditTransactionCommand.MESSAGE_DUPLICATE_CLIENT);
//    }
//
//    @Test
//    public void execute_invalidClientIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
//        EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder().withName(VALID_NAME_BOB).build();
//        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(outOfBoundIndex, descriptor);
//
//        assertCommandFailure(editTransactionCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
//    }
//
//    /**
//     * Edit filtered list where index is larger than size of filtered list,
//     * but smaller than size of address book
//     */
//    @Test
//    public void execute_invalidClientIndexFilteredList_failure() {
//        showClientAtIndex(model, INDEX_FIRST_CLIENT);
//        Index outOfBoundIndex = INDEX_SECOND_CLIENT;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getJeeqTracker().getClientList().size());
//
//        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(outOfBoundIndex,
//                new EditTransactionDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        assertCommandFailure(editTransactionCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        final EditTransactionCommand standardCommand = new EditTransactionCommand(INDEX_FIRST_CLIENT, DESC_AMY);
//
//        // same values -> returns true
//        EditTransactionDescriptor copyDescriptor = new EditTransactionDescriptor(DESC_AMY);
//        EditTransactionCommand commandWithSameValues = new EditTransactionCommand(INDEX_FIRST_CLIENT, copyDescriptor);
//        assertTrue(standardCommand.equals(commandWithSameValues));
//
//        // same object -> returns true
//        assertTrue(standardCommand.equals(standardCommand));
//
//        // null -> returns false
//        assertFalse(standardCommand.equals(null));
//
//        // different types -> returns false
//        assertFalse(standardCommand.equals(new ClearCommand()));
//
//        // different index -> returns false
//        assertFalse(standardCommand.equals(new EditTransactionCommand(INDEX_SECOND_CLIENT, DESC_AMY)));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new EditTransactionCommand(INDEX_FIRST_CLIENT, DESC_BOB)));
//    }
//
//}
