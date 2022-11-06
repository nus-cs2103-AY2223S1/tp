package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.DeleteCommand.MESSAGE_USAGE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.FunctionalInterfaces.Changer;
import seedu.address.commons.util.FunctionalInterfaces.Getter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CmdBuilder;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonOutOfBoundException;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteCommand}.
 */
public class DeleteCommandTest {
    private static final Getter<Person> P_GETTER = (m, i) -> m.getFromFilteredPerson(i);
    private static final Changer<Person> P_DELETER = (m, item) -> m.deletePerson(item);
    private static final java.util.function.Predicate<Object> P_TESTER = o -> o instanceof Person;

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeleteCommand<Person> deleteCommand = CmdBuilder.makeDelPerson(INDEX_FIRST);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand<Person> deleteCommand = CmdBuilder.makeDelPerson(outOfBoundIndex);

        assertCommandFailure(deleteCommand,
            model,
            String.format(PersonOutOfBoundException.ERR_MSG, model.getFilteredPersonList().size(),
                outOfBoundIndex.getOneBased()));
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeleteCommand<Person> deleteCommand = CmdBuilder.makeDelPerson(INDEX_FIRST);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand<Person> deleteCommand = CmdBuilder.makeDelPerson(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE,
                //String.format(PersonOutOfBoundException.ERR_MSG,
                model.getFilteredPersonList().size(), outOfBoundIndex.getOneBased()));
    }

    @Test
    public void equals() throws CommandException {
        DeleteCommand<Person> deleteFirstCommand = CmdBuilder.makeDelPerson(INDEX_FIRST);
        DeleteCommand<Person> deleteSecondCommand = CmdBuilder.makeDelPerson(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand<Person> deleteFirstCommandCopy = CmdBuilder.makeDelPerson(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        deleteFirstCommand.setInput(P_GETTER.apply(model, INDEX_FIRST));
        deleteFirstCommandCopy.setInput(P_GETTER.apply(model, INDEX_FIRST));

        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));
    }

    @Test
    public void setInput_test() throws seedu.address.logic.commands.exceptions.CommandException {
        DisplayItem dataStub = model.getFromFilteredPerson(Index.fromZeroBased(1));

        DeleteCommand delCommandStub = new DeleteCommand(Index.fromZeroBased(1), P_GETTER, P_DELETER, P_TESTER);

        delCommandStub.setInput(dataStub);

        assertTrue(delCommandStub.equals(delCommandStub));
    }
    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }


}
