package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EAR;
import static seedu.address.model.Model.COMPARATOR_GROUP_PATIENTS;
import static seedu.address.model.Model.COMPARATOR_GROUP_PATIENT_APPOINTMENTS;
import static seedu.address.model.Model.COMPARATOR_GROUP_TAG_APPOINTMENTS;
import static seedu.address.model.Model.COMPARATOR_UNGROUP_APPOINTMENTS;
import static seedu.address.model.Model.COMPARATOR_UNGROUP_PATIENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.getDefaultAppointments;
import static seedu.address.testutil.TypicalAppointments.getGroupedAppointmentsByPatient;
import static seedu.address.testutil.TypicalAppointments.getGroupedAppointmentsByTag;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getGroupedPersons;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Appointment;
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
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_EAR)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Appointment> newAppointments = new ArrayList<>();
        AddressBookStub newData = new AddressBookStub(newPersons, newAppointments);

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
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_EAR)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
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
        private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<Appointment> appointments) {
            this.persons.setAll(persons);
            this.appointments.setAll(appointments);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Appointment> getAppointmentList() {
            return appointments;
        }
    }

    @Test
    public void patientCompares() {
        addressBook.setPersons(getTypicalPersons());
        List<Person> ungrouped = getTypicalPersons();
        List<Person> grouped = getGroupedPersons();
        assertEquals(addressBook.getPersonList(), ungrouped);
        addressBook.sortPersons(COMPARATOR_GROUP_PATIENTS);
        assertEquals(addressBook.getPersonList(), grouped);
        addressBook.sortPersons(COMPARATOR_UNGROUP_PATIENTS);
        assertEquals(addressBook.getPersonList(), ungrouped);
    }

    @Test
    public void appointmentCompared() {
        addressBook.setAppointments(getDefaultAppointments());
        List<Appointment> ungrouped = getDefaultAppointments();
        List<Appointment> groupedByTag = getGroupedAppointmentsByTag();
        List<Appointment> groupedByPatient = getGroupedAppointmentsByPatient();
        assertEquals(addressBook.getAppointmentList(), ungrouped);
        addressBook.sortAppointments(COMPARATOR_GROUP_TAG_APPOINTMENTS);
        assertEquals(addressBook.getAppointmentList(), groupedByTag);
        addressBook.sortAppointments(COMPARATOR_GROUP_PATIENT_APPOINTMENTS);
        assertEquals(addressBook.getAppointmentList(), groupedByPatient);
        addressBook.sortAppointments(COMPARATOR_UNGROUP_APPOINTMENTS);
        assertEquals(addressBook.getAppointmentList(), ungrouped);
    }
}
