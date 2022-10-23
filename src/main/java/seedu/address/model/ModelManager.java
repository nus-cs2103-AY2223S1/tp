package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
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
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Appointment> filteredAppointments;
    private final FilteredList<Bill> filteredBills;
    private final ArrayList<ReadOnlyAddressBook> addressBookHistory;
    private final ArrayList<ReadOnlyAddressBook> redoAddressBookHistory;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.addressBook.getPatientList());
        filteredAppointments = new FilteredList<>(this.addressBook.getAppointmentList());
        filteredBills = new FilteredList<>(this.addressBook.getBillList());
        this.addressBookHistory = new ArrayList<>();
        this.redoAddressBookHistory = new ArrayList<>();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return addressBook.hasPatient(patient);
    }

    @Override
    public boolean hasPatient(Name name) {
        requireNonNull(name);
        return addressBook.getPatientList().stream().anyMatch(patient -> patient.getName().equals(name));
    }

    @Override
    public void deletePatient(Patient target) {
        deleteRelativeAppointments(target);
        addressBook.removePatient(target);
    }

    @Override
    public void addPatient(Patient patient) {
        addressBook.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        addressBook.setPatient(target, editedPatient);
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return addressBook.hasAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment target) {
        deleteRelativeBills(target);
        addressBook.removeAppointment(target);
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
        addressBook.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);
        addressBook.setAppointment(target, editedAppointment);
    }

    /**
     * Returns true if a bill with the same identity as {@code bill} exists in the address book.
     * Always returns false since assumption that bills will not be duplicate
     * @param bill
     */
    @Override
    public boolean hasBill(Bill bill) {
        requireNonNull(bill);
        return addressBook.getBillList().stream().anyMatch(b -> b.isSameBill(bill));
    }

    /**
     * Deletes the given bill.
     * The bill must exist in the address book.
     *
     * @param target
     */
    @Override
    public void deleteBill(Bill target) {
        addressBook.removeBill(target);
    }

    /**
     * Adds the given bill.
     * {@code bill} must not already exist in the address book.
     *
     * @param bill
     */
    @Override
    public void addBill(Bill bill) {
        addressBook.addBill(bill);
    }

    /**
     * Replaces the given bill {@code target} with {@code editedBill}.
     * {@code target} must exist in the address book.
     * The bill identity of {@code editedBill} must not be the same as
     * another existing bill in the address book.
     *
     * @param target
     * @param editedBill
     */
    @Override
    public void setBill(Bill target, Bill editedBill) {
        requireAllNonNull(target, editedBill);
        addressBook.setBill(target, editedBill);
    }

    //=========== Filtered Patient List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    //=========== Filtered Appointment List Accessors =============================================================

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    @Override
    public void deleteRelativeAppointments(Patient patient) {
        List<Appointment> toDelete = addressBook.getAppointmentList().stream()
                .filter(a -> a.getName().equals(patient.getName())).collect(Collectors.toList());
        toDelete.stream().forEach(a -> deleteAppointment(a));
    }

    //=========== Filtered Bill List Accessors =============================================================

    @Override
    public ObservableList<Bill> getFilteredBillList() {
        return filteredBills;
    }

    @Override
    public void updateFilteredBillList(Predicate<Bill> predicate) {
        requireNonNull(predicate);
        filteredBills.setPredicate(predicate);
    }

    @Override
    public void deleteRelativeBills(Appointment appointment) {
        List<Bill> toDelete = addressBook.getBillList().stream()
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPatients.equals(other.filteredPatients);
    }

    @Override
    public void sortPatients(Comparator<Patient> comparator, boolean isAscending) {
        this.addressBook.sortPatients(comparator, isAscending);
    }

    @Override
    public void sortBills(Comparator<Bill> comparator, boolean isAscending) {
        this.addressBook.sortBills(comparator, isAscending);
    }

    @Override
    public void sortAppointments(Comparator<Appointment> comparator, boolean isAscending) {
        this.addressBook.sortAppointments(comparator, isAscending);
    }

    @Override
    public void updateAddressBookHistory() {
        this.addressBookHistory.add(new AddressBook(this.addressBook));
    }

    @Override
    public void updateRedoAddressBookHistory() {
        this.redoAddressBookHistory.add(new AddressBook(this.addressBook));
    }

    @Override
    public void undo() throws CommandException {
        try {
            this.updateRedoAddressBookHistory();
            setAddressBook(this.addressBookHistory.get(this.addressBookHistory.size() - 2));
            this.addressBookHistory.remove(this.addressBookHistory.size() - 1);
            this.addressBookHistory.remove(this.addressBookHistory.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            this.redoAddressBookHistory.remove(this.redoAddressBookHistory.size() - 1);
            throw new CommandException("Undo cannot be done as there was no previous action");
        }

    }

    @Override
    public void redo() throws CommandException {
        try {
            setAddressBook(this.redoAddressBookHistory.get(this.redoAddressBookHistory.size() - 1));
            this.redoAddressBookHistory.remove(this.redoAddressBookHistory.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Redo cannot be done as there was no previous action");
        }

    }

    @Override
    public void deleteAddressBookHistory() {
        this.addressBookHistory.remove(addressBookHistory.size() - 1);
    }

}
