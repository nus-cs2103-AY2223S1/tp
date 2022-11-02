package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.bill.Bill;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;

/**
 * Represents the in-memory model of the HealthContact data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final HealthContact healthContact;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Appointment> filteredAppointments;
    private final FilteredList<Bill> filteredBills;
    private final History history;

    /**
     * Initializes a ModelManager with the given healthContact and userPrefs.
     */
    public ModelManager(ReadOnlyHealthContact healthContact, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(healthContact, userPrefs);

        logger.fine("Initializing with HealthContact: " + healthContact + " and user prefs " + userPrefs);

        this.healthContact = new HealthContact(healthContact);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.healthContact.getPatientList());
        filteredAppointments = new FilteredList<>(this.healthContact.getAppointmentList());
        filteredBills = new FilteredList<>(this.healthContact.getBillList());
        this.history = new History(this);
    }

    public ModelManager() {
        this(new HealthContact(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getHealthContactFilePath() {
        return userPrefs.getHealthContactFilePath();
    }

    @Override
    public void setHealthContactFilePath(Path healthContactFilePath) {
        requireNonNull(healthContactFilePath);
        userPrefs.setHealthContactFilePath(healthContactFilePath);
    }

    //=========== HealthContact ================================================================================

    @Override
    public void setHealthContact(ReadOnlyHealthContact healthContact) {
        this.healthContact.resetData(healthContact);
    }

    @Override
    public ReadOnlyHealthContact getHealthContact() {
        return healthContact;
    }

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return healthContact.hasPatient(patient);
    }

    @Override
    public boolean hasPatient(Name name) {
        requireNonNull(name);
        return healthContact.getPatientList().stream().anyMatch(patient -> patient.getName().equals(name));
    }

    @Override
    public void deletePatient(Patient target) {
        deleteRelativeAppointments(target);
        healthContact.removePatient(target);
    }

    @Override
    public void addPatient(Patient patient) {
        healthContact.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        getHealthContact().getAppointmentList().stream()
                .filter(appointment -> appointment.getName().equals(target.getName()))
                .forEach(appointment -> setAppointment(appointment,
                        new Appointment(editedPatient.getName(), appointment.getMedicalTest(),
                                appointment.getSlot(), appointment.getDoctor())));

        healthContact.setPatient(target, editedPatient);
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return healthContact.hasAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment target) {
        deleteRelativeBills(target);
        healthContact.removeAppointment(target);
    }

    @Override
    public void selectPatient(Patient patient) {
        updateFilteredAppointmentList(appointment -> appointment.getName().equals(patient.getName()));
        updateFilteredBillList(bill -> bill.getAppointment().getName().equals(patient.getName()));
    }

    @Override
    public void selectAppointment(Appointment appointment) {
        updateFilteredBillList(bill -> bill.getAppointment().equals(appointment));
    }

    @Override
    public void addAppointment(Appointment appointment) {
        healthContact.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);
        getHealthContact().getBillList().stream()
                .filter(bill -> bill.getAppointment().isSameAppointment(target))
                .forEach(bill -> setBill(bill, new Bill(
                        new Appointment(editedAppointment.getName(), editedAppointment.getMedicalTest(),
                                editedAppointment.getSlot(), editedAppointment.getDoctor()),
                        bill.getAmount(),
                        bill.getBillDate(),
                        bill.getPaymentStatus())));
        healthContact.setAppointment(target, editedAppointment);
    }

    /**
     * Returns true if a bill with the same identity as {@code bill} exists in the HealthContact.
     * Always returns false since assumption that bills will not be duplicate
     * @param bill
     */
    @Override
    public boolean hasBill(Bill bill) {
        requireNonNull(bill);
        return healthContact.getBillList().stream().anyMatch(b -> b.isSameBill(bill));
    }

    /**
     * Deletes the given bill.
     * The bill must exist in the HealthContact.
     *
     * @param target
     */
    @Override
    public void deleteBill(Bill target) {
        healthContact.removeBill(target);
    }

    /**
     * Adds the given bill.
     * {@code bill} must not already exist in the HealthContact.
     *
     * @param bill
     */
    @Override
    public void addBill(Bill bill) {
        healthContact.addBill(bill);
        updateFilteredBillList(PREDICATE_SHOW_ALL_BILLS);
    }

    /**
     * Replaces the given bill {@code target} with {@code editedBill}.
     * {@code target} must exist in the HealthContact.
     * The bill identity of {@code editedBill} must not be the same as
     * another existing bill in the HealthContact.
     *
     * @param target
     * @param editedBill
     */
    @Override
    public void setBill(Bill target, Bill editedBill) {
        requireAllNonNull(target, editedBill);
        healthContact.setBill(target, editedBill);
    }

    //=========== Filtered Patient List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedHealthContact}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public void updateFilteredPatientList(Predicate<? super Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    //=========== Filtered Appointment List Accessors =============================================================

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<? super Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    @Override
    public void deleteRelativeAppointments(Patient patient) {
        List<Appointment> toDelete = healthContact.getAppointmentList().stream()
                .filter(a -> a.getName().equals(patient.getName())).collect(Collectors.toList());
        toDelete.stream().forEach(a -> deleteAppointment(a));
    }

    //=========== Filtered Bill List Accessors =============================================================

    @Override
    public ObservableList<Bill> getFilteredBillList() {
        return filteredBills;
    }

    @Override
    public void updateFilteredBillList(Predicate<? super Bill> predicate) {
        requireNonNull(predicate);
        filteredBills.setPredicate(predicate);
    }

    @Override
    public void deleteRelativeBills(Appointment appointment) {
        List<Bill> toDelete = healthContact.getBillList().stream()
                .filter(b -> b.getAppointment().equals(appointment)).collect(Collectors.toList());
        toDelete.stream().forEach(b -> deleteBill(b));
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return healthContact.equals(other.healthContact)
                && userPrefs.equals(other.userPrefs)
                && filteredPatients.equals(other.filteredPatients);
    }

    @Override
    public void sortPatients(Comparator<Patient> comparator, boolean isAscending) {
        this.healthContact.sortPatients(comparator, isAscending);
    }

    @Override
    public void sortBills(Comparator<Bill> comparator, boolean isAscending) {
        this.healthContact.sortBills(comparator, isAscending);
    }

    /**
     * Sets the bill in the HealthContact data as UNPAID
     * @param bill
     */
    @Override
    public void setBillAsUnpaid(Bill bill) {
        this.healthContact.setBillAsUnpaid(bill);
    }

    @Override
    public void sortAppointments(Comparator<Appointment> comparator, boolean isAscending) {
        this.healthContact.sortAppointments(comparator, isAscending);
    }

    @Override
    public void updateHealthContactHistory() {
        history.addHealthContactHistory(new HealthContact(this.healthContact));
    }

    @Override
    public void updateRedoHealthContactHistory() {
        history.addRedoHealthContactHistory(new HealthContact(this.healthContact));
    }

    @Override
    public void undo() throws CommandException {
        try {
            boolean shouldNotAdd = history.compareHealthContactHistory(this.healthContact)
                    && !(this.filteredPatients.getPredicate() == null
                    && this.filteredAppointments.getPredicate() == null
                    && this.filteredBills.getPredicate() == null);
            if (!shouldNotAdd) {
                history.updateRedoHealthContactHistory();
                history.updateRedoPatientsHistory();
                history.updateRedoAppointmentsHistory();
                history.updateRedoBillsHistory();
                setHealthContact(history.getHealthContactHistory(history.getHealthContactHistorySize() - 2));
                history.deleteHealthContactHistory(history.getHealthContactHistorySize() - 1);
                filteredPatients.setPredicate(history.getPatientsPredicate(history.getPatientsHistorySize() - 1));
                filteredAppointments.setPredicate(history
                        .getAppointmentsPredicate(history.getAppointmentsHistorySize() - 1));
                filteredBills.setPredicate(history.getBillsPredicate(history.getBillsHistorySize() - 1));
                history.deleteHealthContactHistory(history.getHealthContactHistorySize() - 1);
            } else {
                try {
                    history.deleteHealthContactHistory(history.getHealthContactHistorySize() - 1);
                    history.updateRedoHealthContactHistory();
                    history.updateRedoPatientsHistory();
                    history.updateRedoAppointmentsHistory();
                    history.updateRedoBillsHistory();
                    int pointer = 2;
                    while (history.getHealthContactHistory(history.getHealthContactHistorySize() - 1)
                            .equals(history.getHealthContactHistory(history.getHealthContactHistorySize() - pointer))) {
                        pointer = pointer + 1;
                    }
                    setHealthContact(history.getHealthContactHistory(history.getHealthContactHistorySize() - pointer));
                    filteredPatients.setPredicate(history.getPatientsPredicate(history.getPatientsHistorySize() - pointer));
                    filteredAppointments.setPredicate(history
                            .getAppointmentsPredicate(history.getAppointmentsHistorySize() - pointer));
                    filteredBills.setPredicate(history.getBillsPredicate(history.getBillsHistorySize() - pointer));
                    for (int i = 0; i < pointer - 1; i++) {
                        history.deleteHealthContactHistory(history.getHealthContactHistorySize() - 1);
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new CommandException("Undo cannot be done as there was no previous action");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Undo cannot be done as there was no previous action");
        }
    }

    @Override
    public void redo() throws CommandException {
        try {
            setHealthContact(history.getRedoHealthContactHistory(history.getRedoHealthContactHistorySize() - 1));
            filteredPatients.setPredicate(history.getRedoPatientsPredicate(history.getRedoPatientsHistorySize() - 1));
            filteredAppointments.setPredicate(history.getRedoAppointmentsPredicate(history
                    .getRedoAppointmentsHistorySize() - 1));
            filteredBills.setPredicate(history.getRedoBillsPredicate(history.getRedoBillsHistorySize() - 1));
            history.deleteRedoHealthContactHistory(history.getRedoHealthContactHistorySize() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Redo cannot be done as there was no previous action");
        }

    }


    @Override
    public void setBillAsPaid(Bill bill) {
        this.healthContact.setBillAsPaid(bill);
    }

    @Override
    public History getHistory() {
        return history;
    }

}
