package seedu.studmap.model;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.studmap.commons.core.GuiSettings;
import seedu.studmap.commons.core.LogsCenter;
import seedu.studmap.model.attribute.Attribute;
import seedu.studmap.model.order.Order;
import seedu.studmap.model.student.Student;

/**
 * Represents the in-memory model of the student map data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StudMap studMap;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given studMap and userPrefs.
     */
    public ModelManager(ReadOnlyStudMap studMap, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(studMap, userPrefs);

        logger.fine("Initializing with student map: " + studMap + " and user prefs " + userPrefs);

        this.studMap = new StudMap(studMap);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.studMap.getStudentList());
    }

    public ModelManager() {
        this(new StudMap(), new UserPrefs());
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
    public Path getStudMapFilePath() {
        return userPrefs.getStudMapFilePath();
    }

    @Override
    public void setStudMapFilePath(Path studMapFilePath) {
        requireNonNull(studMapFilePath);
        userPrefs.setStudMapFilePath(studMapFilePath);
    }

    //=========== StudMap ================================================================================

    @Override
    public void setStudMap(ReadOnlyStudMap studMap) {
        this.studMap.resetData(studMap);
    }

    @Override
    public ReadOnlyStudMap getStudMap() {
        return studMap;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return studMap.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        studMap.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        studMap.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        studMap.setStudent(target, editedStudent);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedStudMap}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void sortFilteredStudentList(Attribute attribute, Order order) {
        this.studMap.sort(attribute, order);
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public void filterStudentListWithTag(Predicate<Student> predicate) {
        requireAllNonNull(predicate);
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
        return studMap.equals(other.studMap)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}
