package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSION_BOB;
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
import seedu.address.model.person.Session;
import seedu.address.testutil.PersonBuilder;

class SessionCommandTest {

    private static final String SESSION_STUB = "Mon 08:00";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addSessionUnfilteredList_success() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson).withSession(SESSION_STUB).build();
        secondPerson.clearSessionList();

        SessionCommand sessionCommand = new SessionCommand(INDEX_SECOND_PERSON,
                new Session(SESSION_STUB));

        String expectedMessage = String.format(SessionCommand.MESSAGE_ADD_SESSION_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(sessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addSessionFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withSession(SESSION_STUB).build();
        personInFilteredList.clearSessionList();

        SessionCommand sessionCommand = new SessionCommand(INDEX_FIRST_PERSON,
                new Session(SESSION_STUB));

        String expectedMessage = String.format(SessionCommand.MESSAGE_ADD_SESSION_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(sessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withSession(SESSION_STUB).build();
        firstPerson.clearSessionList();

        SessionCommand sessionCommand = new SessionCommand(
                INDEX_FIRST_PERSON, new Session(SESSION_STUB));

        String expectedMessage = String.format(SessionCommand.MESSAGE_ADD_SESSION_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(sessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        SessionCommand sessionCommand = new SessionCommand(
                outOfBoundIndex, new Session(SESSION_STUB));

        assertCommandFailure(sessionCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        SessionCommand sessionCommand = new SessionCommand(
                outOfBoundIndex, new Session(SESSION_STUB));
        assertCommandFailure(sessionCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final SessionCommand standardCommand = new SessionCommand(INDEX_FIRST_PERSON,
                new Session(VALID_SESSION_AMY));
        // same values -> returns true
        SessionCommand commandWithSameValues = new SessionCommand(INDEX_FIRST_PERSON,
                new Session(VALID_SESSION_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new SessionCommand(INDEX_SECOND_PERSON,
                new Session(VALID_SESSION_AMY))));
        // different remark -> returns false
        assertFalse(standardCommand.equals(new SessionCommand(INDEX_FIRST_PERSON,
                new Session(VALID_SESSION_BOB))));
    }
}
