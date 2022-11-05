package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
import seedu.address.testutil.BillBuilder;


public class AddBillCommandTest {
    @Test
    public void constructor_nullBill_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBillCommand(null, null, null));
    }

    @Test
    public void execute_billAcceptedByModel_addSuccessful() throws Exception {
        AddBillCommandTest.ModelStubAcceptingBillAdded modelStub = new AddBillCommandTest.ModelStubAcceptingBillAdded();
        Bill validBill = new BillBuilder().build();

        CommandResult commandResult = new AddBillCommand(validBill).execute(modelStub);

        assertEquals(String.format(AddBillCommand.MESSAGE_SUCCESS, validBill), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBill), modelStub.billsAdded);
    }

    @Test
    public void execute_duplicateBill_throwsCommandException() {
        Bill validBill = new BillBuilder().build();
        AddBillCommand addBillCommand = new AddBillCommand(validBill);
        AddBillCommandTest.ModelStub modelStub = new AddBillCommandTest.ModelStubWithBill(validBill);

        assertThrows(CommandException.class,
                AddBillCommand.MESSAGE_DUPLICATE_BILL, () -> addBillCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Bill bill = new BillBuilder().build();
        Bill otherBill = new BillBuilder().withAppointment(new AppointmentBuilder().build()).build();
        AddBillCommand addBillCommand = new AddBillCommand(bill);
        AddBillCommand addOtherBillCommand = new AddBillCommand(otherBill);
        // same object -> returns true
        assertTrue(addBillCommand.equals(addBillCommand));

        // same values -> returns true
        AddBillCommand addBillCommandCopy = new AddBillCommand(bill);
        assertTrue(addBillCommand.equals(addBillCommandCopy));

        // different types -> returns false
        assertFalse(addBillCommand.equals(1));

        // null -> returns false
        assertFalse(addBillCommand.equals(null));

        // different bill -> returns false
        assertFalse(addBillCommand.equals(addOtherBillCommand));


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
    }

    /**
     * A Model stub that contains a single bill and a single appointment.
     */
    private class ModelStubWithBill extends AddBillCommandTest.ModelStub {
        private final Bill bill;
        private final Appointment appointment;

        ModelStubWithBill(Bill bill) {
            requireNonNull(bill);
            this.bill = bill;
            this.appointment = new AppointmentBuilder().build();
        }

        @Override
        public boolean hasBill(Bill bill) {
            requireNonNull(bill);
            return this.bill.isSameBill(bill);
        }
    }

    /**
     * A Model stub that always accept the patient being added.
     */
    private class ModelStubAcceptingBillAdded extends AddBillCommandTest.ModelStub {
        final ArrayList<Bill> billsAdded = new ArrayList<>();

        @Override
        public boolean hasBill(Bill bill) {
            requireNonNull(bill);
            return billsAdded.stream().anyMatch(bill::isSameBill);
        }

        @Override
        public void addBill(Bill bill) {
            requireNonNull(bill);
            billsAdded.add(bill);
        }

        @Override
        public ReadOnlyHealthContact getHealthContact() {
            return new HealthContact();
        }
    }
}
