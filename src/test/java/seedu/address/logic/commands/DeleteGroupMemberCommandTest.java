package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DeleteGroupMemberCommand.MESSAGE_DELETE_MEMBER_SUCCESS;
import static seedu.address.model.person.testutil.Assert.assertThrows;
import static seedu.address.model.person.testutil.TypicalPersons.CARL;
import static seedu.address.model.person.testutil.TypicalPersons.ELLE;
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
public class DeleteGroupMemberCommandTest {

    private Model model = new ModelManager(new AddressBook(getTypicalAddressBookWithGroups()), new UserPrefs());
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteGroupMemberCommand(new PersonGroup("CS2101"), null));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteGroupMemberCommand(null, new Name("nameHere")));
    }

    @Test
    public void execute_invalidGroup_commandFailure() {
        Person personToAddGroup = model.getPersonWithName(CARL.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToAddGroup).build();
        DeleteGroupMemberCommand deleteGroupMemberCommand =
                new DeleteGroupMemberCommand(new PersonGroup("ABCD"), CARL.getName());

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
                new DeleteGroupMemberCommand(new PersonGroup("Alpha"), new Name("Bob"));

        String expectedMessage = String.format(DeleteGroupMemberCommand.MESSAGE_INVALID_PERSON);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToAddGroup, editedPerson);
        assertCommandFailure(deleteGroupMemberCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_validPerson_commandSuccess() {
        Name name = ELLE.getName();
        Person personToDeleteGroup = model.getPersonWithName(name).get(0);

        String groupName = "Alpha";
        DeleteGroupMemberCommand deleteGroupMemberCommand =
                new DeleteGroupMemberCommand(new PersonGroup(groupName), name);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Group group = model.getGroupWithName(new GroupName(groupName)).get(0);
        HashSet<Person> newSet = new HashSet<>(group.getMembers());
        newSet.remove(personToDeleteGroup);
        Group expectedGroup = new Group(new GroupName(groupName), newSet);

        Person editedPerson = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
                .withEmail("werner@example.com").withAddress("michegan ave")
                .withAssignments(new String[]{"Beta"}, new String[][]{{"Team Beta"}})
                .build();
        String expectedMessage = String.format(MESSAGE_DELETE_MEMBER_SUCCESS, ELLE.getName(), expectedGroup);

        Group group1 = model.getGroupWithName(new GroupName("Beta")).get(0);
        HashSet<Person> newSet1 = new HashSet<>(group.getMembers());
        newSet1.remove(personToDeleteGroup);
        newSet1.add(editedPerson);
        Group expectedGroup1 = new Group(new GroupName("Beta"), newSet1);

        expectedModel.setGroup(group, expectedGroup);
        expectedModel.setGroup(group1, expectedGroup1);
        expectedModel.setPerson(personToDeleteGroup, editedPerson);

        assertCommandSuccess(deleteGroupMemberCommand, model, expectedMessage, expectedModel);
    }
}
