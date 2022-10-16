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
public class AddGroupMemberCommandTest {

    private Model model = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupMemberCommand("CS2101", null));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupMemberCommand(null, "nameHere"));
    }

    @Test
    public void execute_invalidGroup_commandFailure() {
        Person personToAddGroup = model.getPersonWithName(CARL.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToAddGroup).build();
        AddGroupMemberCommand addGroupMemberCommand =
                new AddGroupMemberCommand("Alpha", CARL.getName().fullName);

        String expectedMessage = String.format(AddGroupMemberCommand.MESSAGE_INVALID_GROUP);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToAddGroup, editedPerson);
        assertCommandFailure(addGroupMemberCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_invalidPerson_commandFailure() {
        Person personToAddGroup = model.getPersonWithName(CARL.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToAddGroup).build();
        String name = "Bob";
        AddGroupMemberCommand addGroupMemberCommand =
                new AddGroupMemberCommand("Alpha", name);

        String expectedMessage = String.format(AddGroupMemberCommand.MESSAGE_INVALID_PERSON, name);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToAddGroup, editedPerson);
        assertCommandFailure(addGroupMemberCommand, expectedModel, expectedMessage);
    }
}
