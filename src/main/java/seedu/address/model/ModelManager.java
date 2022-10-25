package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TeachersPet teachersPet;
    private final UserPrefs userPrefs;
    private FilteredList<Person> filteredPersons;
    private final FilteredList<Person> filteredSchedule;

    /**
     * Initializes a ModelManager with the given teachersPet and userPrefs.
     */
    public ModelManager(ReadOnlyTeachersPet teachersPet, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(teachersPet, userPrefs);

        logger.fine("Initializing with address book: " + teachersPet + " and user prefs " + userPrefs);

        this.teachersPet = new TeachersPet(teachersPet);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.teachersPet.getPersonList());
        filteredSchedule = new FilteredList<>(this.teachersPet.getScheduleList());
    }

    public ModelManager() {
        this(new TeachersPet(), new UserPrefs());
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
    public Path getTeachersPetFilePath() {
        return userPrefs.getTeachersPetFilePath();
    }

    @Override
    public void setTeachersPetFilePath(Path teachersPetFilePath) {
        requireNonNull(teachersPetFilePath);
        userPrefs.setTeachersPetFilePath(teachersPetFilePath);
    }

    //=========== TeachersPet ================================================================================

    @Override
    public void setTeachersPet(ReadOnlyTeachersPet teachersPet) {
        this.teachersPet.resetData(teachersPet);
    }

    @Override
    public ReadOnlyTeachersPet getTeachersPet() {
        return teachersPet;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return teachersPet.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        teachersPet.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        teachersPet.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        updateFilteredScheduleList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        teachersPet.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedTeachersPet}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void sortFilteredPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        ArrayList<Person> sortedList = replaceSelectionSort(filteredPersons, comparator);
        ObservableList<Person> observableList = FXCollections.observableList(sortedList);
        this.filteredPersons = new FilteredList<>(observableList);
        filteredPersons.setPredicate(PREDICATE_SHOW_ALL_PERSONS);
    }

    private static ArrayList<Person> replaceSelectionSort(FilteredList<Person> filteredList, Comparator<Person> comparator) {
        ArrayList<Person> duplicatedList = new ArrayList<>();
        for (int i = 0; i < filteredList.size(); i++) {
            duplicatedList.add(filteredList.get(i));
        }
        int n = duplicatedList.size();
        for (int i = 1; i < n; ++i) {
            Person curr = duplicatedList.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(duplicatedList.get(j), curr) == 1) {
                duplicatedList.set(j + 1, duplicatedList.get(j));
                j = j - 1;
            }
            duplicatedList.set(j + 1, curr);
        }
        return duplicatedList;
    }

    //=========== Filtered Schedule List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the schedule of {@code Person} backed by the internal list of
     * {@code versionedTeachersPet}
     */
    @Override
    public ObservableList<Person> getFilteredScheduleList() {
        return filteredSchedule;
    }

    @Override
    public void updateFilteredScheduleList(Predicate<Person> predicate) {
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
        return teachersPet.equals(other.teachersPet)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
