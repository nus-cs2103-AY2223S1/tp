//package seedu.address.logic.commands;
//
//import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
//
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//
///**
// * Contains integration tests (interaction with the Model) and unit tests for
// * {@code MarkCommand}.
// */
//class MarkCommandTest {
//
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//    /*
//    @Test
//    public void execute_validIndexUnfilteredList_success() {
//        Task taskToMark = model.getFilteredTaskList().get(INDEX_FIRST_PERSON.getZeroBased());
//        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON);
//
//        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_TASK_SUCCESS, taskToMark);
//
//        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        Task editedTaskToMark = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        editedTaskToMark.mark();
//        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
//        MarkCommand markCommand = new MarkCommand(outOfBoundIndex);
//
//        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void execute_validIndexFilteredList_success() {
//        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//
//        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON);
//
//        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_TASK_SUCCESS, personToMark);
//
//        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
//        Person editedPersonToMark = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        editedPersonToMark.mark();
//
//        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_invalidIndexFilteredList_throwsCommandException() {
//        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//
//        Index outOfBoundIndex = INDEX_SECOND_PERSON;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
//
//        MarkCommand markCommand = new MarkCommand(outOfBoundIndex);
//
//        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        MarkCommand markFirstCommand = new MarkCommand(INDEX_FIRST_PERSON);
//        MarkCommand markSecondCommand = new MarkCommand(INDEX_SECOND_PERSON);
//
//        // same object -> returns true
//        assertTrue(markFirstCommand.equals(markFirstCommand));
//
//        // same values -> returns true
//        MarkCommand markFirstCommandCopy = new MarkCommand(INDEX_FIRST_PERSON);
//        assertTrue(markFirstCommand.equals(markFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(markFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(markFirstCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(markFirstCommand.equals(markSecondCommand));
//    }
//
//
//     */
//}
