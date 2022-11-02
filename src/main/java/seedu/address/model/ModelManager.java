package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCodeContainsKeywordPredicate;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.ScheduleContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentContainsModulePredicate;
import seedu.address.model.person.TutorContainsModulePredicate;

/**
 * Represents the in-memory model of the profNus data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final ProfNus profNus;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Module> filteredModules;
    private final FilteredList<Student> filteredTutors;
    private FilteredList<Schedule> filteredSchedule;

    /**
     * Initializes a ModelManager with the given profNus and userPrefs.
     */
    public ModelManager(ReadOnlyProfNus profNus, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(profNus, userPrefs);

        logger.fine("Initializing with profNus: " + profNus + " and user prefs " + userPrefs);

        this.profNus = new ProfNus(profNus);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.profNus.getPersonList());
        filteredTutors = new FilteredList<>(this.profNus.getTutorList());
        filteredModules = new FilteredList<>(this.profNus.getModuleList());
        filteredSchedule = new FilteredList<>(this.profNus.getScheduleList());
    }

    public ModelManager() {
        this(new ProfNus(), new UserPrefs());
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
    public Path getProfNusFilePath() {
        return userPrefs.getProfNusFilePath();
    }

    @Override
    public void setProfNusFilePath(Path profNusFilePath) {
        requireNonNull(profNusFilePath);
        userPrefs.setProfNusFilePath(profNusFilePath);
    }

    //=========== ProfNus ================================================================================

    @Override
    public void setProfNus(ReadOnlyProfNus profNus) {
        this.profNus.resetData(profNus);
    }

    @Override
    public ReadOnlyProfNus getProfNus() {
        return profNus;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return profNus.hasPerson(person);
    }

    @Override
    public boolean hasTutor(Student tutor) {
        requireNonNull(tutor);
        return profNus.hasTutor(tutor);
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return profNus.hasModule(module);
    }

    @Override
    public boolean conflictSchedule(Schedule schedule) {
        requireNonNull(schedule);
        return profNus.conflictSchedule(schedule);
    }

    @Override
    public boolean conflictScheduleWithTarget(Schedule schedule, Schedule target) {
        requireNonNull(schedule);
        return profNus.conflictScheduleWithTarget(schedule, target);
    }

    @Override
    public void deletePerson(Person target) {
        profNus.removePerson(target);
    }

    @Override
    public void deleteTutor(Student target) {
        profNus.removeTutor(target);
    }

    @Override
    public void deleteModule(Module target) {
        profNus.removeModule(target);
    }

    @Override
    public void addModule(Module module) {
        profNus.addModule(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public Module getModuleByModuleCode(String moduleCode) {
        return profNus.getModuleByModuleCode(moduleCode);
    }

    @Override
    public void addPerson(Person person) {
        profNus.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addTutor(Student tutor) {
        profNus.addTutor(tutor);
        updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
    }

    @Override
    public void addSchedule(Schedule schedule) {
        profNus.addSchedule(schedule);
        updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
    }

    @Override
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        requireAllNonNull(target, editedSchedule);
        profNus.setSchedule(target, editedSchedule);
        updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
    }

    @Override
    public void deleteSchedule(Schedule target) {
        requireNonNull(target);
        profNus.removeSchedule(target);
        updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
    }

    @Override
    public void clearSchedules(ArrayList<ModuleCode> modulesToClear) {
        profNus.clearSchedules(modulesToClear);
        updateFilteredScheduleList((PREDICATE_SHOW_ALL_SCHEDULES));
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        profNus.setPerson(target, editedPerson);
    }

    @Override
    public void setTutor(Student target, Student editedTutor) {
        requireAllNonNull(target, editedTutor);

        profNus.setTutor(target, editedTutor);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);
        profNus.setModule(target, editedModule);
    }

    @Override
    public void viewModule(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        updateFilteredModuleList(new ModuleCodeContainsKeywordPredicate(moduleCode));
        updateFilteredScheduleList(new ScheduleContainsKeywordsPredicate(Arrays
                .asList(moduleCode.toString())));
    }

    @Override
    public void viewModuleDetails(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        updateFilteredStudentList(new StudentContainsModulePredicate(moduleCode));
        updateFilteredTutorList(new TutorContainsModulePredicate(moduleCode));
    }



    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedProfNus}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<Person> getAllPersonList() {
        return new FilteredList<>(this.profNus.getPersonList());
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Student List Accessors =============================================================

    @Override
    public void updateFilteredStudentList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Tutor List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedProfNus}
     */
    @Override
    public ObservableList<Student> getFilteredTutorList() {
        return filteredTutors;
    }

    @Override
    public ObservableList<Student> getAllTutorList() {
        return new FilteredList<>(this.profNus.getTutorList());
    }

    @Override
    public void updateFilteredTutorList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredTutors.setPredicate(predicate);
    }


    //=========== Filtered Module List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Module} backed by the internal list of
     * {@code versionedProfNus}
     */
    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public ObservableList<Module> getAllModuleList() {
        return new FilteredList<>(this.profNus.getModuleList());
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    //=========== Filtered Schedule List Accessors =============================================================

    @Override
    public ObservableList<Schedule> getFilteredScheduleList() {
        return filteredSchedule;
    }

    @Override
    public ObservableList<Schedule> getAllScheduleList() {
        return new FilteredList<>(this.profNus.getScheduleList());

    }

    @Override
    public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
        filteredSchedule = new FilteredList<>(profNus.getScheduleList());
        requireNonNull(predicate);
        filteredSchedule.setPredicate(predicate);
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
        return profNus.equals(other.profNus)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }
}
