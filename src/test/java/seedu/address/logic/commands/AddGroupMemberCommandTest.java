package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.AddGroupMemberCommand.MESSAGE_ASSIGN_GROUP_SUCCESS;
import static seedu.address.logic.commands.AddGroupMemberCommand.MESSAGE_DUPLICATE_PERSON_IN_GROUP;
import static seedu.address.logic.commands.AddGroupMemberCommand.MESSAGE_INVALID_GROUP;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.person.testutil.Assert.assertThrows;
import static seedu.address.model.person.testutil.TypicalPersons.CARL;
import static seedu.address.model.person.testutil.TypicalPersons.DANIEL;
import static seedu.address.model.person.testutil.TypicalPersons.getTypicalAddressBookWithGroups;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonGroup;
import seedu.address.model.person.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddMemberCommand}.
 */
public class AddGroupMemberCommandTest {

    private Model model = new ModelManager(new AddressBook(getTypicalAddressBookWithGroups()), new UserPrefs());
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupMemberCommand(new PersonGroup("CS2101"), null));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupMemberCommand(null, new Name("nameHere")));
    }

    @Test
    public void equals() {
        AddGroupMemberCommand cmd = new AddGroupMemberCommand(new PersonGroup("group"), new Name("name"));
        assertEquals(cmd, cmd);
        assertFalse(cmd.equals(null));
    }

    @Test
    public void execute_invalidGroup_commandFailure() {
        Person personToAddGroup = model.getPersonWithName(CARL.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToAddGroup).build();
        AddGroupMemberCommand addGroupMemberCommand =
                new AddGroupMemberCommand(new PersonGroup("Invalid Group"), CARL.getName());

        String expectedMessage = String.format(MESSAGE_INVALID_GROUP);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToAddGroup, editedPerson);
        assertCommandFailure(addGroupMemberCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_invalidPerson_commandFailure() {
        Person personToAddGroup = model.getPersonWithName(CARL.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToAddGroup).build();
        Name name = new Name("Bob");
        AddGroupMemberCommand addGroupMemberCommand =
                new AddGroupMemberCommand(new PersonGroup("Alpha"), name);

        String expectedMessage = String.format(AddGroupMemberCommand.MESSAGE_INVALID_PERSON, name);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToAddGroup, editedPerson);
        assertCommandFailure(addGroupMemberCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_duplicatePerson_commandSuccess() {
        Name name = CARL.getName();
        Person personToAddGroup = model.getPersonWithName(name).get(0);
        Person editedPerson = new PersonBuilder(personToAddGroup).withGroups(new String[]{"Team Project"}).build();
        String groupName = "Team Project";
        AddGroupMemberCommand addGroupMemberCommand =
                new AddGroupMemberCommand(new PersonGroup(groupName), name);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Group group = model.getGroupWithName(new GroupName(groupName)).get(0);
        HashSet<Person> newSet = new HashSet<>(group.getMembers());
        newSet.add(editedPerson);
        Group expectedGroup = new Group(new GroupName(groupName), newSet);

        String expectedMessage = String.format(MESSAGE_DUPLICATE_PERSON_IN_GROUP, CARL.getName());

        assertCommandFailure(addGroupMemberCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_validPerson_commandSuccess() {
        Name name = DANIEL.getName();
        Person personToAddGroup = model.getPersonWithName(name).get(0);
        Person editedPerson = new PersonBuilder(personToAddGroup).withGroups(new String[]{"Team Project"}).build();
        String groupName = "Team Project";
        AddGroupMemberCommand addGroupMemberCommand =
                new AddGroupMemberCommand(new PersonGroup(groupName), name);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Group group = model.getGroupWithName(new GroupName(groupName)).get(0);
        HashSet<Person> newSet = new HashSet<>(group.getMembers());
        newSet.add(editedPerson);
        Group expectedGroup = new Group(new GroupName(groupName), newSet);

        String expectedMessage = String.format(MESSAGE_ASSIGN_GROUP_SUCCESS, DANIEL.getName(), expectedGroup);
        expectedModel.setGroup(group, expectedGroup);
        expectedModel.setPerson(personToAddGroup, editedPerson);

        assertCommandSuccess(addGroupMemberCommand, model, expectedMessage, expectedModel);
    }
}
