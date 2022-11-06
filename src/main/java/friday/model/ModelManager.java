package friday.model;

import static friday.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import friday.commons.core.GuiSettings;
import friday.commons.core.LogsCenter;
import friday.model.alias.Alias;
import friday.model.alias.ReservedKeyword;
import friday.model.student.Student;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

/**
 * Represents the in-memory model of FRIDAY's data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Friday friday;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final SortedList<Student> sortedStudents;
    private ObservableList<Student> students;

    /**
     * Initializes a ModelManager with the given FRIDAY and userPrefs.
     */
    public ModelManager(ReadOnlyFriday friday, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(friday, userPrefs);

        logger.fine("Initializing with FRIDAY: " + friday + " and user prefs " + userPrefs);

        this.friday = new Friday(friday);
        this.userPrefs = new UserPrefs(userPrefs);
        this.students = this.friday.getStudentList();
        this.filteredStudents = new FilteredList<>(students);
        this.sortedStudents = new SortedList<>(students);
    }

    public ModelManager() {
        this(new Friday(), new UserPrefs());
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

    public Path getFridayFilePath() {
        return userPrefs.getFridayFilePath();
    }

    public void setFridayFilePath(Path fridayFilePath) {
        requireNonNull(fridayFilePath);
        userPrefs.setFridayFilePath(fridayFilePath);
    }

    //=========== FRIDAY ================================================================================

    public void setFriday(ReadOnlyFriday friday) {
        this.friday.resetData(friday);
    }

    @Override
    public ReadOnlyFriday getFriday() {
        return friday;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return friday.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        friday.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        friday.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        friday.setStudent(target, editedStudent);
    }

    @Override
    public boolean hasAlias(Alias alias) {
        requireNonNull(alias);
        return friday.hasAlias(alias);
    }

    @Override
    public void addAlias(Alias toAdd, ReservedKeyword keyword) {
        friday.addAlias(toAdd, keyword);
    }

    @Override
    public void removeAlias(Alias key) {
        friday.removeAlias(key);
    }

    @Override
    public String getKeyword(String key) {
        return friday.getKeyword(key);
    }

    @Override
    public String displayAliases() {
        return friday.displayAliases();
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedFRIDAY}
     */
    @Override
    public ObservableList<Student> getStudentList() {
        return students;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
        students = filteredStudents;
    }

    @Override
    public void updateSortedStudentList(Comparator<Student> comparator) {
        requireNonNull(comparator);
        sortedStudents.setComparator(comparator);
        students = sortedStudents;
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
        return friday.equals(other.friday)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}
