package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.model.group.testutil.TypicalGroups.INDIVIDUAL_PROJECT;
import static seedu.address.model.group.testutil.TypicalGroups.TEAM_PROJECT;
import static seedu.address.model.group.testutil.TypicalGroups.getTypicalAddressBookWithGroups;
import static seedu.address.model.person.testutil.Assert.assertThrows;
import static seedu.address.model.person.testutil.TypicalPersons.ALICE;
import static seedu.address.model.person.testutil.TypicalPersons.BOB;
import static seedu.address.model.person.testutil.TypicalPersons.DANIEL;
import static seedu.address.model.person.testutil.TypicalPersons.ELLE;
import static seedu.address.model.person.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.group.testutil.TypicalGroups;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.testutil.GroupBuilder;
import seedu.address.model.person.testutil.PersonBuilder;
import seedu.address.model.person.testutil.TypicalPersons;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
        assertEquals(Collections.emptyList(), addressBook.getGroupList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);

        AddressBook newDataWithGroups = getTypicalAddressBookWithGroups();
        addressBook.resetData(newDataWithGroups);
        assertEquals(newDataWithGroups, addressBook);

        assertFalse((newDataWithGroups.equals(newData)));
    }

    @Test
    public void resetData_withDuplicatePerson_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons, TypicalGroups.getTypicalGroups());

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void addPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.addPerson(null));
    }

    @Test
    public void removePerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.removePerson(null));
    }

    @Test
    public void removePerson_personNotInAddressBook_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> addressBook.removePerson(ALICE));
    }

    @Test
    public void removePerson_personInAddressBook_personNoLongerInAddressBook() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
        addressBook.removePerson(ALICE);
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void setPerson_editedPersonIsNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_personNotInAddressBook_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> addressBook.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_personInAddressBook_personSuccessfullySet() {
        addressBook.addPerson(ALICE);
        addressBook.setPerson(ALICE, BOB);
        assertFalse(addressBook.hasPerson(ALICE));
        assertTrue(addressBook.hasPerson(BOB));
    }


    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void resetData_withDuplicateGroups_throwsDuplicateGroupException() {
        // Two groups with the same identity fields
        Group editedTp = new GroupBuilder(TEAM_PROJECT).withMembers(DANIEL, ELLE).build();
        List<Group> newGroups = Arrays.asList(TEAM_PROJECT, editedTp);
        AddressBookStub newData = new AddressBookStub(TypicalPersons.getTypicalPersons(), newGroups);

        assertThrows(DuplicateGroupException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasGroup_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasGroup(null));
    }

    @Test
    public void hasGroup_groupNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasGroup(TEAM_PROJECT));
    }

    @Test
    public void hasGroup_groupInAddressBook_returnsTrue() {
        addressBook.addGroup(TEAM_PROJECT);
        assertTrue(addressBook.hasGroup(TEAM_PROJECT));
    }

    @Test
    public void hasGroup_groupWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addGroup(TEAM_PROJECT);
        Group editedTp = new GroupBuilder(TEAM_PROJECT).withMembers(DANIEL, ELLE).build();
        assertTrue(addressBook.hasGroup(editedTp));
    }

    @Test
    public void addGroup_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.addGroup(null));
    }

    @Test
    public void removeGroup_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.removeGroup(null));
    }

    @Test
    public void removeGroup_groupNotInAddressBook_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> addressBook.removeGroup(TEAM_PROJECT));
    }

    @Test
    public void removeGroup_groupInAddressBook_groupNoLongerInAddressBook() {
        addressBook.addGroup(TEAM_PROJECT);
        assertTrue(addressBook.hasGroup(TEAM_PROJECT));
        addressBook.removeGroup(TEAM_PROJECT);
        assertFalse(addressBook.hasGroup(TEAM_PROJECT));
    }

    @Test
    public void setGroup_editedGroupIsNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setGroup(TEAM_PROJECT, null));
    }

    @Test
    public void setGroup_groupNotInAddressBook_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> addressBook.setGroup(TEAM_PROJECT, TEAM_PROJECT));
    }

    @Test
    public void setGroup_groupInAddressBook_groupSuccessfullySet() {
        addressBook.addGroup(TEAM_PROJECT);
        addressBook.setGroup(TEAM_PROJECT, INDIVIDUAL_PROJECT);
        assertFalse(addressBook.hasGroup(TEAM_PROJECT));
        assertTrue(addressBook.hasGroup(INDIVIDUAL_PROJECT));
    }


    @Test
    public void getGroupList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getGroupList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Group> groups = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<Group> groups) {
            this.persons.setAll(persons);
            this.groups.setAll(groups);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Group> getGroupList() {
            return groups;
        }
    }

}
