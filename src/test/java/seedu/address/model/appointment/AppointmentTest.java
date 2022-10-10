package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCTOR_DECKER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_TEST_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_TEST_8;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SLOT_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SLOT_8;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_2;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_3;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_4;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void isSameAppointment() {
        // same object -> returns true
        assertTrue(APPOINTMENT_1.isSameAppointment(APPOINTMENT_1));

        // null -> returns false
        assertFalse(APPOINTMENT_1.isSameAppointment(null));

        // same name, all other attributes different -> returns false
        Appointment editedAppointment1 = new AppointmentBuilder(APPOINTMENT_1).withMedicalTest(VALID_MEDICAL_TEST_8)
                .withSlot(VALID_SLOT_8).withDoctor(VALID_DOCTOR_DECKER).build();
        assertFalse(APPOINTMENT_1.isSameAppointment(editedAppointment1));

        // all attributes same -> returns true
        editedAppointment1 = new AppointmentBuilder().withName(APPOINTMENT_1.getName().toString())
                .withMedicalTest(APPOINTMENT_1.getMedicalTest().toString())
                .withSlot(APPOINTMENT_1.getSlot().toString())
                .withDoctor(APPOINTMENT_1.getDoctor().toString()).build();
        assertTrue(APPOINTMENT_1.isSameAppointment(editedAppointment1));

        // name has trailing spaces, all other attributes same -> returns false
        String testWithTrailingSpaces = APPOINTMENT_1.getName().toString() + " ";
        editedAppointment1 = new AppointmentBuilder(APPOINTMENT_1).withName(testWithTrailingSpaces).build();
        assertFalse(APPOINTMENT_1.isSameAppointment(editedAppointment1));

        // medical test has trailing spaces, all other attributes same -> returns false
        testWithTrailingSpaces = APPOINTMENT_1.getMedicalTest().toString() + " ";
        editedAppointment1 = new AppointmentBuilder(APPOINTMENT_1).withMedicalTest(testWithTrailingSpaces).build();
        assertFalse(APPOINTMENT_1.isSameAppointment(editedAppointment1));

        // doctor has trailing spaces, all other attributes same -> returns false
        testWithTrailingSpaces = APPOINTMENT_1.getDoctor().toString() + " ";
        editedAppointment1 = new AppointmentBuilder(APPOINTMENT_1).withDoctor(testWithTrailingSpaces).build();
        assertFalse(APPOINTMENT_1.isSameAppointment(editedAppointment1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Appointment appointmentOneCopy = new AppointmentBuilder(APPOINTMENT_1).build();
        assertTrue(APPOINTMENT_1.isSameAppointment(appointmentOneCopy));

        // same object -> returns true
        assertTrue(APPOINTMENT_2.equals(APPOINTMENT_2));

        // null -> returns false
        assertFalse(APPOINTMENT_3.equals(null));

        // different type -> returns false
        assertFalse(APPOINTMENT_4.equals(5));

        // different appointments -> returns false
        assertFalse(APPOINTMENT_1.equals(APPOINTMENT_2));

        // different name -> returns false
        Appointment editedAppointment1 = new AppointmentBuilder(APPOINTMENT_1).withName(VALID_NAME_BOB).build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment1));

        // different medical test -> returns false
        editedAppointment1 = new AppointmentBuilder(APPOINTMENT_1).withMedicalTest(VALID_MEDICAL_TEST_7).build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment1));

        // different slot -> returns false
        editedAppointment1 = new AppointmentBuilder(APPOINTMENT_1).withSlot(VALID_SLOT_7).build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment1));

        // different doctor -> returns false
        editedAppointment1 = new AppointmentBuilder(APPOINTMENT_1).withMedicalTest(VALID_DOCTOR_DECKER).build();
        assertFalse(APPOINTMENT_1.equals(editedAppointment1));
    }
}
