package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_TP;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.model.person.testutil.Assert.assertThrows;
import static seedu.address.model.person.testutil.TypicalPersons.*;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.AddGroupCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Person;
import seedu.address.model.person.testutil.GroupBuilder;
import seedu.address.model.person.testutil.PersonBuilder;

import java.util.HashSet;
import java.util.Set;

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
    public void execute_addGroupMemberCommand_failure_Invalid_Group() {
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
    public void execute_addGroupMemberCommand_failure_Invalid_Person() {
        Person personToAddGroup = model.getPersonWithName(CARL.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToAddGroup).build();
        AddGroupMemberCommand addGroupMemberCommand =
                new AddGroupMemberCommand("Alpha", "Bob");

        String expectedMessage = String.format(AddGroupMemberCommand.MESSAGE_INVALID_PERSON);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToAddGroup, editedPerson);
        assertCommandFailure(addGroupMemberCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_addGroupMemberCommand_failure_Duplicate_Person() {
        Person personToAddGroup = model.getPersonWithName(CARL.getName()).get(0);

        Set<Person> members = new HashSet<>();
        members.add(personToAddGroup);
        Group g = new GroupBuilder().withName(VALID_GROUPNAME_TP).withMembers(DANIEL, ELLE, FIONA).build();

        Person editedPerson = new PersonBuilder(personToAddGroup).build();

        AddGroupMemberCommand addGroupMemberCommand =
                new AddGroupMemberCommand(g.getName().groupName, CARL.getName().fullName);

        String expectedMessage = String.format(AddGroupMemberCommand.MESSAGE_DUPLICATE_PERSON_IN_GROUP);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToAddGroup, editedPerson);
        assertCommandFailure(addGroupMemberCommand, expectedModel, expectedMessage);
    }
}
