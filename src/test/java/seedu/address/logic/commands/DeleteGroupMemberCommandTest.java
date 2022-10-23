package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.model.person.testutil.Assert.assertThrows;
import static seedu.address.model.person.testutil.TypicalPersons.CARL;
import static seedu.address.model.person.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddMemberCommand}.
 */
public class DeleteGroupMemberCommandTest {

    private Model model = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteGroupMemberCommand("CS2101", null));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteGroupMemberCommand(null, "nameHere"));
    }

    @Test
    public void execute_invalidGroup_commandFailure() {
        Person personToAddGroup = model.getPersonWithName(CARL.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToAddGroup).build();
        DeleteGroupMemberCommand deleteGroupMemberCommand =
                new DeleteGroupMemberCommand("Alpha", CARL.getName().fullName);

        String expectedMessage = String.format(DeleteGroupMemberCommand.MESSAGE_INVALID_GROUP);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToAddGroup, editedPerson);
        assertCommandFailure(deleteGroupMemberCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_invalidPerson_commandFailure() {
        Person personToAddGroup = model.getPersonWithName(CARL.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToAddGroup).build();
        DeleteGroupMemberCommand deleteGroupMemberCommand =
                new DeleteGroupMemberCommand("Alpha", "Bob");

        String expectedMessage = String.format(DeleteGroupMemberCommand.MESSAGE_INVALID_PERSON);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToAddGroup, editedPerson);
        assertCommandFailure(deleteGroupMemberCommand, expectedModel, expectedMessage);
    }
}
