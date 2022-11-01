package seedu.address.model;

import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.bill.Bill;
import seedu.address.model.patient.Patient;

/**
 * The API of the History component.
 */
public class History {
    private final ArrayList<ReadOnlyHealthContact> healthContactHistory;
    private final ArrayList<ReadOnlyHealthContact> redoHealthContactHistory;
    private final ArrayList<FilteredList<Patient>> filteredPatientsHistory;
    private final ArrayList<FilteredList<Appointment>> filteredAppointmentsHistory;
    private final ArrayList<FilteredList<Bill>> filteredBillsHistory;
    private final ArrayList<FilteredList<Patient>> redoFilteredPatientsHistory;
    private final ArrayList<FilteredList<Appointment>> redoFilteredAppointmentsHistory;
    private final ArrayList<FilteredList<Bill>> redoFilteredBillsHistory;
    private final Model model;

    /**
     * Creates a new History object.
     */
    public History(Model model) {
        healthContactHistory = new ArrayList<>();
        redoHealthContactHistory = new ArrayList<>();
        filteredPatientsHistory = new ArrayList<>();
        filteredAppointmentsHistory = new ArrayList<>();
        filteredBillsHistory = new ArrayList<>();
        redoFilteredPatientsHistory = new ArrayList<>();
        redoFilteredAppointmentsHistory = new ArrayList<>();
        redoFilteredBillsHistory = new ArrayList<>();
        this.model = model;
    }

    /**
     * Adds a new state to the history.
     */
    public void updateHealthContactHistory() {
        this.healthContactHistory.add(new HealthContact(model.getHealthContact()));
        this.filteredAppointmentsHistory.add((FilteredList<Appointment>) model.getFilteredAppointmentList());
        this.filteredPatientsHistory.add((FilteredList<Patient>) model.getFilteredPatientList());
        this.filteredBillsHistory.add((FilteredList<Bill>) model.getFilteredBillList());
    }

    public void updateRedoHealthContactHistory() {
        this.redoHealthContactHistory.add(new HealthContact(model.getHealthContact()));
    }

    public void clearRedoHealthContactHistory() {
        this.redoHealthContactHistory.clear();
    }

    public void deleteHealthContactHistory(int index) {
        this.healthContactHistory.remove(index);
    }

    public ReadOnlyHealthContact getHealthContactHistory(int index) {
        return healthContactHistory.get(index);
    }

    public ReadOnlyHealthContact getRedoHealthContactHistory(int index) {
        return redoHealthContactHistory.get(index);
    }

    public void deleteRedoHealthContactHistory(int index) {
        this.redoHealthContactHistory.remove(index);
    }

    public int getHealthContactHistorySize() {
        return this.healthContactHistory.size();
    }

    public int getRedoHealthContactHistorySize() {
        return this.redoHealthContactHistory.size();
    }

    public void addHealthContactHistory(ReadOnlyHealthContact healthContact) {
        this.healthContactHistory.add(healthContact);
    }

    public void addRedoHealthContactHistory(ReadOnlyHealthContact healthContact) {
        this.redoHealthContactHistory.add(healthContact);
    }

    public boolean compareHealthContactHistory(ReadOnlyHealthContact healthContact) {
        return this.healthContactHistory.get(this.healthContactHistory.size() - 2).equals(healthContact);
    }

    public void clearRedoPatientsHistory() {
        this.redoFilteredPatientsHistory.clear();
    }

    public void clearRedoAppointmentsHistory() {
        this.redoFilteredAppointmentsHistory.clear();
    }

    public void clearRedoBillsHistory() {
        this.redoFilteredBillsHistory.clear();
    }

    public void deletePatientsHistory(int index) {
        this.filteredPatientsHistory.remove(index);
    }

    public void deleteAppointmentsHistory(int index) {
        this.filteredAppointmentsHistory.remove(index);
    }

    public void deleteBillsHistory(int index) {
        this.filteredBillsHistory.remove(index);
    }

    public int getPatientsHistorySize() {
        return this.filteredPatientsHistory.size();
    }

    public int getAppointmentsHistorySize() {
        return this.filteredAppointmentsHistory.size();
    }

    public int getBillsHistorySize() {
        return this.filteredBillsHistory.size();
    }

    public void updateRedoPatientsHistory() {
        this.redoFilteredPatientsHistory.add((FilteredList<Patient>) model.getFilteredPatientList());
    }

    public void updateRedoAppointmentsHistory() {
        this.redoFilteredAppointmentsHistory.add((FilteredList<Appointment>) model.getFilteredAppointmentList());
    }

    public void updateRedoBillsHistory() {
        this.redoFilteredBillsHistory.add((FilteredList<Bill>) model.getFilteredBillList());
    }

    public Predicate<? super Patient> getPatientsPredicate(int index) {
        return this.filteredPatientsHistory.get(index).getPredicate();
    }

    public Predicate<? super Appointment> getAppointmentsPredicate(int index) {
        return this.filteredAppointmentsHistory.get(index).getPredicate();
    }

    public Predicate<? super Bill> getBillsPredicate(int index) {
        return this.filteredBillsHistory.get(index).getPredicate();
    }

    public int getRedoPatientsHistorySize() {
        return this.redoFilteredPatientsHistory.size();
    }

    public int getRedoAppointmentsHistorySize() {
        return this.redoFilteredAppointmentsHistory.size();
    }

    public int getRedoBillsHistorySize() {
        return this.redoFilteredBillsHistory.size();
    }

    public Predicate<? super Patient> getRedoPatientsPredicate(int index) {
        return this.redoFilteredPatientsHistory.get(index).getPredicate();
    }

    public Predicate<? super Appointment> getRedoAppointmentsPredicate(int index) {
        return this.redoFilteredAppointmentsHistory.get(index).getPredicate();
    }

    public Predicate<? super Bill> getRedoBillsPredicate(int index) {
        return this.redoFilteredBillsHistory.get(index).getPredicate();
    }

    public FilteredList<? super Patient> getFilteredPatientList() {
        return this.filteredPatientsHistory.get(this.filteredPatientsHistory.size() - 2);
    }

    public FilteredList<? super Appointment> getFilteredAppointmentList() {
        return this.filteredAppointmentsHistory.get(this.filteredAppointmentsHistory.size() - 2);
    }

    public FilteredList<? super Bill> getFilteredBillList() {
        return this.filteredBillsHistory.get(this.filteredBillsHistory.size() - 2);
    }
}
