package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCTOR_CAITIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCTOR_DECKER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_TEST_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_TEST_8;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SLOT_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SLOT_8;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalPatients.CARL;
import static seedu.address.testutil.TypicalPatients.DANIEL;
import static seedu.address.testutil.TypicalPatients.ELLE;
import static seedu.address.testutil.TypicalPatients.FIONA;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsHealthContact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.HealthContact;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment APPOINTMENT_1 = new AppointmentBuilder().withName(ALICE.getName().toString())
            .withMedicalTest("gastric fluid analysis").withSlot("2022-11-01 08:00")
            .withDoctor("Edith Hilton").build();
    public static final Appointment APPOINTMENT_2 = new AppointmentBuilder().withName(BENSON.getName().toString())
            .withMedicalTest("kidney function test").withSlot("2023-01-21 08:30")
            .withDoctor("Milly Everett").build();
    public static final Appointment APPOINTMENT_3 = new AppointmentBuilder().withName(CARL.getName().toString())
            .withMedicalTest("liver function test").withSlot("2022-11-01 08:00")
            .withDoctor("Fannie Lambert").build();
    public static final Appointment APPOINTMENT_4 = new AppointmentBuilder().withName(DANIEL.getName().toString())
            .withMedicalTest("lumbar puncture").withSlot("2022-11-01 08:00")
            .withDoctor("Zainab Gibbons").build();
    public static final Appointment APPOINTMENT_5 = new AppointmentBuilder().withName(ELLE.getName().toString())
            .withMedicalTest("malabsorption test").withSlot("2022-11-01 08:00")
            .withDoctor("Nadia Guzman").build();
    public static final Appointment APPOINTMENT_6 = new AppointmentBuilder().withName(FIONA.getName().toString())
            .withMedicalTest("pap smear").withSlot("2022-11-01 08:00")
            .withDoctor("Lucy Poole").build();

    // Manually added - Appointment's details found in {@code CommandTestUtil}
    public static final Appointment APPOINTMENT_7 = new AppointmentBuilder().withName(VALID_NAME_AMY)
            .withMedicalTest(VALID_MEDICAL_TEST_7).withSlot(VALID_SLOT_7)
            .withDoctor(VALID_DOCTOR_CAITIE).build();
    public static final Appointment APPOINTMENT_8 = new AppointmentBuilder().withName(VALID_NAME_BOB)
            .withMedicalTest(VALID_MEDICAL_TEST_8).withSlot(VALID_SLOT_8)
            .withDoctor(VALID_DOCTOR_DECKER).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns an {@code HealthContact} with all the typical patients.
     */
    public static HealthContact getTypicalAppointmentsHealthContact() {
        HealthContact ab = getTypicalPatientsHealthContact();

        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(APPOINTMENT_1, APPOINTMENT_2, APPOINTMENT_3,
                APPOINTMENT_4, APPOINTMENT_5, APPOINTMENT_6));
    }
}
