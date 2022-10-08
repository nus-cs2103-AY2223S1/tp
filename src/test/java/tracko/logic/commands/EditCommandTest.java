package tracko.logic.commands;

//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static tracko.logic.commands.CommandTestUtil.DESC_AMY;
//import static tracko.logic.commands.CommandTestUtil.DESC_BOB;
//import static tracko.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static tracko.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
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
//import tracko.logic.commands.order.EditOrderCommand;
//import tracko.logic.commands.order.EditOrderCommand.EditPersonDescriptor;
//import tracko.model.Model;
//import tracko.model.ModelManager;
//import tracko.model.UserPrefs;
//import tracko.model.person.Person;
//import tracko.testutil.EditPersonDescriptorBuilder;
//import tracko.testutil.OrderBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

//    private Model model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());
//
//    @Test
//    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        Person editedPerson = new OrderBuilder().build();
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
//        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_PERSON, descriptor);
//
//        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
//
//        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_someFieldsSpecifiedUnfilteredList_success() {
//        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
//        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
//
//        OrderBuilder personInList = new OrderBuilder(lastPerson);
//        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
//                .withTags(VALID_TAG_HUSBAND).build();
//
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
//                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
//        EditOrderCommand editOrderCommand = new EditOrderCommand(indexLastPerson, descriptor);
//
//        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setPerson(lastPerson, editedPerson);
//
//        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_noFieldSpecifiedUnfilteredList_success() {
//        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
//        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//
//        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//
//        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_filteredList_success() {
//        showOrderAtIndex(model, INDEX_FIRST_PERSON);
//
//        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        Person editedPerson = new OrderBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
//        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_PERSON,
//                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
//
//        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_duplicatePersonUnfilteredList_failure() {
//        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
//        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_SECOND_PERSON, descriptor);
//
//        assertCommandFailure(editOrderCommand, model, EditOrderCommand.MESSAGE_DUPLICATE_PERSON);
//    }
//
//    @Test
//    public void execute_duplicatePersonFilteredList_failure() {
//        showOrderAtIndex(model, INDEX_FIRST_PERSON);
//
//        // edit person in filtered list into a duplicate in address book
//        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
//        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_PERSON,
//                new EditPersonDescriptorBuilder(personInList).build());
//
//        assertCommandFailure(editOrderCommand, model, EditOrderCommand.MESSAGE_DUPLICATE_PERSON);
//    }
//
//    @Test
//    public void execute_invalidPersonIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
//        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex, descriptor);
//
//        assertCommandFailure(editOrderCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }
//
//    /**
//     * Edit filtered list where index is larger than size of filtered list,
//     * but smaller than size of address book
//     */
//    @Test
//    public void execute_invalidPersonIndexFilteredList_failure() {
//        showOrderAtIndex(model, INDEX_FIRST_PERSON);
//        Index outOfBoundIndex = INDEX_SECOND_PERSON;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
//
//        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex,
//                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        assertCommandFailure(editOrderCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        final EditOrderCommand standardCommand = new EditOrderCommand(INDEX_FIRST_PERSON, DESC_AMY);
//
//        // same values -> returns true
//        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
//        EditOrderCommand commandWithSameValues = new EditOrderCommand(INDEX_FIRST_PERSON, copyDescriptor);
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
//        assertFalse(standardCommand.equals(new EditOrderCommand(INDEX_SECOND_PERSON, DESC_AMY)));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new EditOrderCommand(INDEX_FIRST_PERSON, DESC_BOB)));
//    }

}
