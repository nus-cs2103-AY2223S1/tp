package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

// import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
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
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Tag> newTags = new ArrayList<>();
        AddressBookStub newData = new AddressBookStub(newPersons, newTags);

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
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    void sortByName() {
        AddressBook sampleA = new AddressBook();
        AddressBook sampleB = new AddressBook();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(BENSON);
        sampleB.addPerson(BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByName(true);
        assertEquals(sampleA, sampleB);
        sampleA.sortByName(false);
        assertNotEquals(sampleA, sampleB);
    }

    @Test
    void sortByPhone() {
        AddressBook sampleA = new AddressBook();
        AddressBook sampleB = new AddressBook();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(BENSON);
        sampleB.addPerson(BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByPhone(true);
        assertEquals(sampleA, sampleB);
        sampleA.sortByPhone(false);
        assertNotEquals(sampleA, sampleB);
    }

    @Test
    void sortByEmail() {
        AddressBook sampleA = new AddressBook();
        AddressBook sampleB = new AddressBook();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(BENSON);
        sampleB.addPerson(BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByEmail(true);
        assertEquals(sampleA, sampleB);
        sampleA.sortByEmail(false);
        assertNotEquals(sampleA, sampleB);
    }

    @Test
    void sortByAddress() {
        AddressBook sampleA = new AddressBook();
        AddressBook sampleB = new AddressBook();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(BENSON);
        sampleB.addPerson(BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByAddress(true);
        assertEquals(sampleA, sampleB);
        sampleA.sortByAddress(false);
        assertNotEquals(sampleA, sampleB);
    }

    /**
     * Disabled until test methods has been updated for upgraded tags.
     */
    @Test
    @Disabled
    void sortByTag() {
        AddressBook sampleA = new AddressBook();
        AddressBook sampleB = new AddressBook();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(BENSON);
        sampleB.addPerson(BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByTag(new Tag("owesMoney"), true);
        assertEquals(sampleA, sampleB);
        sampleA.sortByTag(new Tag("owesMoney"), false);
        assertNotEquals(sampleA, sampleB);
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<Tag> tags) {
            this.persons.setAll(persons);
            this.tags.setAll(tags);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }
    }

}
