package seedu.taassist.model.stubs;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import seedu.taassist.commons.core.GuiSettings;
import seedu.taassist.model.Model;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.ReadOnlyUserPrefs;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.StudentView;

/**
 * A default model stub that has all the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getTaAssistFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTaAssistFilePath(Path taAssistFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addStudent(Student student) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTaAssist(ReadOnlyTaAssist newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyTaAssist getTaAssist() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasStudent(Student student) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeStudent(Student target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<StudentView> getStudentViewList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Session> getSessionList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Student> getStudentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setFilteredListPredicate(Predicate<Student> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void andFilteredListPredicate(Predicate<Student> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasModuleClass(ModuleClass moduleClass) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasModuleClasses(Collection<ModuleClass> moduleClasses) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ModuleClass getModuleClassWithSameName(ModuleClass moduleClass) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeModuleClass(ModuleClass moduleClass) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setModuleClass(ModuleClass target, ModuleClass editedModuleClass) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeModuleClasses(Collection<ModuleClass> moduleClass) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addSessions(ModuleClass moduleClass, Set<Session> sessions) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeSessions(ModuleClass moduleClass, Set<Session> sessions) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addModuleClass(ModuleClass moduleClass) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addModuleClasses(Set<ModuleClass> moduleClasses) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<ModuleClass> getModuleClassList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void enterFocusMode(ModuleClass classToFocus) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void exitFocusMode() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isInFocusMode() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ModuleClass getFocusedClass() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public SimpleStringProperty getFocusLabelProperty() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void querySessionData(Session targetSession) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void resetQueriedSessionData() {
        throw new AssertionError("This method should not be called.");
    }
}
