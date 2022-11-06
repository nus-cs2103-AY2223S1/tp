package seedu.modquik.model;

import static java.util.Objects.requireNonNull;
import static seedu.modquik.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.chart.PieChart;
import seedu.modquik.commons.core.GuiSettings;
import seedu.modquik.commons.core.LogsCenter;
import seedu.modquik.model.commons.ModuleCode;
import seedu.modquik.model.consultation.Consultation;
import seedu.modquik.model.reminder.Reminder;
import seedu.modquik.model.student.Student;
import seedu.modquik.model.tutorial.Tutorial;
import seedu.modquik.model.tutorial.TutorialName;

/**
 * Represents the in-memory model of the modquik book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ModQuik modQuik;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Reminder> filteredReminders;
    private final FilteredList<Tutorial> filteredTutorials;
    private final FilteredList<Consultation> filteredConsultations;

    /**
     * Initializes a ModelManager with the given modQuik and userPrefs.
     */
    public ModelManager(ReadOnlyModQuik modQuik, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(modQuik, userPrefs);

        logger.fine("Initializing with modquik: " + modQuik + " and user prefs " + userPrefs);

        this.modQuik = new ModQuik(modQuik);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.modQuik.getPersonList());
        filteredReminders = new FilteredList<>(this.modQuik.getReminderList());
        filteredTutorials = new FilteredList<>(this.modQuik.getTutorialList());
        filteredConsultations = new FilteredList<>(this.modQuik.getConsultationList());
    }

    public ModelManager() {
        this(new ModQuik(), new UserPrefs());
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
    public Path getModQuikFilePath() {
        return userPrefs.getModQuikFilePath();
    }

    @Override
    public void setModQuikFilePath(Path modQuikFilePath) {
        requireNonNull(modQuikFilePath);
        userPrefs.setModQuikFilePath(modQuikFilePath);
    }

    //=========== ModQuik ================================================================================

    @Override
    public void setModQuik(ReadOnlyModQuik modQuik) {
        this.modQuik.resetData(modQuik);
    }

    @Override
    public ReadOnlyModQuik getModQuik() {
        return modQuik;
    }

    @Override
    public boolean hasPerson(Student student) {
        requireNonNull(student);
        return modQuik.hasPerson(student);
    }

    @Override
    public void deletePerson(Student target) {
        modQuik.removePerson(target);
    }

    @Override
    public void addPerson(Student student) {
        modQuik.addPerson(student);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        modQuik.setPerson(target, editedStudent);
    }

    @Override
    public void resetStudents() {
        modQuik.setPersons(new ArrayList<>());
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedModQuik}
     */
    @Override
    public ObservableList<Student> getFilteredPersonList() {
        return filteredStudents;
    }

    @Override
    public ObservableList<PieChart.Data> getStudentGradeChartData() {
        int[] gradeArr = modQuik.getGradeData();
        ObservableList<PieChart.Data> gradeChartData = FXCollections.observableArrayList(
                new PieChart.Data("Grade A: " + gradeArr[0], gradeArr[0]),
                new PieChart.Data("Grade B: " + gradeArr[1], gradeArr[1]),
                new PieChart.Data("Grade C: " + gradeArr[2], gradeArr[2]),
                new PieChart.Data("Grade D: " + gradeArr[3], gradeArr[3]),
                new PieChart.Data("Grade F: " + gradeArr[4], gradeArr[4]));
        return gradeChartData;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
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
        return modQuik.equals(other.modQuik)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

    //=========== Reminders =============================================================


    @Override
    public boolean hasReminder(Reminder reminder) {
        requireNonNull(reminder);
        return modQuik.hasReminder(reminder);
    }

    @Override
    public void addReminder(Reminder reminder) {
        modQuik.addReminder(reminder);
        updateFilteredReminderList(PREDICATE_SHOW_ALL_REMINDERS);
    }

    @Override
    public void deleteReminder(Reminder target) {
        modQuik.removeReminder(target);
    }

    @Override
    public void sortReminderByPriority() {
        modQuik.sortRemindersByPriority();
    }

    @Override
    public void sortReminderByDeadline() {
        modQuik.sortRemindersByDeadline();
    }

    @Override
    public void setReminder(Reminder target, Reminder editedReminder) {
        requireAllNonNull(target, editedReminder);

        modQuik.setReminder(target, editedReminder);
    }

    @Override
    public void resetReminders() {
        modQuik.setReminders(new ArrayList<>());
    }

    public void markReminder(Reminder target) {
        modQuik.markReminder(target);
    }

    @Override
    public boolean reminderIsMarked(Reminder reminderToMark) {
        return reminderToMark.getCompletionStatus();
    }

    @Override
    public void unmarkReminder(Reminder target) {
        modQuik.unmarkReminder(target);
    }

    //=========== Filtered Reminder List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Reminder} backed by the internal list of
     * {@code versionedModQuik}
     */
    @Override
    public ObservableList<Reminder> getFilteredReminderList() {
        return filteredReminders;
    }

    @Override
    public void updateFilteredReminderList(Predicate<Reminder> predicate) {
        requireNonNull(predicate);
        filteredReminders.setPredicate(predicate);
    }

    //=========== Tutorials ==================================================================================

    @Override
    public boolean hasTutorial(Tutorial tutorial) {
        requireNonNull(tutorial);
        return modQuik.hasTutorial(tutorial);
    }

    @Override
    public boolean hasClashingTutorial(Tutorial toCheck) {
        requireNonNull(toCheck);
        return modQuik.hasClashingTutorial(toCheck);
    }

    @Override
    public boolean hasClashingTutorialExcept(Tutorial toCheck, Tutorial exception) {
        requireNonNull(toCheck);
        requireNonNull(exception);
        return modQuik.hasClashingTutorialExcept(toCheck, exception);
    }

    @Override
    public void addTutorial(Tutorial tutorial) {
        modQuik.addTutorial(tutorial);
        updateFilteredTutorialList(PREDICATE_SHOW_ALL_TUTORIALS);
    }

    @Override
    public void deleteTutorial(Tutorial target) {
        modQuik.removeTutorial(target);
    }

    @Override
    public void setTutorial(Tutorial target, Tutorial editedTutorial) {
        requireAllNonNull(target, editedTutorial);
        modQuik.setTutorial(target, editedTutorial);
    }


    /**
     * Returns if there exists a moduleCode.
     */
    public boolean hasModuleCode(ModuleCode moduleCode) {
        for (Tutorial tutorial : modQuik.getTutorialList()) {
            if (tutorial.getModule().equals(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns if there exists a tutorial name.
     */
    public boolean hasTutorialName(TutorialName tutorialName) {
        for (Tutorial tutorial : modQuik.getTutorialList()) {
            if (tutorial.getName().equals(tutorialName)) {
                return true;
            }
        }
        return false;
    }
    //=========== Filtered Tutorial List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Tutorial} backed by the internal list of
     * {@code versionedModQuik}
     */
    @Override
    public ObservableList<Tutorial> getFilteredTutorialList() {
        return filteredTutorials;
    }

    @Override
    public void updateFilteredTutorialList(Predicate<Tutorial> predicate) {
        requireNonNull(predicate);
        filteredTutorials.setPredicate(predicate);
    }

    @Override
    public void resetTutorials() {
        modQuik.setTutorials(new ArrayList<>());
    }

    //=========== Consultation ==================================================================================

    @Override
    public boolean hasConsultation(Consultation consultation) {
        requireNonNull(consultation);
        return modQuik.hasConsultation(consultation);
    }

    @Override
    public boolean hasClashingConsultation(Consultation toCheck) {
        requireNonNull(toCheck);
        return modQuik.hasClashingConsultation(toCheck);
    }

    @Override
    public boolean hasClashingConsultationExcept(Consultation toCheck, Consultation exception) {
        requireNonNull(toCheck);
        requireNonNull(exception);
        return modQuik.hasClashingConsultationExcept(toCheck, exception);
    }

    @Override
    public void addConsultation(Consultation consultation) {
        modQuik.addConsultation(consultation);
        updateFilteredConsultationList(PREDICATE_SHOW_ALL_CONSULTATIONS);
    }

    @Override
    public void resetConsultations() {
        modQuik.setConsultations(new ArrayList<>());
    }

    @Override
    public void setConsultation(Consultation target, Consultation editedConsultation) {
        requireAllNonNull(target, editedConsultation);

        modQuik.setConsultation(target, editedConsultation);
    }

    @Override
    public void deleteConsultation(Consultation target) {
        modQuik.removeConsultation(target);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Consultation} backed by the internal list of
     * {@code versionedModQuik}
     */
    @Override
    public ObservableList<Consultation> getFilteredConsultationList() {
        return filteredConsultations;
    }

    @Override
    public void updateFilteredConsultationList(Predicate<Consultation> predicate) {
        requireNonNull(predicate);
        filteredConsultations.setPredicate(predicate);
    }
}
