package seedu.taassist.model;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
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
    private static final String DEFAULT_FOCUS_LABEL = "";
    private static final String FOCUS_LABEL_FORMAT = "Focus: %s";

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TaAssist taAssist;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final SimpleStringProperty focusLabelProperty;

    // N.B. must guarantee focusedClass is equivalent to the entry in the UniqueModuleClassList.
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
        focusLabelProperty = new SimpleStringProperty(DEFAULT_FOCUS_LABEL);
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
        requireNonNull(target);
        taAssist.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        requireNonNull(student);
        taAssist.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        taAssist.setStudent(target, editedStudent);
    }

    @Override
    public boolean hasModuleClass(ModuleClass moduleClass) {
        requireAllNonNull(moduleClass);
        return taAssist.hasModuleClass(moduleClass);
    }

    @Override
    public boolean hasModuleClasses(Collection<ModuleClass> moduleClasses) {
        requireAllNonNull(moduleClasses);
        return moduleClasses.stream().allMatch(this::hasModuleClass);
    }

    @Override
    public void deleteModuleClass(ModuleClass target) {
        requireNonNull(target);
        taAssist.removeModuleClass(target);

        // TODO: Should an Exception be thrown instead?
        if (target.isSame(focusedClass)) {
            exitFocusMode();
        }
    }

    @Override
    public void setModuleClass(ModuleClass target, ModuleClass editedModuleClass) {
        requireAllNonNull(target, editedModuleClass);
        taAssist.setModuleClass(target, editedModuleClass);

        if (target.isSame(focusedClass)) {
            enterFocusMode(editedModuleClass);
        }
    }

    @Override
    public void deleteModuleClasses(Collection<ModuleClass> moduleClasses) {
        requireAllNonNull(moduleClasses);
        for (ModuleClass moduleClass : moduleClasses) {
            deleteModuleClass(moduleClass);
        }
    }

    @Override
    public void addModuleClass(ModuleClass moduleClass) {
        requireNonNull(moduleClass);
        taAssist.addModuleClass(moduleClass);
    }

    @Override
    public ObservableList<ModuleClass> getModuleClassList() {
        return taAssist.getModuleClassList();
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

    // TODO: Should guarantee classToFocus's equivalent identity module class must exist in TaAssist.
    @Override
    public void enterFocusMode(ModuleClass classToFocus) {
        requireNonNull(classToFocus);

        // This is done as the passed in module class might not be the exact module class needed.
        // Hence, it should look for the module class with equivalent identity in taAssist.
        // As it's taAssist's module class that contains the actual Session content.
        this.focusedClass = taAssist.findModuleClass(classToFocus);

        focusLabelProperty.set(String.format(FOCUS_LABEL_FORMAT, focusedClass));
        IsPartOfClassPredicate predicate = new IsPartOfClassPredicate(focusedClass);
        updateFilteredStudentList(predicate);
    }

    @Override
    public void exitFocusMode() {
        focusedClass = null;
        focusLabelProperty.set(DEFAULT_FOCUS_LABEL);
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

    @Override
    public SimpleStringProperty getFocusLabelProperty() {
        return focusLabelProperty;
    }
}
