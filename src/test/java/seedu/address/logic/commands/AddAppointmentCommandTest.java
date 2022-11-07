package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_2;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.HealthContact;
import seedu.address.model.History;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHealthContact;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.bill.Bill;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.PatientBuilder;

public class AddAppointmentCommandTest {

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = new AppointmentBuilder().build();
        modelStub.addPatient(new PatientBuilder().withName(validAppointment.getName().toString()).build());

        CommandResult commandResult = new AddAppointmentCommand(validAppointment).execute(modelStub);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() throws CommandException {
        Appointment validAppointment = new AppointmentBuilder().build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithAppointment(validAppointment);
        assertThrows(CommandException.class,
                AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT, () -> addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void execute_nameCaseNotMatch_throwsCommandException() throws CommandException {
        Appointment validAppointment = new AppointmentBuilder().build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithAppointment(new AppointmentBuilder(validAppointment)
                .withName(validAppointment.getName().fullName.toLowerCase()).build());
        assertThrows(CommandException.class,
                AddAppointmentCommand.MESSAGE_PATIENT_NAME_CASE_UNMATCHED, () -> addAppointmentCommand
                        .execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment alice = new AppointmentBuilder(APPOINTMENT_1).withName("Alice").build();
        Appointment bob = new AppointmentBuilder(APPOINTMENT_2).withName("Bob").build();
        AddAppointmentCommand addAliceCommand = new AddAppointmentCommand(alice);
        AddAppointmentCommand addBobCommand = new AddAppointmentCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddAppointmentCommand addAliceCommandCopy = new AddAppointmentCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different appointment -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getHealthContactFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHealthContactFilePath(Path healthContactFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void selectPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void selectAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHealthContact(ReadOnlyHealthContact newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyHealthContact getHealthContact() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatient(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePatient(Patient target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatient(Patient target, Patient editedPatient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<? super Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<? super Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortPatients(Comparator<Patient> comparator, boolean isAscending) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortAppointments(Comparator<Appointment> comparator, boolean isAscending) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortBills(Comparator<Bill> comparator, boolean isAscending) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRelativeAppointments(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBill(Bill bill) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBill(Bill target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBill(Bill bill) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBill(Bill target, Bill editedBill) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Bill> getFilteredBillList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBillList(Predicate<? super Bill> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBillAsUnpaid(Bill bill) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRelativeBills(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateHealthContactHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateRedoHealthContactHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBillAsPaid(Bill bill) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public History getHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatientWithExactlySameName(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatientWithExactlySameName(Name name) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single patient.
     */
    private class ModelStubWithAppointment extends ModelStub {
        private final Name name;
        private final Appointment appointment;

        ModelStubWithAppointment(Appointment appointment) {
            requireNonNull(appointment);
            this.name = appointment.getName();
            this.appointment = appointment;
        }

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return this.name.isSameName(patient.getName());
        }

        @Override
        public boolean hasPatient(Name name) {
            requireNonNull(name);
            return this.name.isSameName(name);
        }

        @Override
        public boolean hasPatientWithExactlySameName(Patient patient) {
            requireNonNull(patient);
            return patient.getName().equals(this.name.fullName);
        }

        @Override
        public boolean hasPatientWithExactlySameName(Name name) {
            requireNonNull(this.name);
            return name.equals(this.name);
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.isSameAppointment(appointment);
        }
    }

    /**
     * A Model stub that always accept the patient being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends ModelStub {
        final ArrayList<Name> patientsAdded = new ArrayList<>();
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            patientsAdded.add(appointment.getName());
            appointmentsAdded.add(appointment);
        }

        @Override
        public boolean hasPatient(Name name) {
            requireNonNull(name);
            return patientsAdded.stream().anyMatch(p -> name.isSameName(p));
        }

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return patientsAdded.stream().anyMatch(p -> patient.getName().isSameName(p));
        }

        @Override
        public boolean hasPatientWithExactlySameName(Patient patient) {
            requireNonNull(patient);
            return patientsAdded.stream().anyMatch(p -> patient.getName().equals(p.fullName));
        }

        @Override
        public boolean hasPatientWithExactlySameName(Name name) {
            requireNonNull(name);
            return patientsAdded.stream().anyMatch(n -> n.equals(name));
        }


        @Override
        public void addPatient(Patient patient) {
            requireNonNull(patient);
            patientsAdded.add(patient.getName());
        }

        @Override
        public ReadOnlyHealthContact getHealthContact() {
            return new HealthContact();
        }
    }

}
