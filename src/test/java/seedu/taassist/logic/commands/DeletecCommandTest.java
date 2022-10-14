package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.commons.core.Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
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
import seedu.taassist.model.Model;
import seedu.taassist.model.ModelManager;
import seedu.taassist.model.ModelStub;
import seedu.taassist.model.UserPrefs;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.uniquelist.UniqueList;
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

        assertEquals(String.format(DeletecCommand.MESSAGE_DELETE_MODULE_CLASS_SUCCESS, validModuleClasses),
                commandResult.getFeedbackToUser());

        assertTrue(modelStub.getModuleClasses().isEmpty());
    }

    @Test
    public void execute_deleteNonExistentClass_throwsCommandException() throws Exception {
        ModuleClass moduleClass = new ModuleClassBuilder().build();
        ModelStubWithNoModuleClass modelStub = new ModelStubWithNoModuleClass();

        assertThrows(CommandException.class, () ->
                new DeletecCommand(new HashSet<>(Arrays.asList(moduleClass))).execute(modelStub));
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
        Student oldStudent = new StudentBuilder().withModuleClasses("CS1101S").build();
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

        assertCommandSuccess(new DeletecCommand(moduleClasses), model,
                String.format(DeletecCommand.MESSAGE_DELETE_MODULE_CLASS_SUCCESS, moduleClasses), expectedModel);
    }

    @Test
    public void execute_deleteNonExistentModuleClass_failure() {
        ModuleClass moduleClass = new ModuleClassBuilder().build();

        // Ensure that moduleClass does not exist
        assertFalse(model.hasModuleClass(moduleClass));

        assertCommandFailure(new DeletecCommand(new HashSet<>(Arrays.asList(moduleClass))), model,
                String.format(MESSAGE_MODULE_CLASS_DOES_NOT_EXIST, model.getModuleClassList()));
    }

    //==================================== Model Stubs ===============================================================

    private static class ModelStubWithNoModuleClass extends ModelStub {

        @Override
        public void deleteModuleClasses(Collection<ModuleClass> moduleClasses) {
            requireAllNonNull(moduleClasses);
        }

        @Override
        public void deleteModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
        }

        @Override
        public boolean hasModuleClasses(Collection<ModuleClass> moduleClasses) {
            requireAllNonNull(moduleClasses);
            return false;
        }

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return false;
        }

        @Override
        public ObservableList<ModuleClass> getModuleClassList() {
            return new UniqueList<ModuleClass>().asUnmodifiableObservableList();
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return new UniqueList<Student>().asUnmodifiableObservableList();
        }
    }

    private static class ModelStubWithFixedModuleClasses extends ModelStub {
        private Set<ModuleClass> moduleClasses = new HashSet<>(Arrays.asList(CS1101S, CS1231S));

        @Override
        public void deleteModuleClasses(Collection<ModuleClass> moduleClasses) {
            requireAllNonNull(moduleClasses);
            this.moduleClasses = new HashSet<>();
        }

        @Override
        public boolean hasModuleClasses(Collection<ModuleClass> moduleClasses) {
            requireAllNonNull(moduleClasses);
            return true;
        }

        public Set<ModuleClass> getModuleClasses() {
            return moduleClasses;
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return new UniqueList<Student>().asUnmodifiableObservableList();
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
        public void deleteModuleClasses(Collection<ModuleClass> moduleClasses) {
            requireAllNonNull(moduleClasses);
            return;
        }

        @Override
        public ObservableList<Student> getStudentList() {
            UniqueList<Student> studentList = new UniqueList<>();
            studentList.add((student));
            return studentList.asUnmodifiableObservableList();
        }

        @Override
        public void setStudent(Student oldStudent, Student newStudent) {
            this.student = newStudent;
        }
    }
}
