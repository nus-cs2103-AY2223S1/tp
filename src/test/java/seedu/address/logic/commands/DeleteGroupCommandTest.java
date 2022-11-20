package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.group.testutil.TypicalGroups.ORAL_PRESENTATION;
import static seedu.address.model.group.testutil.TypicalGroups.TEAM_PROJECT;
import static seedu.address.model.group.testutil.TypicalGroups.getTypicalAddressBookWithGroups;
import static seedu.address.model.person.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonGroup;
import seedu.address.model.person.testutil.GroupBuilder;

public class DeleteGroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    @Test
    public void execute_validGroup_success() {
        Group groupToDelete = model.getFilteredGroupList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(TEAM_PROJECT);

        String expectedMessage = String.format(DeleteGroupCommand.MESSAGE_SUCCESS, groupToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        Set<Person> currMembers = new HashSet<Person>(expectedModel
                .getGroupWithName(groupToDelete.getName()).get(0).getMembers());

        PersonGroup personGroupToRemove = new PersonGroup(groupToDelete.getName().groupName);

        for (Person p : currMembers) {
            ArrayList<PersonGroup> editedPersonGroup = new ArrayList<>(p.getPersonGroups());

            editedPersonGroup.remove(personGroupToRemove);
            Person editedPerson = new Person(p.getName(), p.getPhone(), p.getEmail(), p.getAddress(),
                    p.getTags(), p.getAssignments(), editedPersonGroup);

            for (PersonGroup pg : editedPersonGroup) {
                Group currGroup = expectedModel.getGroupWithName(new GroupName(pg.getGroupName())).get(0);
                Set<Person> editedPersonList = new HashSet<Person>(currGroup.getMembers());

                editedPersonList.remove(p);
                editedPersonList.add(editedPerson);

                Group expectedGroup = new Group(currGroup.getName(), editedPersonList);

                expectedModel.setGroup(currGroup, expectedGroup);
            }

            expectedModel.setPerson(p, editedPerson);
        }

        expectedModel.deleteGroup(groupToDelete);

        assertCommandSuccess(deleteGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidGroup_throwsCommandException() {
        Group notPresentGroup = new GroupBuilder().withName("Group Does Not Exist").withMembers().build();
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(notPresentGroup);

        assertCommandFailure(deleteGroupCommand, model, Messages.MESSAGE_INVALID_GROUP_NAME);
    }

    @Test
    public void equals() {
        DeleteGroupCommand deleteFirstCommand = new DeleteGroupCommand(TEAM_PROJECT);
        DeleteGroupCommand deleteSecondCommand = new DeleteGroupCommand(ORAL_PRESENTATION);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteGroupCommand deleteFirstCommandCopy = new DeleteGroupCommand(TEAM_PROJECT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different group -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
