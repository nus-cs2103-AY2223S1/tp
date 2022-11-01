package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.Messages;
import taskbook.commons.core.index.Index;
import taskbook.logic.commands.contacts.ContactEditCommand;
import taskbook.model.Model;
import taskbook.model.ModelManager;
import taskbook.model.TaskBook;
import taskbook.model.UserPrefs;
import taskbook.model.person.Person;
import taskbook.testutil.EditPersonDescriptorBuilder;
import taskbook.testutil.PersonBuilder;
import taskbook.testutil.TypicalIndexes;
import taskbook.testutil.TypicalTaskBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class ContactEditCommandTest {

    private final Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        ContactEditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        ContactEditCommand editCommand = new ContactEditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(ContactEditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new TaskBook(model.getTaskBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(CommandTestUtil.VALID_NAME_BOB)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();

        ContactEditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOB)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        ContactEditCommand editCommand = new ContactEditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(ContactEditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new TaskBook(model.getTaskBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        ContactEditCommand editCommand = new ContactEditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new ContactEditCommand.EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(ContactEditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new TaskBook(model.getTaskBook()), new UserPrefs());

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(CommandTestUtil.VALID_NAME_BOB).build();
        ContactEditCommand editCommand = new ContactEditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(ContactEditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new TaskBook(model.getTaskBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        ContactEditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        ContactEditCommand editCommand = new ContactEditCommand(TypicalIndexes.INDEX_SECOND_PERSON, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, ContactEditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in task book
        Person personInList = model.getTaskBook()
                .getPersonList().get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased());
        ContactEditCommand editCommand = new ContactEditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(personInList).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, ContactEditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ContactEditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOB).build();
        ContactEditCommand editCommand = new ContactEditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of task book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of task book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskBook().getPersonList().size());

        ContactEditCommand editCommand = new ContactEditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final ContactEditCommand standardCommand = new ContactEditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                CommandTestUtil.DESC_AMY);

        // same values -> returns true
        ContactEditCommand.EditPersonDescriptor copyDescriptor =
                new ContactEditCommand.EditPersonDescriptor(CommandTestUtil.DESC_AMY);
        ContactEditCommand
            commandWithSameValues = new ContactEditCommand(TypicalIndexes.INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ContactEditCommand(TypicalIndexes.INDEX_SECOND_PERSON,
                CommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new ContactEditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                CommandTestUtil.DESC_BOB)));
    }

}
