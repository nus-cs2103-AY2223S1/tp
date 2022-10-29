package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsHealthContact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.bill.Bill;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.DuplicatePatientException;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.PatientBuilder;

public class HealthContactTest {

    private final HealthContact healthContact = new HealthContact();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), healthContact.getPatientList());
        assertEquals(Collections.emptyList(), healthContact.getAppointmentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> healthContact.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyHealthContact_replacesData() {
        HealthContact newData = getTypicalPatientsHealthContact();
        healthContact.resetData(newData);
        assertEquals(newData, healthContact);
    }

    @Test
    public void resetData_withDuplicatePatients_throwsDuplicatePatientException() {
        // Two patients with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        HealthContactStub newData = new HealthContactStub(newPatients, null);

        assertThrows(DuplicatePatientException.class, () -> healthContact.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateAppointments_throwsDuplicateAppointmentException() {
        // Two patients with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Patient> newPatients = Arrays.asList(ALICE);
        List<Appointment> appointments = Arrays.asList(
                APPOINTMENT_1, new AppointmentBuilder(APPOINTMENT_1).build());
        HealthContactStub newData = new HealthContactStub(newPatients, appointments);
        assertThrows(DuplicateAppointmentException.class, () -> healthContact.resetData(newData));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> healthContact.hasPatient((Patient) null));
        assertThrows(NullPointerException.class, () -> healthContact.hasPatient((Name) null));
    }

    @Test
    public void hasAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> healthContact.hasAppointment(null));
    }

    @Test
    public void hasPatientPatient_patientNotInHealthContact_returnsFalse() {
        assertFalse(healthContact.hasPatient(ALICE));
    }

    @Test
    public void hasPatientName_patientNotInHealthContact_returnsFalse() {
        assertFalse(healthContact.hasPatient(ALICE.getName()));
    }

    @Test
    public void hasAppointment_appointmentNotInHealthContact_returnsFalse() {
        assertFalse(healthContact.hasAppointment(APPOINTMENT_1));
    }

    @Test
    public void hasPatientPatient_patientInHealthContact_returnsTrue() {
        healthContact.addPatient(ALICE);
        assertTrue(healthContact.hasPatient(ALICE));
    }

    @Test
    public void hasPatientName_patientInHealthContact_returnsTrue() {
        healthContact.addPatient(ALICE);
        assertTrue(healthContact.hasPatient(ALICE.getName()));
    }

    @Test
    public void hasAppointment_appointmentInHealthContact_returnsTrue() {
        healthContact.addAppointment(APPOINTMENT_1);
        assertTrue(healthContact.hasAppointment(APPOINTMENT_1));
    }

    @Test
    public void hasPatient_patientWithSameIdentityFieldsInHealthContact_returnsTrue() {
        healthContact.addPatient(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(healthContact.hasPatient(editedAlice));
    }

    @Test
    public void getPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> healthContact.getPatientList().remove(0));
    }

    @Test
    public void getAppointmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> healthContact.getAppointmentList().remove(0));
    }

    /**
     * A stub ReadOnlyHealthContact whose patients list can violate interface constraints.
     */
    private static class HealthContactStub implements ReadOnlyHealthContact {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();
        private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        private final ObservableList<Bill> bills = FXCollections.observableArrayList();

        HealthContactStub(Collection<Patient> patients, Collection<Appointment> appointments) {
            this.patients.setAll(patients);
            this.appointments.setAll(Optional.ofNullable(appointments).orElse(new ArrayList<>()));
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            return patients;
        }

        @Override
        public ObservableList<Appointment> getAppointmentList() {
            return appointments;
        }

        @Override
        public ObservableList<Bill> getBillList() {
            return bills;
        }
    }

}
