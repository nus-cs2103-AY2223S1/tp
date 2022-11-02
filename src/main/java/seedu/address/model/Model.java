package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.commons.ModuleCode;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Reminder> PREDICATE_SHOW_ALL_REMINDERS = unused -> true;
    Predicate<Tutorial> PREDICATE_SHOW_ALL_TUTORIALS = unused -> true;
    Predicate<Consultation> PREDICATE_SHOW_ALL_CONSULTATIONS = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Student student);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Student target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Student student);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Student> getFilteredPersonList();

    /** Returns an unmodifiable view of the student'grade */
    ObservableList<PieChart.Data> getStudentGradeChartData();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Student> predicate);

    /**
     * Returns true if a reminder with the same identity as {@code reminder} exists in ModQuik.
     */
    boolean hasReminder(Reminder reminder);

    /**
     * Adds the given reminder.
     * {@code reminder} must not already exist in ModQuik.
     */
    void addReminder(Reminder reminder);

    /**
     * Replaces the given reminder {@code target} with {@code editedReminder}.
     * {@code target} must exist in ModQuik.
     * The reminder identity of {@code editedReminder} must not be the same as another existing reminder in ModQuik.
     */
    void setReminder(Reminder target, Reminder editedReminder);

    /**
     * Deletes the given reminder.
     * The reminder must exist in ModQuik.
     */
    void deleteReminder(Reminder reminder);

    /**
     * Sort reminders by priority. Reminders with the same priority will be sorted lexicographically by their names.
     */
    void sortReminderByPriority();

    /**
     * Sort reminders by deadline. Reminders with the same deadline will be sorted lexicographically by their names.
     */
    void sortReminderByDeadline();

    /** Returns an unmodifiable view of the filtered reminder list */
    ObservableList<Reminder> getFilteredReminderList();

    /**
     * Updates the filter of the filtered reminder list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredReminderList(Predicate<Reminder> predicate);

    /**
     * Returns true if a tutorial with the same identity as {@code tutorial} exists in ModQuik.
     */
    boolean hasTutorial(Tutorial tutorial);

    /**
     * Returns the true if there exists tutorials in ModQuik having timeslot overlapping with an {@code tutorial}.
     */
    boolean hasClashingTutorial(Tutorial toCheck);

    /**
     * Returns true if there exists tutorials exclude {@code exception} in ModQuik having timeslot overlapping with
     * an {@code tutorial}
     */
    boolean hasClashingTutorialExcept(Tutorial toCheck, Tutorial exception);

    /**
     * Adds the given tutorial.
     * {@code tutorial} must not already exist in ModQuik.
     */
    void addTutorial(Tutorial tutorial);

    /**
     * Deletes the given tutorial.
     * The person must exist in ModQuik.
     */
    void deleteTutorial(Tutorial target);


    void setTutorial(Tutorial tutorialToEdit, Tutorial editedTutorial);

    /** Returns an unmodifiable view of the filtered tutorial list */
    ObservableList<Tutorial> getFilteredTutorialList();

    /**
     * Updates the filter of the filtered tutorial list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTutorialList(Predicate<Tutorial> predicate);

    /**
     * Returns true if a consultation with the same identity as {@code consultation} exists in ModQuik.
     */
    boolean hasConsultation(Consultation consultation);

    /**
     * Returns the true if there exists tutorials in ModQuik having timeslot overlapping with an {@code tutorial}.
     */
    boolean hasClashingConsultation(Consultation toCheck);

    /**
     * Returns true if there exists tutorials exclude {@code exception} in ModQuik having timeslot overlapping with
     * an {@code tutorial}
     */
    boolean hasClashingConsultationExcept(Consultation toCheck, Consultation exception);

    /**
     * Deletes the given consultation.
     * The person must exist in ModQuik.
     */
    void deleteConsultation(Consultation target);

    /**
     * Adds the given consultation.
     */
    void addConsultation(Consultation consultation);

    /** Returns an unmodifiable view of the filtered consultation list */
    ObservableList<Consultation> getFilteredConsultationList();

    /**
     * Updates the filter of the filtered tutorial list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredConsultationList(Predicate<Consultation> predicate);

    /**
     * Replaces the given consultation {@code target} with {@code editedConsultation}.
     * {@code target} must exist in ModQuik.
     * The consultation identity of {@code editedConsultation} must not be the same as another existing consultation
     * in Modquik.
     */
    void setConsultation(Consultation target, Consultation editedConsultation);

    /**
     * Clears all existing consultations.
     */
    void resetStudents();

    /**
     * Clears all existing consultations.
     */
    void resetConsultations();

    /**
     * Clears all existing consultations.
     */
    void resetTutorials();

    /**
     * Clears all existing consultations.
     */
    void resetReminders();

    void markReminder(Reminder reminderToMark);

    boolean reminderIsMarked(Reminder reminderToMark);

    void unmarkReminder(Reminder reminderToUnmark);

    boolean hasModuleCode(ModuleCode moduleCode);

    boolean hasTutorialName(TutorialName tutorialName);
}
