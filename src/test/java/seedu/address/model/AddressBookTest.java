package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_VALUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADUATION_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAXIMUM_CAP_VALUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_KIV;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIVERSITY_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
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
        Person editedAlice = new PersonBuilder(ALICE)
                .withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withCap(VALID_CAP_VALUE_BOB, VALID_MAXIMUM_CAP_VALUE_BOB)
                .withGender(VALID_GENDER_BOB)
                .withGraduationDate(VALID_GRADUATION_DATE_BOB)
                .withUniversity(VALID_UNIVERSITY_BOB)
                .withMajor(VALID_MAJOR_BOB)
                .withTags(VALID_TAG_KIV)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

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
        Person editedAlice = new PersonBuilder(ALICE)
                .withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withCap(VALID_CAP_VALUE_BOB, VALID_MAXIMUM_CAP_VALUE_BOB)
                .withGender(VALID_GENDER_BOB)
                .withGraduationDate(VALID_GRADUATION_DATE_BOB)
                .withUniversity(VALID_UNIVERSITY_BOB)
                .withMajor(VALID_MAJOR_BOB)
                .withTags(VALID_TAG_KIV)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasPerson_personWithSameEmailNameInAddressBook_returnsFalse() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE)
                .withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withCap(VALID_CAP_VALUE_BOB, VALID_MAXIMUM_CAP_VALUE_BOB)
                .withGender(VALID_GENDER_BOB)
                .withGraduationDate(VALID_GRADUATION_DATE_BOB)
                .withUniversity(VALID_UNIVERSITY_BOB)
                .withId(VALID_JOB_ID_BOB)
                .withTitle(VALID_JOB_TITLE_BOB)
                .withMajor(VALID_MAJOR_BOB)
                .withTags(VALID_TAG_KIV)
                .build();
        assertFalse(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasPerson_personWithSameJobInAddressBook_returnsFalse() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE)
                .withName(VALID_NAME_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withCap(VALID_CAP_VALUE_BOB, VALID_MAXIMUM_CAP_VALUE_BOB)
                .withGender(VALID_GENDER_BOB)
                .withGraduationDate(VALID_GRADUATION_DATE_BOB)
                .withUniversity(VALID_UNIVERSITY_BOB)
                .withMajor(VALID_MAJOR_BOB)
                .withTags(VALID_TAG_KIV)
                .build();
        assertFalse(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasPersons_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPersons(null));
    }

    @Test
    public void hasPersons_addressBookWithSomeSamePersons_returnsTrue() {
        addressBook.addPerson(ALICE);
        addressBook.addPerson(BENSON);
        AddressBook ab = new AddressBook();
        ab.addPerson(ALICE);
        assertTrue(addressBook.hasPersons(ab));
    }

    @Test
    public void hasPersons_addressBookWithAllSamePersons_returnsTrue() {
        addressBook.addPerson(ALICE);
        addressBook.addPerson(BENSON);
        AddressBook ab = new AddressBook();
        ab.addPerson(ALICE);
        ab.addPerson(BENSON);
        assertTrue(addressBook.hasPersons(ab));
    }

    @Test
    public void hasPersons_addressBookWithDifferentPersons_returnsFalse() {
        addressBook.addPerson(ALICE);
        AddressBook ab = new AddressBook();
        ab.addPerson(BENSON);
        assertFalse(addressBook.hasPersons(ab));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
