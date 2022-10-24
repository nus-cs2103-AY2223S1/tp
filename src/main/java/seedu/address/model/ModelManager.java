package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Module> filteredModules;
    private final FilteredList<Student> filteredTutors;
    private FilteredList<Schedule> filteredSchedule;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTutors = new FilteredList<>(this.addressBook.getTutorList());
        filteredModules = new FilteredList<>(this.addressBook.getModuleList());
        filteredSchedule = new FilteredList<>(this.addressBook.getScheduleList());
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
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasTutor(Student tutor) {
        requireNonNull(tutor);
        return addressBook.hasTutor(tutor);
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return addressBook.hasModule(module);
    }

    @Override
    public boolean conflictSchedule(Schedule schedule) {
        requireNonNull(schedule);
        return addressBook.conflictSchedule(schedule);
    }

    @Override
    public boolean conflictScheduleWithTarget(Schedule schedule, Schedule target) {
        requireNonNull(schedule);
        return addressBook.conflictScheduleWithTarget(schedule, target);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void deleteTutor(Student target) {
        addressBook.removeTutor(target);
    }

    @Override
    public void deleteModule(Module target) {
        addressBook.removeModule(target);
    }

    @Override
    public void addModule(Module module) {
        addressBook.addModule(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public Module getModuleByModuleCode(String moduleCode) {
        return addressBook.getModuleByModuleCode(moduleCode);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addTutor(Student tutor) {
        addressBook.addTutor(tutor);
        updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
    }

    @Override
    public void addSchedule(Schedule schedule) {
        addressBook.addSchedule(schedule);
        updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
    }

    @Override
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        requireAllNonNull(target, editedSchedule);
        addressBook.setSchedule(target, editedSchedule);
        updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
    }

    @Override
    public void deleteSchedule(Schedule target) {
        requireNonNull(target);
        addressBook.removeSchedule(target);
        updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
    }

    @Override
    public void clearSchedules(ArrayList<ModuleCode> modulesToClear) {
        addressBook.clearSchedules(modulesToClear);
        updateFilteredScheduleList((PREDICATE_SHOW_ALL_SCHEDULES));
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void setTutor(Student target, Student editedTutor) {
        requireAllNonNull(target, editedTutor);

        addressBook.setTutor(target, editedTutor);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);
        addressBook.setModule(target, editedModule);
    }



    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<Person> getAllPersonList() {
        return new FilteredList<>(this.addressBook.getPersonList());
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
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredTutorList() {
        return filteredTutors;
    }

    @Override
    public ObservableList<Student> getAllTutorList() {
        return new FilteredList<>(this.addressBook.getTutorList());
    }

    @Override
    public void updateFilteredTutorList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredTutors.setPredicate(predicate);
    }


    //=========== Filtered Module List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Module} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public ObservableList<Module> getAllModuleList() {
        return new FilteredList<>(this.addressBook.getModuleList());
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
        return new FilteredList<>(this.addressBook.getScheduleList());

    }

    @Override
    public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
        filteredSchedule = new FilteredList<>(addressBook.getScheduleList());
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }
}
