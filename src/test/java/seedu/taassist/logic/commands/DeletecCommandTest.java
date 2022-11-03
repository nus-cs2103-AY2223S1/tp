package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.commons.util.StringUtil.commaSeparate;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.logic.commands.DeletecCommand.MESSAGE_MODULE_CLASSES_DOES_NOT_EXIST;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1101S;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalStudents.getTypicalTaAssist;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.model.Model;
import seedu.taassist.model.ModelManager;
import seedu.taassist.model.UserPrefs;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.stubs.ModelStub;
import seedu.taassist.model.stubs.ModelStubWithNoModuleClass;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.uniquelist.UniqueList;
import seedu.taassist.model.uniquelist.exceptions.ElementNotFoundException;
import seedu.taassist.testutil.ModuleClassBuilder;
import seedu.taassist.testutil.StudentBuilder;

public class DeletecCommandTest {

    private Model model = new ModelManager(getTypicalTaAssist(), new UserPrefs());

    //==================================== Unit Tests ================================================================

    @Test
    public void constructor_nullModuleClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeletecCommand(null));
    }

    @Test
    public void execute_deleteExistingClasses_success() throws Exception {
        ModelStubWithFixedModuleClasses modelStub = new ModelStubWithFixedModuleClasses();

        // guaranteed to be existing module classes
        Set<ModuleClass> validModuleClasses = modelStub.getModuleClasses();

        CommandResult commandResult = new DeletecCommand(validModuleClasses).execute(modelStub);

        String expectedMessage = DeletecCommand.getCommandMessage(validModuleClasses, new HashSet<>());

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());

        assertTrue(modelStub.getModuleClasses().isEmpty());
    }

    @Test
    public void execute_deleteNonExistentClass_showNonExistentClassMessage() throws Exception {
        ModuleClass moduleClass = new ModuleClassBuilder().build();
        ModelStubWithNoModuleClass modelStub = new ModelStubWithNoModuleClass();

        assertEquals(String.format(DeletecCommand.MESSAGE_MODULE_CLASSES_DOES_NOT_EXIST, moduleClass),
                new DeletecCommand(new HashSet<>(Arrays.asList(moduleClass))).execute(modelStub).getFeedbackToUser());
    }

    @Test
    public void equals() {
        DeletecCommand deleteCs1101sCommand = new DeletecCommand(new HashSet<>(Arrays.asList(CS1101S)));
        DeletecCommand deleteCs1101sCommandCopy = new DeletecCommand(new HashSet<>(Arrays.asList(CS1101S)));

        DeletecCommand deleteCs1231sCommand = new DeletecCommand(new HashSet<>(Arrays.asList(CS1231S)));

        assertTrue(deleteCs1101sCommand.equals(deleteCs1101sCommand));

        assertTrue(deleteCs1101sCommand.equals(deleteCs1101sCommandCopy));

        assertFalse(deleteCs1101sCommand.equals(1));

        assertFalse(deleteCs1101sCommand.equals(null));

        assertFalse(deleteCs1101sCommand.equals(deleteCs1231sCommand));
    }

    @Test
    public void execute_deleteExistingModuleClass_unassignsModuleClassFromStudent() throws Exception {
        // Student with one module class
        Student oldStudent = new StudentBuilder().withModuleClasses(CS1101S).build();
        ModuleClass moduleClass = oldStudent.getModuleClasses().get(0);

        ModelStubAcceptingStudentAndModuleClass modelStub = new ModelStubAcceptingStudentAndModuleClass();

        // Model with one module class and one student
        modelStub.addModuleClass(moduleClass);
        modelStub.addStudent(oldStudent);

        DeletecCommand deletecCommand = new DeletecCommand(new HashSet<>(Arrays.asList(moduleClass)));
        deletecCommand.execute(modelStub);

        Student newStudent = modelStub.getStudentList().get(0);
        assertTrue(newStudent.getModuleClasses().isEmpty());
    }

    //==================================== Integration Tests =========================================================

    @Test
    public void execute_deleteModuleClass_success() throws Exception {
        Model expectedModel = new ModelManager(model.getTaAssist(), new UserPrefs());

        // Must exist
        Set<ModuleClass> moduleClasses = new HashSet<>(Arrays.asList(expectedModel.getModuleClassList().get(0)));
        new DeletecCommand(moduleClasses).execute(expectedModel);

        String moduleClassesStr = commaSeparate(moduleClasses, ModuleClass::toString);

        assertCommandSuccess(new DeletecCommand(moduleClasses), model,
                String.format(DeletecCommand.MESSAGE_SUCCESS, moduleClassesStr), expectedModel);
    }

    @Test
    public void execute_deleteNonExistentModuleClass_success() {
        ModuleClass moduleClass = new ModuleClassBuilder().build();

        // Ensure that moduleClass does not exist
        assertFalse(model.hasModuleClass(moduleClass));

        assertCommandSuccess(new DeletecCommand(new HashSet<>(Arrays.asList(moduleClass))), model,
                String.format(MESSAGE_MODULE_CLASSES_DOES_NOT_EXIST, moduleClass), model);
    }

    @Test
    public void execute_deleteFocusedClass_success() throws CommandException {
        ModelStubWithFocusedClass modelStub = new ModelStubWithFocusedClass();
        new DeletecCommand(Set.of(modelStub.getFocusedClass())).execute(modelStub);

        assertFalse(modelStub.isInFocusMode()); // exited focus
        assertNull(modelStub.moduleClass); // deleted class
    }

    //==================================== Model Stubs ===============================================================

    private static class ModelStubWithFixedModuleClasses extends ModelStub {
        private Set<ModuleClass> moduleClasses = new HashSet<>(Arrays.asList(CS1101S, CS1231S));

        @Override
        public void removeModuleClasses(Collection<ModuleClass> moduleClasses) {
            requireAllNonNull(moduleClasses);
            this.moduleClasses = new HashSet<>();
        }

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return true;
        }

        public Set<ModuleClass> getModuleClasses() {
            return moduleClasses;
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return new UniqueList<Student>().asUnmodifiableObservableList();
        }

        @Override
        public ModuleClass getModuleClassWithSameName(ModuleClass moduleClass) {
            return moduleClass;
        }

        @Override
        public boolean isInFocusMode() {
            return false;
        }
    }

    private static class ModelStubAcceptingStudentAndModuleClass extends ModelStub {
        private ModuleClass moduleClass;
        private Student student;

        @Override
        public void addStudent(Student student) {
            requireNonNull(student);
            this.student = student;
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return false;
        }

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return true;
        }

        @Override
        public void addModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            this.moduleClass = moduleClass;
        }

        @Override
        public boolean hasModuleClasses(Collection<ModuleClass> moduleClasses) {
            requireAllNonNull(moduleClasses);
            return true;
        }

        @Override
        public void removeModuleClasses(Collection<ModuleClass> moduleClasses) {
            requireAllNonNull(moduleClasses);
            moduleClasses.forEach(moduleClass -> {
                student = student.removeModuleClass(moduleClass);
            });
        }

        @Override
        public ObservableList<Student> getStudentList() {
            UniqueList<Student> studentList = new UniqueList<>();
            studentList.add((student));
            return studentList.asUnmodifiableObservableList();
        }

        @Override
        public void setStudent(Student oldStudent, Student newStudent) {
            requireAllNonNull(oldStudent, newStudent);
            this.student = newStudent;
        }

        @Override
        public ModuleClass getModuleClassWithSameName(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return moduleClass;
        }

        @Override
        public boolean isInFocusMode() {
            return false;
        }
    }

    private static class ModelStubWithFocusedClass extends ModelStub {
        private ModuleClass moduleClass = new ModuleClassBuilder().build();
        private ModuleClass focusedClass = moduleClass;

        @Override
        public boolean isInFocusMode() {
            return focusedClass != null;
        }

        @Override
        public ModuleClass getFocusedClass() {
            return focusedClass;
        }

        @Override
        public void exitFocusMode() {
            focusedClass = null;
        }

        @Override
        public boolean hasModuleClass(ModuleClass toCheck) {
            requireNonNull(toCheck);
            return moduleClass.isSame(toCheck);
        }

        @Override
        public void removeModuleClass(ModuleClass toRemove) {
            requireNonNull(toRemove);
            if (moduleClass == null || !moduleClass.isSame(toRemove)) {
                throw new ElementNotFoundException();
            }
            moduleClass = null;
        }

        @Override
        public void removeModuleClasses(Collection<ModuleClass> moduleClasses) {
            requireAllNonNull(moduleClasses);
            moduleClasses.forEach(this::removeModuleClass);
        }

        @Override
        public ModuleClass getModuleClassWithSameName(ModuleClass moduleClass) {
            return moduleClass;
        }
    }
}
