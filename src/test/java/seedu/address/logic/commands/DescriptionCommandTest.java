package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Description;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DescriptionCommand.
 */
public class DescriptionCommandTest {

    private static final String DESCRIPTION_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addDescriptionUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withDescription(DESCRIPTION_STUB).build();

        DescriptionCommand descriptionCommand = new DescriptionCommand(INDEX_FIRST_PERSON, new Description(editedPerson.getDescription().value));

        String expectedMessage = String.format(DescriptionCommand.MESSAGE_ADD_DESCRIPTION_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(descriptionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteDescriptionUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withDescription("").build();

        DescriptionCommand descriptionCommand = new DescriptionCommand(INDEX_FIRST_PERSON,
                new Description(editedPerson.getDescription().toString()));

        String expectedMessage = String.format(DescriptionCommand.MESSAGE_DELETE_DESCRIPTION_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(descriptionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withDescription(DESCRIPTION_STUB).build();

        DescriptionCommand descriptionCommand = new DescriptionCommand(INDEX_FIRST_PERSON, new Description(editedPerson.getDescription().value));

        String expectedMessage = String.format(DescriptionCommand.MESSAGE_ADD_DESCRIPTION_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(descriptionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DescriptionCommand descriptionCommand = new DescriptionCommand(outOfBoundIndex, new Description(VALID_DESCRIPTION_BOB));

        assertCommandFailure(descriptionCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DescriptionCommand descriptionCommand = new DescriptionCommand(outOfBoundIndex, new Description(VALID_DESCRIPTION_BOB));

        assertCommandFailure(descriptionCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DescriptionCommand standardCommand = new DescriptionCommand(INDEX_FIRST_PERSON,
                new Description(VALID_DESCRIPTION_AMY));
        // same values -> returns true
        DescriptionCommand commandWithSameValues = new DescriptionCommand(INDEX_FIRST_PERSON,
                new Description(VALID_DESCRIPTION_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new DescriptionCommand(INDEX_SECOND_PERSON,
                new Description(VALID_DESCRIPTION_AMY))));
        // different remark -> returns false
        assertFalse(standardCommand.equals(new DescriptionCommand(INDEX_FIRST_PERSON,
                new Description(VALID_DESCRIPTION_BOB))));
    }
}
