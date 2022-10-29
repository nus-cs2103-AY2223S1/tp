package seedu.taassist.model;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import seedu.taassist.commons.core.GuiSettings;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.StudentView;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

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
     * Returns the user prefs' TaAssist file path.
     */
    Path getTaAssistFilePath();

    /**
     * Sets the user prefs' TaAssist file path.
     */
    void setTaAssistFilePath(Path taAssistFilePath);

    /**
     * Replaces TaAssist data with the data in {@code taAssist}.
     */
    void setTaAssist(ReadOnlyTaAssist taAssist);

    /** Returns the TaAssist */
    ReadOnlyTaAssist getTaAssist();

    /**
     * Returns true if a student with the same identity as {@code student} exists in TA-Assist.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in TA-Assist.
     */
    void removeStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in TA-Assist.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in TA-Assist.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the
     * TaAssist.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list. */
    ObservableList<Student> getFilteredStudentList();

    /** Returns a view of students along with its associated session data */
    ObservableList<StudentView> getStudentViewList();

    /** Returns an unmodifiable view of the student list. */
    ObservableList<Student> getStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void setFilteredListPredicate(Predicate<Student> predicate);

    /**
     * Updates the filter of the filtered student list to filter by the given
     * {@code predicate} AND the current predicate.
     */
    void andFilteredListPredicate(Predicate<Student> predicate);

    /**
     * Returns true if a class with the same identity as {@code moduleClass} exists in TA-Assist.
     */
    boolean hasModuleClass(ModuleClass moduleClass);

    /** Checks if all {@code ModuleClass} in {@code moduleClasses} exists in the model. */
    boolean hasModuleClasses(Collection<ModuleClass> moduleClasses);

    /** Returns an existing {@code ModuleClass} with the same name as {@code moduleClass}. */
    ModuleClass getModuleClassWithSameName(ModuleClass moduleClass);

    /**
     * Replaces the module class {@code target} in the list with {@code editedModuleClass}.
     * {@code target} must exist in the list.
     * The identity of {@code editedModuleClass} must not be the same as another existing module class in the TaAssist.
     */
    void setModuleClass(ModuleClass target, ModuleClass editedModuleClass);

    /**
     * Deletes the given {@code moduleClass}.
     * The class must exist in TA-Assist.
     */
    void removeModuleClass(ModuleClass moduleClass);

    /**
     * Deletes multiple classes.
     * The classes must exist in TA-Assist.
     */
    void removeModuleClasses(Collection<ModuleClass> moduleClasses);

    /**
     * Adds the specified {@code sessions} to the specified {@code moduleClass}.
     */
    void addSessions(ModuleClass moduleClass, Set<Session> sessions);

    /**
     * Removes the specified {@code sessions} from the specified {@code moduleClass}.
     */
    void removeSessions(ModuleClass moduleClass, Set<Session> sessions);

    /**
     * Adds the given class.
     * {@code moduleClass} must not already exist in TA-Assist.
     */
    void addModuleClass(ModuleClass moduleClass);

    /**
     * Adds the given classes.
     * {@code moduleClass} must not already exist in TA-Assist.
     */
    void addModuleClasses(Set<ModuleClass> moduleClasses);

    /** Returns an unmodifiable view of the module class list */
    ObservableList<ModuleClass> getModuleClassList();

    /** Returns an unmodifiable view of the session list */
    ObservableList<Session> getSessionList();

    /** Enters focus mode. */
    void enterFocusMode(ModuleClass classToFocus);

    /** Exits focus mode. */
    void exitFocusMode();

    /** Checks if the focus mode is active. */
    boolean isInFocusMode();

    /** Returns module class currently in focus. */
    ModuleClass getFocusedClass();

    SimpleStringProperty getFocusLabelProperty();

    /** Queries {@code SessionData} within studentViewList with the provided {@code Session} */
    void querySessionData(Session targetSession);

    /** Removes queried {@code SessionData} within the studentViewList */
    void resetQueriedSessionData();
}
