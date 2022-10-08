package tracko.logic.commands;

//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static tracko.logic.commands.CommandTestUtil.assertCommandFailure;
//import static tracko.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static tracko.logic.commands.CommandTestUtil.showOrderAtIndex;
//import static tracko.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
//import static tracko.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
//import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;
//
//import org.junit.jupiter.api.Test;
//
//import tracko.commons.core.Messages;
//import tracko.commons.core.index.Index;
//import tracko.logic.commands.order.DeleteOrderCommand;
//import tracko.model.Model;
//import tracko.model.ModelManager;
//import tracko.model.UserPrefs;
//import tracko.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

//    private Model model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());
//
//    @Test
//    public void execute_validIndexUnfilteredList_success() {
//        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(INDEX_FIRST_PERSON);
//
//        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);
//
//        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
//        expectedModel.deletePerson(personToDelete);
//
//        assertCommandSuccess(deleteOrderCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
//        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);
//
//        assertCommandFailure(deleteOrderCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void execute_validIndexFilteredList_success() {
//        showOrderAtIndex(model, INDEX_FIRST_PERSON);
//
//        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(INDEX_FIRST_PERSON);
//
//        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);
//
//        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
//        expectedModel.deletePerson(personToDelete);
//        showNoPerson(expectedModel);
//
//        assertCommandSuccess(deleteOrderCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_invalidIndexFilteredList_throwsCommandException() {
//        showOrderAtIndex(model, INDEX_FIRST_PERSON);
//
//        Index outOfBoundIndex = INDEX_SECOND_PERSON;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
//
//        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);
//
//        assertCommandFailure(deleteOrderCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        DeleteOrderCommand deleteFirstCommand = new DeleteOrderCommand(INDEX_FIRST_PERSON);
//        DeleteOrderCommand deleteSecondCommand = new DeleteOrderCommand(INDEX_SECOND_PERSON);
//
//        // same object -> returns true
//        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));
//
//        // same values -> returns true
//        DeleteOrderCommand deleteFirstCommandCopy = new DeleteOrderCommand(INDEX_FIRST_PERSON);
//        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(deleteFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(deleteFirstCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
//    }
//
//    /**
//     * Updates {@code model}'s filtered list to show no one.
//     */
//    private void showNoPerson(Model model) {
//        model.updateFilteredPersonList(p -> false);
//
//        assertTrue(model.getFilteredPersonList().isEmpty());
//    }
}
