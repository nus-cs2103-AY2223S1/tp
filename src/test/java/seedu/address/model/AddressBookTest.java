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
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.XAVIER;
import static seedu.address.testutil.TypicalPersons.ZEPHYR;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.user.User;
import seedu.address.testutil.AddressBookBuilder;
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
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasUser_emptyUser_returnsFalse() {
        assertFalse(addressBook.hasUser());
    }

    @Test
    public void hasUser_existingUser_returnsTrue() {
        addressBook.addUser(ZEPHYR);
        assertTrue(addressBook.hasUser());
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
    public void equals() {
        // same values -> returns true
        AddressBook ab = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).withUser(ZEPHYR).build();
        AddressBook abCopy = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).withUser(ZEPHYR).build();
        assertTrue(ab.equals(abCopy));

        // same object -> returns true
        assertTrue(ab.equals(ab));

        // null -> returns false
        assertFalse(ab.equals(null));

        // different types -> returns false
        assertFalse(ab.equals(5));

        // different personsList -> returns false
        AddressBook differentAddressBook = new AddressBookBuilder().withPerson(CARL).withPerson(DANIEL).build();
        assertFalse(ab.equals(differentAddressBook));

        // different addressBook -> returns false
        differentAddressBook = new AddressBookBuilder().withUser(XAVIER).build();
        assertFalse(ab.equals(differentAddressBook));

        // different user -> returns false
        abCopy.setUser(XAVIER);
        assertFalse(ab.equals(abCopy));

        // abCopy empty user -> returns false
        abCopy.deleteUser();
        assertFalse(ab.equals(abCopy));

        // both empty user -> returns true
        ab.deleteUser();
        assertTrue(ab.equals(abCopy));
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        AddressBook ab = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).withUser(ZEPHYR).build();
        AddressBook abCopy = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).withUser(ZEPHYR).build();
        assertEquals(ab.hashCode(), abCopy.hashCode());

        // different personsList -> returns different hashcode
        AddressBook differentAb = new AddressBookBuilder().withPerson(CARL).withPerson(DANIEL).withUser(ZEPHYR)
                .build();
        assertNotEquals(ab.hashCode(), differentAb.hashCode());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private User user;

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public User getUser() {
            return user;
        }
    }

}
