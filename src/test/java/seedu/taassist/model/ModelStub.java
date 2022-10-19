package seedu.taassist.model;

import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Predicate;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import seedu.taassist.commons.core.GuiSettings;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Student;

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
    public void deleteStudent(Student target) {
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
    public ObservableList<Student> getStudentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
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
    public void deleteModuleClass(ModuleClass moduleClass) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setModuleClass(ModuleClass target, ModuleClass editedModuleClass) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteModuleClasses(Collection<ModuleClass> moduleClass) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addModuleClass(ModuleClass moduleClass) {
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
}
