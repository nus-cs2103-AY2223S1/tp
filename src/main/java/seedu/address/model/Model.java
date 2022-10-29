package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.bill.Bill;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;
    Predicate<Bill> PREDICATE_SHOW_ALL_BILLS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' HealthContact file path.
     */
    Path getHealthContactFilePath();

    /**
     * Sets the user prefs' HealthContact file path.
     */
    void setHealthContactFilePath(Path healthContactFilePath);

    /**
     * Replaces HealthContact data with the data in {@code healthContact}.
     */
    void setHealthContact(ReadOnlyHealthContact healthContact);

    /** Returns the HealthContact */
    ReadOnlyHealthContact getHealthContact();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the HealthContact.
     */
    boolean hasPatient(Patient patient);

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the HealthContact.
     */
    boolean hasPatient(Name name);

    /**
     * Deletes the given patient.
     * The patient must exist in the HealthContact.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the HealthContact.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the HealthContact.
     * The patient identity of {@code editedPatient} must not be the same as
     * another existing patient in the HealthContact.
     */
    void setPatient(Patient target, Patient editedPatient);

    /**
     * Updates the FilteredAppointmentList and FilteredBillList
     * so that it includes the given patient's appointments and bills only.
     * @param patient The given patient.
     */
    void selectPatient(Patient patient);

    /**
     * Updates the FilteredBillList so that it includes the given appointments' bill only.
     * @param appointment The given patient.
     */
    void selectAppointment(Appointment appointment);

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<? super Patient> predicate);

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the HealthContact.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Deletes the given appointment.
     * The appointment must exist in the HealthContact.
     */
    void deleteAppointment(Appointment target);

    /**
     * Adds the given appointment.
     * {@code appointment} must not already exist in the HealthContact.
     */
    void addAppointment(Appointment appointment);

    /**
     * Replaces the given appointment {@code target} with {@code editedAppointment}.
     * {@code target} must exist in the HealthContact.
     * The appointment identity of {@code editedAppointment} must not be the same as
     * another existing appointment in the HealthContact.
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    /** Returns an unmodifiable view of the filtered appointment list */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<? super Appointment> predicate);

    void deleteRelativeAppointments(Patient patient);

    /**
     * Returns true if a bill with the same identity as {@code bill} exists in the HealthContact.
     */
    boolean hasBill(Bill bill);

    /**
     * Deletes the given bill.
     * The bill must exist in the HealthContact.
     */
    void deleteBill(Bill target);

    /**
     * Adds the given bill.
     * {@code bill} must not already exist in the HealthContact.
     */
    void addBill(Bill bill);

    /**
     * Replaces the given bill {@code target} with {@code editedBill}.
     * {@code target} must exist in the HealthContact.
     * The bill identity of {@code editedBill} must not be the same as
     * another existing bill in the HealthContact.
     */
    void setBill(Bill target, Bill editedBill);

    /** Returns an unmodifiable view of the filtered bill list */
    ObservableList<Bill> getFilteredBillList();

    /**
     * Updates the filter of the filtered bill list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBillList(Predicate<? super Bill> predicate);

    void deleteRelativeBills(Appointment appointment);

    /**
     * Sorts the namelist by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortPatients(Comparator<Patient> comparator, boolean isAscending);

    /**
     * Sorts the appointments by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortAppointments(Comparator<Appointment> comparator, boolean isAscending);

    /**
     * Sets the Status of PaymentStatus of the given bill to UNPAID.
     * @param bill
     */
    void setBillAsUnpaid(Bill bill);

    /**
     * Sorts the bills by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortBills(Comparator<Bill> comparator, boolean isAscending);

    /**
     * Updates previous state of the HealthContact.
     */
    void updateHealthContactHistory();

    /**
     * Updates state of the HealthContact when undo command is executed.
     */
    void updateRedoHealthContactHistory();

    /**
     * Undo last change made to state of HealthContact.
     */
    void undo() throws CommandException;


    /**
     * Redo last change made to state of HealthContact.
     */
    void redo() throws CommandException;


    /**
     * Sets the Status of PaymentStatus of the given bill to PAID.
     * @param bill
     */
    void setBillAsPaid(Bill bill);

    /**
     * Gets History in Model.
     */
    History getHistory();
}
