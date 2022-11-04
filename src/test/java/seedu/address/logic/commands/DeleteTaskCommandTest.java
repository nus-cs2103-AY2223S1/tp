//package seedu.address.logic.commands;
//
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//
///**
// * Contains integration tests (interaction with the Model) and unit tests for
// * {@code DeleteTaskCommand}.
// */
//public class DeleteTaskCommandTest {
//
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//
//    /*
//      @Test
//    public void execute_validIndexUnfilteredList_success() {
//
//        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_PERSON.getZeroBased());
//        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_PERSON);
//
//        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);
//
//        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
//        expectedModel.deleteTask(taskToDelete);
//
//        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
//        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);
//
//        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void execute_validIndexFilteredList_success() {
//        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//
//        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
//
//        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);
//
//        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
//        expectedModel.deletePerson(personToDelete);
//        showNoPerson(expectedModel);
//
//        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_invalidIndexFilteredList_throwsCommandException() {
//        showTaskAtIndex(model, INDEX_FIRST_TASK);
//
//        Index outOfBoundIndex = INDEX_SECOND_TASK;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());
//
//        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);
//
//        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        DeleteTaskCommand deleteTaskFirstCommand = new DeleteTaskCommand(INDEX_FIRST_TASK);
//        DeleteTaskCommand deleteTaskSecondCommand = new DeleteTaskCommand(INDEX_SECOND_TASK);
//
//        // same object -> returns true
//        assertTrue(deleteTaskFirstCommand.equals(deleteTaskFirstCommand));
//
//        // same values -> returns true
//        DeleteTaskCommand deleteTaskFirstCommandCopy = new DeleteTaskCommand(INDEX_FIRST_TASK);
//        assertTrue(deleteTaskFirstCommand.equals(deleteTaskFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(deleteTaskFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(deleteTaskFirstCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(deleteTaskFirstCommand.equals(deleteTaskSecondCommand));
//    }
//    */
//
//    /*
//    private void showNoPerson(Model model) {
//        model.updateFilteredTaskList(p -> false);
//
//        assertTrue(model.getFilteredTaskList().isEmpty());
//    }
//    */
//
//}
