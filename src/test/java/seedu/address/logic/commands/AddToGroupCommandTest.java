package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class AddToGroupCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddToGroupCommand(null, new Group("example")));
    }

    @Test
    void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person groupedPerson = personInList.withGroups("friends").build();
        expectedModel.setPerson(lastPerson, groupedPerson);

        String expectedMessage = String.format(AddToGroupCommand.MESSAGE_GROUP_PERSON_SUCCESS, groupedPerson);

        AddToGroupCommand command = new AddToGroupCommand(indexLastPerson, new Group("friends"));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidIndex_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Index invalidIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        AddToGroupCommand command = new AddToGroupCommand(invalidIndex, new Group("friends"));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index index = Index.fromOneBased(1);
        Group group1 = new Group("testGroup1");
        Group group2 = new Group("testGroup2");
        AddToGroupCommand addGroup1Command = new AddToGroupCommand(index, group1);
        AddToGroupCommand addGroup2Command = new AddToGroupCommand(index, group2);

        // same object -> returns true
        assertTrue(addGroup1Command.equals(addGroup1Command));

        // same values -> returns true
        AddToGroupCommand addGroup1CommandCopy = new AddToGroupCommand(index, group1);
        assertTrue(addGroup1Command.equals(addGroup1CommandCopy));

        // different types -> returns false
        assertFalse(addGroup1Command.equals(1));

        // null -> returns false
        assertFalse(addGroup1Command.equals(null));

        // different person -> returns false
        assertFalse(addGroup1Command.equals(addGroup2Command));
    }

}
