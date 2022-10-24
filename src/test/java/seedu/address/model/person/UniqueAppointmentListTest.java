package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.COMPARATOR_GROUP_PATIENT_APPOINTMENTS;
import static seedu.address.model.Model.COMPARATOR_GROUP_TAG_APPOINTMENTS;
import static seedu.address.model.Model.COMPARATOR_UNGROUP_APPOINTMENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_BENSON;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_CARL;
import static seedu.address.testutil.TypicalAppointments.getDefaultAppointments;
import static seedu.address.testutil.TypicalAppointments.getGroupedAppointmentsByPatient;
import static seedu.address.testutil.TypicalAppointments.getGroupedAppointmentsByTag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.AppointmentNotFoundException;
import seedu.address.model.person.exceptions.DuplicateAppointmentException;

public class UniqueAppointmentListTest {

    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.contains(APPOINTMENT_BENSON));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(APPOINTMENT_BENSON);
        assertTrue(uniqueAppointmentList.contains(APPOINTMENT_BENSON));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(APPOINTMENT_BENSON);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(APPOINTMENT_BENSON));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.remove(APPOINTMENT_BENSON));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(APPOINTMENT_BENSON);
        uniqueAppointmentList.remove(APPOINTMENT_BENSON);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullUniqueAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueAppointmentList.setAppointments((UniqueAppointmentList) null));
    }

    @Test
    public void setPersons_uniqueAppointmentList_replacesOwnListWithProvidedUniqueAppointmentList() {
        uniqueAppointmentList.add(APPOINTMENT_BENSON);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT_CARL);
        uniqueAppointmentList.setAppointments(expectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointments((List<Appointment>) null));
    }

    @Test
    public void setAppointments_list_replacesOwnListWithProvidedList() {
        uniqueAppointmentList.add(APPOINTMENT_BENSON);
        List<Appointment> appointmentList = Collections.singletonList(APPOINTMENT_CARL);
        uniqueAppointmentList.setAppointments(appointmentList);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT_CARL);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_listWithDuplicateAppointments_throwsDuplicateAppointmentException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(APPOINTMENT_CARL, APPOINTMENT_CARL);
        assertThrows(DuplicateAppointmentException.class, () ->
                uniqueAppointmentList.setAppointments(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueAppointmentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void appointmentCompared() {
        uniqueAppointmentList.setAppointments(getDefaultAppointments());
        List<Appointment> ungrouped = getDefaultAppointments();
        List<Appointment> groupedByTag = getGroupedAppointmentsByTag();
        List<Appointment> groupedByPatient = getGroupedAppointmentsByPatient();
        assertEquals(uniqueAppointmentList.asUnmodifiableObservableList(), ungrouped);
        uniqueAppointmentList.sort(COMPARATOR_GROUP_TAG_APPOINTMENTS);
        assertEquals(uniqueAppointmentList.asUnmodifiableObservableList(), groupedByTag);
        uniqueAppointmentList.sort(COMPARATOR_GROUP_PATIENT_APPOINTMENTS);
        assertEquals(uniqueAppointmentList.asUnmodifiableObservableList(), groupedByPatient);
        uniqueAppointmentList.sort(COMPARATOR_UNGROUP_APPOINTMENTS);
        assertEquals(uniqueAppointmentList.asUnmodifiableObservableList(), ungrouped);
    }
}
