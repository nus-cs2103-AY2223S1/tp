package seedu.taassist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taassist.commons.core.GuiSettings;
import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.ReadOnlyUserPrefs;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Student;

class FocusCommandTest {

    @Test
    void focus_classDoesNotExist_throwsCommandException() {
        FocusCommand focusCommand = new FocusCommand(new ModuleClass("Unknown"));
        ModelStubWithNoModuleClasses modelStub = new ModelStubWithNoModuleClasses();
        String expectedMessage =
                String.format(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST, modelStub.getModuleClassList());
        assertThrows(CommandException.class, expectedMessage, () -> focusCommand.execute(modelStub));
    }

    @Test
    void focus_classExists_focusSuccessful() throws CommandException {
        ModuleClass moduleClass = new ModuleClass("CS2103T");
        FocusCommand focusCommand = new FocusCommand(moduleClass);
        ModelStubWithAnyModuleClass modelStub = new ModelStubWithAnyModuleClass();

        CommandResult commandResult = focusCommand.execute(modelStub);

        assertEquals(String.format(FocusCommand.MESSAGE_ENTERED_FOCUS_MODE, moduleClass),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.isFocusedModuleClass(moduleClass));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
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
        public void deleteModuleClasses(Collection<ModuleClass> moduleClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModuleClass(ModuleClass moduleClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleClass(ModuleClass target, ModuleClass editedModuleClass) {
            throw new AssertionError("This method should not be called.");
        }

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

    /**
     * A Model stub that pretends to have no module classes.
     */
    private class ModelStubWithNoModuleClasses extends ModelStub {
        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            return false;
        }

        @Override
        public ObservableList<ModuleClass> getModuleClassList() {
            return FXCollections.observableArrayList(new ModuleClass("CS1101S"), new ModuleClass("CS2103T"));
        }
    }

    /**
     * A Model stub that pretends to have any module class.
     */
    private class ModelStubWithAnyModuleClass extends ModelStub {

        private ModuleClass focusedClass;

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            return true;
        }

        @Override
        public void enterFocusMode(ModuleClass classToFocus) {
            focusedClass = classToFocus;
        }

        public boolean isFocusedModuleClass(ModuleClass moduleClass) {
            return this.focusedClass.isSameModuleClass(moduleClass);
        }
    }
}
