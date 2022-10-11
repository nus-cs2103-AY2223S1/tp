package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_IP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_TP;
import static seedu.address.model.person.testutil.Assert.assertThrows;
import static seedu.address.model.person.testutil.TypicalPersons.DANIEL;
import static seedu.address.model.person.testutil.TypicalPersons.ELLE;
import static seedu.address.model.person.testutil.TypicalPersons.FIONA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.person.testutil.GroupBuilder;

public class UniqueGroupListTest {

    private final UniqueGroupList uniqueGroupList = new UniqueGroupList();
    private final Group group = new GroupBuilder().build();

    @Test
    public void contains_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.contains(null));
    }

    @Test
    public void contains_groupNotInList_returnsFalse() {
        assertFalse(uniqueGroupList.contains(group));
    }

    @Test
    public void contains_groupInList_returnsTrue() {
        uniqueGroupList.add(group);
        assertTrue(uniqueGroupList.contains(group));
    }

    @Test
    public void contains_groupWithSameMembersInList_returnsTrue() {
        uniqueGroupList.add(group);
        Group editedGroup = new GroupBuilder().withName(VALID_GROUPNAME_TP).withMembers(DANIEL, ELLE, FIONA).build();
        assertTrue(uniqueGroupList.contains(editedGroup));
    }

    @Test
    public void add_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.add(null));
    }

    @Test
    public void add_duplicateGroup_throwsDuplicateGroupException() {
        uniqueGroupList.add(group);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.add(group));
    }

    @Test
    public void remove_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.remove(null));
    }

    @Test
    public void remove_groupDoesNotExist_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> uniqueGroupList.remove(group));
    }

    @Test
    public void remove_existingGroup_removesGroup() {
        uniqueGroupList.add(group);
        uniqueGroupList.remove(group);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void removeFromGroupName_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.removeFromGroupName(null));
    }

    @Test
    public void removeFromGroupName_groupDoesNotExist_throwsGroupNotFoundException() {
        GroupName groupName = new GroupName(VALID_GROUPNAME_TP);
        assertThrows(GroupNotFoundException.class, () -> uniqueGroupList.removeFromGroupName(groupName));
    }

    @Test
    public void removeFromGroupName_existingGroup_removesGroup() {
        GroupName groupName = new GroupName(VALID_GROUPNAME_TP);
        uniqueGroupList.add(group);
        uniqueGroupList.removeFromGroupName(groupName);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_nullUniqueGroupList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroups((UniqueGroupList) null));
    }

    @Test
    public void setPersons_uniqueGroupsList_replacesOwnListWithProvidedUniqueGroupList() {
        uniqueGroupList.add(group);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(new GroupBuilder().withName(VALID_GROUPNAME_IP).withMembers(DANIEL).build());
        uniqueGroupList.setGroups(expectedUniqueGroupList);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroups((List<Group>) null));
    }

    @Test
    public void setGroups_list_replacesOwnListWithProvidedList() {
        uniqueGroupList.add(group);
        List<Group> groupList = Collections.singletonList(
                new GroupBuilder().withName(VALID_GROUPNAME_IP).withMembers(DANIEL).build());
        uniqueGroupList.setGroups(groupList);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(new GroupBuilder().withName(VALID_GROUPNAME_IP).withMembers(DANIEL).build());
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_listWithDuplicateGroups_throwsDuplicateGroupException() {
        List<Group> listWithDuplicateGroups = Arrays.asList(group, group);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.setGroups(listWithDuplicateGroups));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueGroupList.asUnmodifiableObservableList().remove(0));
    }
}
