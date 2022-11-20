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
import static seedu.address.model.person.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.model.person.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.model.person.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FullNamePredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.testutil.EditPersonDescriptorBuilder;
import seedu.address.model.person.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPersonCommand.
 */
public class EditPersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        FullNamePredicate predicate = new FullNamePredicate(model.getFilteredPersonList().get(0)
                        .getName().toString());
        EditPersonCommand editPersonCommand = new EditPersonCommand(predicate, descriptor);

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
        FullNamePredicate predicate = new FullNamePredicate(lastPerson.getName().toString());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(predicate, descriptor);

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FullNamePredicate predicate = new FullNamePredicate(personToEdit.getName().toString());
        EditPersonCommand editPersonCommand = new EditPersonCommand(predicate, new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Person personInFilteredList = model.getFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        FullNamePredicate predicate = new FullNamePredicate(personInFilteredList.getName().toString());
        EditPersonCommand editPersonCommand = new EditPersonCommand(predicate,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        FullNamePredicate predicate = new FullNamePredicate(secondPerson.getName().toString());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(predicate, descriptor);

        assertCommandFailure(editPersonCommand, model, EditPersonCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonNameUnfilteredList_failure() throws CommandException {
        FullNamePredicate fullNamePredicate = new FullNamePredicate("abc");
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(fullNamePredicate, descriptor);

        assertCommandFailure(editPersonCommand, model, EditPersonCommand.MESSAGE_INVALID_PERSON_NAME);
    }


    @Test
    public void equals() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        final FullNamePredicate fullNamePredicate = new FullNamePredicate(firstPerson.getName().toString());
        final EditPersonCommand standardCommand = new EditPersonCommand(fullNamePredicate, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditPersonCommand commandWithSameValues = new EditPersonCommand(fullNamePredicate, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different predicate -> returns false
        assertFalse(standardCommand.equals(new EditPersonCommand(new FullNamePredicate(secondPerson
                .getName().toString()), DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPersonCommand(fullNamePredicate, DESC_BOB)));
    }

}
