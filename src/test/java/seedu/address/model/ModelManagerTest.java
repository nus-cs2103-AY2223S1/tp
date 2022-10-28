package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BILLS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_2;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_3;
import static seedu.address.testutil.TypicalBills.BILL_1;
import static seedu.address.testutil.TypicalBills.BILL_2;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.NameContainsKeywordsPredicatePatient;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.HealthContactBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new HealthContact(), new HealthContact(modelManager.getHealthContact()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setHealthContactFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setHealthContactFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setHealthContactFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setHealthContactFilePath(null));
    }

    @Test
    public void setHealthContactFilePath_validPath_setsHealthContactFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setHealthContactFilePath(path);
        assertEquals(path, modelManager.getHealthContactFilePath());
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPatient((Patient) null));
    }

    @Test
    public void hasPatient_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPatient((Name) null));
    }

    @Test
    public void hasPatient_patientNotInHealthContact_returnsFalse() {
        assertFalse(modelManager.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_patientInHealthContact_returnsTrue() {
        modelManager.addPatient(ALICE);
        assertTrue(modelManager.hasPatient(ALICE));
    }

    @Test
    public void getFilteredPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPatientList().remove(0));
    }

    @Test
    public void hasAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAppointment((Appointment) null));
    }

    @Test
    public void hasAppointment_appointmentNotInHealthContact_returnsFalse() {
        assertFalse(modelManager.hasAppointment(APPOINTMENT_1));
    }

    @Test
    public void hasAppointment_appointmentInHealthContact_returnsTrue() {
        modelManager.addAppointment(APPOINTMENT_1);
        assertTrue(modelManager.hasAppointment(APPOINTMENT_1));
    }

    @Test
    public void getFilteredAppointmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
                UnsupportedOperationException.class, () -> modelManager.getFilteredAppointmentList().remove(0));
    }

    @Test
    public void equals() {
        HealthContact healthContact = new HealthContactBuilder()
                .withPatient(ALICE).withPatient(BENSON)
                .withAppointment(APPOINTMENT_1).withAppointment(APPOINTMENT_2).withAppointment(APPOINTMENT_3)
                .withBill(BILL_1).withBill(BILL_2)
                .build();
        HealthContact differentHealthContact = new HealthContact();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(healthContact, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(healthContact, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different healthContact -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentHealthContact, userPrefs)));

        // different filteredPatientList -> returns false
        String[] patientKeywords = ALICE.getName().fullName.split("\\s+");

        String predicateName = patientKeywords[0];
        for (int i = 1; i < patientKeywords.length; i++) {
            predicateName += " " + patientKeywords[i];
        }

        modelManager.updateFilteredPatientList(
                new NameContainsKeywordsPredicatePatient(predicateName));
        assertFalse(modelManager.equals(new ModelManager(healthContact, userPrefs)));

        // different filteredAppointmentList -> returns false TODO

        // different filteredBillList -> returns false TODO

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        modelManager.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        modelManager.updateFilteredBillList(PREDICATE_SHOW_ALL_BILLS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setHealthContactFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(healthContact, differentUserPrefs)));
    }
}
