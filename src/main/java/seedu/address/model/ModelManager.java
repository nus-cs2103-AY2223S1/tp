package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.student.Class;
import seedu.address.model.student.Person;
import seedu.address.model.timeRange.TimeRange;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TeachersPet teachersPet;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Person> filteredSchedule;
    private final ArrayList<ReadOnlyTeachersPet> teachersPetHistory;

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
        this.teachersPetHistory = new ArrayList<>();
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

    @Override
    public Class findAvailableClass(TimeRange timeRange) {
        requireNonNull(timeRange);
        return teachersPet.findAvailableClass(timeRange);
    }

    @Override
    public void sortPersons(Comparator<Person> comparator) {
        requireNonNull(comparator);
        teachersPet.sortPersons(comparator);
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

    //=========== Undo Accessors =================================================================================
    @Override
    public void updateTeachersPetHistory() {
        this.teachersPetHistory.add(new TeachersPet(this.teachersPet));
    }

    @Override
    public void undo() throws CommandException {
        try {
            ReadOnlyTeachersPet targetTeachersPet = this.teachersPetHistory.get(this.teachersPetHistory.size() - 2);
            setTeachersPet(targetTeachersPet);
            // remove the current state and last state from history
            deleteTeachersPetHistory();
            deleteTeachersPetHistory();
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Undo cannot be done as there was no previous action");
        }
    }

    @Override
    public void deleteTeachersPetHistory() {
        this.teachersPetHistory.remove(this.teachersPetHistory.size() - 1);
    }
}
