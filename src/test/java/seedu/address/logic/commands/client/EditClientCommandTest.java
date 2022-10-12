//package seedu.address.logic.commands.client;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
//import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.core.Messages;
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.ClearCommand;
//import seedu.address.logic.commands.client.EditClientCommand.EditPersonDescriptor;
//import seedu.address.model.AddressBook;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.client.Person;
//import seedu.address.testutil.EditPersonDescriptorBuilder;
//import seedu.address.testutil.PersonBuilder;
//import seedu.address.ui.StubUiManager;
//import seedu.address.ui.Ui;
//
///**
// * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
// */
//public class EditClientCommandTest {
//
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//
//    @Test
//    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        Person editedPerson = new PersonBuilder().build();
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
//        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_PERSON, descriptor);
//
//        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
//        Ui stubUi = new StubUiManager();
//
//        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel, stubUi);
//    }
//
//    @Test
//    public void execute_someFieldsSpecifiedUnfilteredList_success() {
//        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
//        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
//
//        PersonBuilder personInList = new PersonBuilder(lastPerson);
//        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
//                .withTags(VALID_TAG_HUSBAND).build();
//
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
//                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
//        EditClientCommand editClientCommand = new EditClientCommand(indexLastPerson, descriptor);
//
//        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setPerson(lastPerson, editedPerson);
//        Ui stubUi = new StubUiManager();
//
//        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel, stubUi);
//    }
//
//    @Test
//    public void execute_noFieldSpecifiedUnfilteredList_success() {
//        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
//        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//
//        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        Ui stubUi = new StubUiManager();
//
//        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel, stubUi);
//    }
//
//    @Test
//    public void execute_filteredList_success() {
//        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//
//        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
//        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_PERSON,
//                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
//
//        Ui stubUi = new StubUiManager();
//        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel, stubUi);
//    }
//
//    @Test
//    public void execute_duplicatePersonUnfilteredList_failure() {
//        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
//
//        EditClientCommand editClientCommand = new EditClientCommand(INDEX_SECOND_PERSON, descriptor);
//        Ui stubUi = new StubUiManager();
//
//        assertCommandFailure(editClientCommand, model, EditClientCommand.MESSAGE_DUPLICATE_PERSON, stubUi);
//    }
//
//    @Test
//    public void execute_duplicatePersonFilteredList_failure() {
//        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//
//        // edit client in filtered list into a duplicate in address book
//        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
//        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_PERSON,
//                new EditPersonDescriptorBuilder(personInList).build());
//        Ui stubUi = new StubUiManager();
//
//        assertCommandFailure(editClientCommand, model, EditClientCommand.MESSAGE_DUPLICATE_PERSON, stubUi);
//    }
//
//    @Test
//    public void execute_invalidPersonIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
//
//        EditClientCommand editClientCommand = new EditClientCommand(outOfBoundIndex, descriptor);
//        Ui stubUi = new StubUiManager();
//
//        assertCommandFailure(editClientCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, stubUi);
//    }
//
//    /**
//     * Edit filtered list where index is larger than size of filtered list,
//     * but smaller than size of address book
//     */
//    @Test
//    public void execute_invalidPersonIndexFilteredList_failure() {
//        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//        Index outOfBoundIndex = INDEX_SECOND_PERSON;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
//
//        EditClientCommand editClientCommand = new EditClientCommand(outOfBoundIndex,
//                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        Ui stubUi = new StubUiManager();
//        assertCommandFailure(editClientCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, stubUi);
//    }
//
//    @Test
//    public void equals() {
//        final EditClientCommand standardCommand = new EditClientCommand(INDEX_FIRST_PERSON, DESC_AMY);
//
//        // same values -> returns true
//        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
//        EditClientCommand commandWithSameValues = new EditClientCommand(INDEX_FIRST_PERSON, copyDescriptor);
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
//        assertFalse(standardCommand.equals(new EditClientCommand(INDEX_SECOND_PERSON, DESC_AMY)));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new EditClientCommand(INDEX_FIRST_PERSON, DESC_BOB)));
//    }
//
//}
