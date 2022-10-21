package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILEPATH_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILEPATH_BOB;
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
import seedu.address.model.person.FilePath;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class SetPersonFileCommandTest {
    private static final String FILEPATH_STUB = "src/test/data/TestPDFs/Test_PDF2.pdf";
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_editFilePathUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withFilePath(FILEPATH_STUB).build();

        SetPersonFileCommand setPersonFileCommand =
                new SetPersonFileCommand(INDEX_FIRST_PERSON, new FilePath(editedPerson.getFilePath().value));

        String expectedMessage = String.format(SetPersonFileCommand.MESSAGE_CHANGE_FILEPATH_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(setPersonFileCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_setToPlaceholderFilePathUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withFilePath("").build();

        SetPersonFileCommand setPersonFileCommand =
                new SetPersonFileCommand(INDEX_FIRST_PERSON, new FilePath(editedPerson.getFilePath().value));

        String expectedMessage = String.format(SetPersonFileCommand.MESSAGE_DELETE_FILEPATH_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(setPersonFileCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withFilePath(FILEPATH_STUB).build();

        SetPersonFileCommand setPersonFileCommand =
                new SetPersonFileCommand(INDEX_FIRST_PERSON, new FilePath(editedPerson.getFilePath().value));

        String expectedMessage = String.format(SetPersonFileCommand.MESSAGE_CHANGE_FILEPATH_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(setPersonFileCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        SetPersonFileCommand setPersonFileCommand =
                new SetPersonFileCommand(outOfBoundIndex, new FilePath(VALID_FILEPATH_BOB));

        assertCommandFailure(setPersonFileCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        SetPersonFileCommand setPersonFileCommand =
                new SetPersonFileCommand(outOfBoundIndex, new FilePath(VALID_FILEPATH_BOB));

        assertCommandFailure(setPersonFileCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final SetPersonFileCommand standardCommand = new SetPersonFileCommand(INDEX_FIRST_PERSON,
                new FilePath(VALID_FILEPATH_AMY));
        // same values -> returns true
        SetPersonFileCommand commandWithSameValues = new SetPersonFileCommand(INDEX_FIRST_PERSON,
                new FilePath(VALID_FILEPATH_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new SetPersonFileCommand(INDEX_SECOND_PERSON,
                new FilePath(VALID_FILEPATH_AMY))));
        // different filepath -> returns false
        assertFalse(standardCommand.equals(new SetPersonFileCommand(INDEX_FIRST_PERSON,
                new FilePath(VALID_FILEPATH_BOB))));
    }
}
