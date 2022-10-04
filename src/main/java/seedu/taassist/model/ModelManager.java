package seedu.taassist.model;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.taassist.commons.core.GuiSettings;
import seedu.taassist.commons.core.LogsCenter;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.IsPartOfClassPredicate;
import seedu.taassist.model.student.Student;

/**
 * Represents the in-memory model of TA-Assist data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TaAssist taAssist;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private ModuleClass focusedClass;

    /**
     * Initializes a ModelManager with the given taAssist and userPrefs.
     */
    public ModelManager(ReadOnlyTaAssist taAssist, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(taAssist, userPrefs);

        logger.fine("Initializing with TaAssist: " + taAssist + " and user prefs " + userPrefs);

        this.taAssist = new TaAssist(taAssist);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.taAssist.getStudentList());
    }

    public ModelManager() {
        this(new TaAssist(), new UserPrefs());
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
    public Path getTaAssistFilePath() {
        return userPrefs.getTaAssistFilePath();
    }

    @Override
    public void setTaAssistFilePath(Path taAssistFilePath) {
        requireNonNull(taAssistFilePath);
        userPrefs.setTaAssistFilePath(taAssistFilePath);
    }

    //=========== TaAssist ================================================================================

    @Override
    public void setTaAssist(ReadOnlyTaAssist taAssist) {
        this.taAssist.resetData(taAssist);
    }

    @Override
    public ReadOnlyTaAssist getTaAssist() {
        return taAssist;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return taAssist.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        taAssist.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        taAssist.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        taAssist.setStudent(target, editedStudent);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedTaAssist}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
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
        return taAssist.equals(other.taAssist)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

    //=========== Handles focus mode state ==================================================================
    @Override
    public void enterFocusMode(ModuleClass classToFocus) {
        requireNonNull(classToFocus);
        this.focusedClass = classToFocus;
        IsPartOfClassPredicate predicate = new IsPartOfClassPredicate(classToFocus);
        updateFilteredStudentList(predicate);
    }

    @Override
    public void exitFocusMode() {
        focusedClass = null;
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public boolean isInFocusMode() {
        return focusedClass != null;
    }

    @Override
    public ModuleClass getFocusedClass() {
        return focusedClass;
    }
}
