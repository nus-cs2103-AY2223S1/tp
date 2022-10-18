package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.testutil.AppointmentBuilder;

public class UniqueAppointmentListTest {

    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.contains(null));
    }

    @Test
    public void containsAppointment_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.contains(APPOINTMENT_1));
    }

    @Test
    public void containsAppointment_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        assertTrue(uniqueAppointmentList.contains(APPOINTMENT_1));
    }

    @Test
    public void contains_appointmentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        Appointment editedAppointment1 = new AppointmentBuilder(APPOINTMENT_1)
                .build();
        assertTrue(uniqueAppointmentList.contains(editedAppointment1));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(APPOINTMENT_1));
    }

    @Test
    public void setAppointment_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList
                .setAppointment(null, APPOINTMENT_1));
    }

    @Test
    public void setAppointment_nullEditedAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList
                .setAppointment(APPOINTMENT_1, null));
    }

    @Test
    public void setAppointment_targetAppointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList
                .setAppointment(APPOINTMENT_1, APPOINTMENT_1));
    }

    @Test
    public void setAppointment_editedAppointmentIsSameAppointment_success() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        uniqueAppointmentList.setAppointment(APPOINTMENT_1, APPOINTMENT_1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT_1);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasSameIdentity_success() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        Appointment editedAppointment1 = new AppointmentBuilder()
                .withSlot(APPOINTMENT_1.getSlot().toString())
                .withDoctor(APPOINTMENT_1.getDoctor().toString())
                .withMedicalTest(APPOINTMENT_1.getMedicalTest().toString())
                .withName(APPOINTMENT_1.getName().toString())
                .build();
        uniqueAppointmentList.setAppointment(APPOINTMENT_1, editedAppointment1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(editedAppointment1);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasDifferentIdentity_success() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        uniqueAppointmentList.setAppointment(APPOINTMENT_1, APPOINTMENT_2);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT_2);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasNonUniqueIdentity_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        uniqueAppointmentList.add(APPOINTMENT_2);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList
                .setAppointment(APPOINTMENT_1, APPOINTMENT_2));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.remove(APPOINTMENT_1));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        uniqueAppointmentList.remove(APPOINTMENT_1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullUniqueAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList
                .setAppointments((UniqueAppointmentList) null));
    }

    @Test
    public void setAppointments_uniqueAppointmentList_replacesOwnListWithProvidedUniqueAppointmentList() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT_2);
        uniqueAppointmentList.setAppointments(expectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList
                .setAppointments((List<Appointment>) null));
    }

    @Test
    public void setAppointments_list_replacesOwnListWithProvidedList() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        List<Appointment> appointmentList = Collections.singletonList(APPOINTMENT_2);
        uniqueAppointmentList.setAppointments(appointmentList);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT_2);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_listWithDuplicateAppointments_throwsDuplicateAppointmentException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(APPOINTMENT_1, APPOINTMENT_1);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList
                .setAppointments(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAppointmentList.asUnmodifiableObservableList().remove(0));
    }
}
