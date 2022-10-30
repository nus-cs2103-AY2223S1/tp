package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.C_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.C_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CloneCommand.ClonePersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.ClonePersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CloneCommand.
 */
public class CloneCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person clonedPerson = new PersonBuilder().build();
        ClonePersonDescriptor descriptor = new ClonePersonDescriptorBuilder(clonedPerson).build();
        CloneCommand cloneCommand = new CloneCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(CloneCommand.MESSAGE_CLONE_PERSON_SUCCESS, clonedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addPerson(clonedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(cloneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person clonedPerson = personInList.withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withTags(VALID_TAG_FRIEND).build();

        ClonePersonDescriptor descriptor = new ClonePersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withTags(VALID_TAG_FRIEND).build();
        CloneCommand cloneCommand = new CloneCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(CloneCommand.MESSAGE_CLONE_PERSON_SUCCESS, clonedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addPerson(clonedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(cloneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person clonedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_AMY).build();
        CloneCommand cloneCommand = new CloneCommand(INDEX_FIRST_PERSON,
                new ClonePersonDescriptorBuilder().withName(VALID_NAME_AMY).build());

        String expectedMessage = String.format(CloneCommand.MESSAGE_CLONE_PERSON_SUCCESS, clonedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addPerson(clonedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(cloneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ClonePersonDescriptor descriptor = new ClonePersonDescriptorBuilder(firstPerson).build();
        CloneCommand cloneCommand = new CloneCommand(INDEX_FIRST_PERSON, descriptor);

        assertCommandFailure(cloneCommand, model, CloneCommand.MESSAGE_DUPLICATE_CLONED_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // clone person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        CloneCommand cloneCommand = new CloneCommand(INDEX_FIRST_PERSON,
                new ClonePersonDescriptorBuilder(personInList).build());

        assertCommandFailure(cloneCommand, model, CloneCommand.MESSAGE_DUPLICATE_CLONED_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ClonePersonDescriptor descriptor = new ClonePersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        CloneCommand cloneCommand = new CloneCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(cloneCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        CloneCommand cloneCommand = new CloneCommand(outOfBoundIndex,
                new ClonePersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(cloneCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final CloneCommand standardCommand = new CloneCommand(INDEX_FIRST_PERSON, C_DESC_AMY);

        // same values -> returns true
        ClonePersonDescriptor copyDescriptor = new ClonePersonDescriptor(C_DESC_AMY);
        CloneCommand commandWithSameValues = new CloneCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new CloneCommand(INDEX_SECOND_PERSON, C_DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new CloneCommand(INDEX_FIRST_PERSON, C_DESC_BOB)));
    }

}
