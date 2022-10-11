package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_IP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_TP;
import static seedu.address.model.person.testutil.Assert.assertThrows;
import static seedu.address.model.person.testutil.TypicalPersons.ALICE;
import static seedu.address.model.person.testutil.TypicalPersons.BOB;
import static seedu.address.model.person.testutil.TypicalPersons.CARL;
import static seedu.address.model.person.testutil.TypicalPersons.DANIEL;
import static seedu.address.model.person.testutil.TypicalPersons.ELLE;
import static seedu.address.model.person.testutil.TypicalPersons.FIONA;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.testutil.GroupBuilder;

public class GroupTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Group group = new GroupBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> group.getMembers().remove(0));
    }

    @Test
    public void isSameGroup() {
        Group group = new GroupBuilder().build();
        // same object -> returns true
        assertTrue(group.isSameGroup(group));

        // null -> returns false
        assertFalse(group.isSameGroup(null));

        // same name, different members -> returns true
        Group editedGroup = new GroupBuilder().withName(VALID_GROUPNAME_TP).withMembers(DANIEL, ELLE, FIONA).build();
        assertTrue(group.isSameGroup(editedGroup));

        // different name, same members -> returns false
        editedGroup = new GroupBuilder().withName(VALID_GROUPNAME_IP).withMembers(ALICE, BOB, CARL).build();
        assertFalse(group.isSameGroup(editedGroup));

        //name differs in case, all other attributes same -> returns false
        editedGroup =
                new GroupBuilder().withName(VALID_GROUPNAME_TP.toLowerCase()).withMembers(ALICE, BOB, CARL).build();
        assertFalse(group.isSameGroup(editedGroup));

        //name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_GROUPNAME_TP + " ";
        editedGroup = new GroupBuilder().withName(nameWithTrailingSpaces).withMembers(ALICE, BOB, CARL).build();
        assertFalse(group.isSameGroup(editedGroup));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Group defaultGroup = new GroupBuilder().build();
        Group otherGroup = new GroupBuilder().withName(VALID_GROUPNAME_TP).withMembers(ALICE, BOB, CARL).build();
        assertTrue(defaultGroup.equals(otherGroup));

        // same object -> returns true
        assertTrue(defaultGroup.equals(defaultGroup));

        // null -> returns false
        assertFalse(defaultGroup.equals(null));

        // different type -> returns false
        assertFalse(defaultGroup.equals(5));

        // different group -> returns false
        otherGroup = new GroupBuilder().withName(VALID_GROUPNAME_IP).withMembers(DANIEL).build();
        assertFalse(defaultGroup.equals(otherGroup));

        // different name -> returns false
        otherGroup = new GroupBuilder().withName(VALID_GROUPNAME_IP).withMembers(ALICE, BOB, CARL).build();
        assertFalse(defaultGroup.equals(otherGroup));

        // different members -> returns false
        otherGroup = new GroupBuilder().withName(VALID_GROUPNAME_TP).withMembers(DANIEL, ELLE, FIONA).build();
        assertFalse(defaultGroup.equals(otherGroup));
    }
}
