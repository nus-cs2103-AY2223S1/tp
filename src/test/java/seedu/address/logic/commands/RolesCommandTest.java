package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RolesCommand}.
 */
public class RolesCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validRolesUnfilteredList_success() {
        Person personToEdit = model.getFilteredPersonList().get(2);
        RolesCommand rolesCommand = new RolesCommand(Index.fromOneBased(3), "Lecturer");
        Person editedPerson = personToEdit;
        editedPerson.getPosition().setDetails("Lecturer");
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);
        String expectedMessage = String.format(RolesCommand.MESSAGE_EDIT_ROLES_SUCCESS, editedPerson);

        assertCommandSuccess(rolesCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexPerson_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RolesCommand rolesCommand = new RolesCommand(outOfBoundIndex, "Lecturer");

        assertCommandFailure(rolesCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_notProfessor_throwsCommandException() {
        Index nonProfessorIndex = Index.fromOneBased(1);
        RolesCommand rolesCommand = new RolesCommand(nonProfessorIndex, "Lecturer");

        assertCommandFailure(rolesCommand, model, RolesCommand.MESSAGE_PERSON_NOT_PROFESSOR);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        RolesCommand rolesCommand = new RolesCommand(outOfBoundIndex, "Lecturer");

        assertCommandFailure(rolesCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        RolesCommand rolesFirstCommand = new RolesCommand(INDEX_FIRST_PERSON, "Lecturer");
        RolesCommand rolesSecondCommand = new RolesCommand(INDEX_SECOND_PERSON, "Lecturer");

        // same object -> returns true
        assertTrue(rolesFirstCommand.equals(rolesFirstCommand));

        // same values -> returns true
        RolesCommand rolesFirstCommandCopy = new RolesCommand(INDEX_FIRST_PERSON, "Lecturer");
        assertTrue(rolesFirstCommand.equals(rolesFirstCommandCopy));

        // different types -> returns false
        assertFalse(rolesFirstCommand.equals(1));

        // null -> returns false
        assertFalse(rolesFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(rolesFirstCommand.equals(rolesSecondCommand));
    }

}
