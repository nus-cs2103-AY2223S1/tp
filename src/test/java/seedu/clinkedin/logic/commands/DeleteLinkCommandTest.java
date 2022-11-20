package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinkedin.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.clinkedin.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.clinkedin.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.Messages;
import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.commons.exceptions.DataConversionException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.testutil.PersonBuilder;

public class DeleteLinkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteLinkCommand(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws DataConversionException {
        DeleteLinkCommand deleteLinkCommand = new DeleteLinkCommand(INDEX_FIRST_PERSON);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withLinks().build();

        String expectedMessage = String.format(DeleteLinkCommand.MESSAGE_DELETE_LINK_SUCCESS, editedPerson);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPerson);

        assertCommandSuccess(deleteLinkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteLinkCommand deleteLinkCommand = new DeleteLinkCommand(outOfBoundIndex);

        assertCommandFailure(deleteLinkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws DataConversionException {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        DeleteLinkCommand deleteLinkCommand = new DeleteLinkCommand(INDEX_FIRST_PERSON);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withLinks().build();

        String expectedMessage = String.format(DeleteLinkCommand.MESSAGE_DELETE_LINK_SUCCESS, editedPerson);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPerson);

        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(deleteLinkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of clinkedin book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteLinkCommand deleteLinkCommand = new DeleteLinkCommand(outOfBoundIndex);

        assertCommandFailure(deleteLinkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals_sameObject() {
        DeleteLinkCommand command1 = new DeleteLinkCommand(Index.fromZeroBased(0));
        assertTrue(command1.equals(command1));

    }

    @Test
    public void notEqual_differentTypes() {
        DeleteLinkCommand command1 = new DeleteLinkCommand(Index.fromZeroBased(0));
        assertFalse(command1.equals(5));
    }

    @Test
    public void equals_diffObjectSameParameters() {
        DeleteLinkCommand command1 = new DeleteLinkCommand(Index.fromZeroBased(0));
        DeleteLinkCommand command2 = new DeleteLinkCommand(Index.fromZeroBased(0));
        assertTrue(command1.equals(command2));
    }

    @Test
    public void notEqual_differentObjectDifferentParameters() {
        DeleteLinkCommand command1 = new DeleteLinkCommand(Index.fromZeroBased(0));
        DeleteLinkCommand command2 = new DeleteLinkCommand(Index.fromZeroBased(1));
        assertFalse(command1.equals(command2));
    }

    @Test
    public void notEqual_null() {
        DeleteLinkCommand command1 = new DeleteLinkCommand(Index.fromZeroBased(0));
        assertFalse(command1.equals(null));
    }
}
