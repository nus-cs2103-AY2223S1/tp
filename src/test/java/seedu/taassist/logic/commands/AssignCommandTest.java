package seedu.taassist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalStudents.ALICE;
import static seedu.taassist.testutil.TypicalStudents.BOB;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taassist.commons.core.Messages;
import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.ModelStub;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Student;
import seedu.taassist.testutil.StudentBuilder;

class AssignCommandTest {

    @Test
    public void execute_noModuleClass_throwsCommandException() {
        ModelStubWithNoModuleClasses modelStub = new ModelStubWithNoModuleClasses();
        ModuleClass moduleClass = new ModuleClass("GEN1000");
        List<Index> indices = new ArrayList<>();

        AssignCommand assignCommand = new AssignCommand(indices, moduleClass);
        String expectedMessage = String.format(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                modelStub.getModuleClassList());

        assertThrows(CommandException.class, expectedMessage, () -> assignCommand.execute(modelStub));
    }

    @Test
    public void execute_indexOutOfRange_throwsCommandException() {
        ModelStubWithOneStudent modelStub = new ModelStubWithOneStudent();
        ModuleClass moduleClass = new ModuleClass("GEN1000");

        List<Index> indices = new ArrayList<>();
        indices.add(Index.fromOneBased(1));
        indices.add(Index.fromOneBased(2));

        AssignCommand assignCommand = new AssignCommand(indices, moduleClass);
        String expectedMessage = Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;

        assertThrows(CommandException.class, expectedMessage, () -> assignCommand.execute(modelStub));
    }

    @Test
    public void execute_assignOneStudent_success() throws CommandException {
        ModelStubWithOneStudent modelStub = new ModelStubWithOneStudent();
        ModuleClass moduleClass = new ModuleClass("GEN1000");

        List<Index> indices = new ArrayList<>();
        indices.add(Index.fromOneBased(1));

        AssignCommand assignCommand = new AssignCommand(indices, moduleClass);
        String expectedMessage = String.format(AssignCommand.MESSAGE_SUCCESS, "index", indices, moduleClass);
        Student expectedStudent = new StudentBuilder(ALICE).withModuleClasses(moduleClass.getClassName()).build();

        assertEquals(expectedMessage, assignCommand.execute(modelStub).getFeedbackToUser());
        assertEquals(expectedStudent, modelStub.student);
    }

    @Test
    void execute_assignMultipleStudents_success() throws CommandException {
        ModelStubWithMultipleStudents modelStub = new ModelStubWithMultipleStudents();
        ModuleClass moduleClass = new ModuleClass("GEN1000");

        List<Index> indices = new ArrayList<>();
        indices.add(Index.fromOneBased(1));
        indices.add(Index.fromOneBased(2));

        AssignCommand assignCommand = new AssignCommand(indices, moduleClass);
        String expectedMessage = String.format(AssignCommand.MESSAGE_SUCCESS, "indices", indices, moduleClass);
        Student expectedStudent1 = new StudentBuilder(ALICE).withModuleClasses(moduleClass.getClassName()).build();
        Student expectedStudent2 = new StudentBuilder(BOB).withModuleClasses(moduleClass.getClassName()).build();

        assertEquals(expectedMessage, assignCommand.execute(modelStub).getFeedbackToUser());
        assertEquals(expectedStudent1, modelStub.students.get(0));
        assertEquals(expectedStudent2, modelStub.students.get(1));
    }

    @Test
    public void equals() {
        ModuleClass moduleClass = new ModuleClass("GEN1000");

        AssignCommand assignFirstCommand = new AssignCommand(List.of(Index.fromOneBased(1)), moduleClass);
        AssignCommand assignSecondCommand = new AssignCommand(List.of(Index.fromOneBased(2)), moduleClass);

        // same object -> returns true
        assertEquals(assignFirstCommand, assignFirstCommand);

        // same values -> returns true
        AssignCommand assignFirstCommandCopy = new AssignCommand(List.of(Index.fromOneBased(1)), moduleClass);
        assertEquals(assignFirstCommand, assignFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(assignFirstCommand, 1);

        // null -> returns false
        assertNotEquals(assignFirstCommand, null);

        // different student -> returns false
        assertNotEquals(assignFirstCommand, assignSecondCommand);
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
            return FXCollections.observableArrayList();
        }
    }

    /**
     * A Model stub with one filtered student. Always says it has a module.
     */
    private class ModelStubWithOneStudent extends ModelStub {

        // ALICE with no module classes
        private Student student = new StudentBuilder(ALICE).withModuleClasses().build();

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return FXCollections.observableArrayList(student);
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            student = editedStudent;
        }

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            return true;
        }
    }

    /**
     * Model stub with multiple filtered students. Always says it has a module.
     */
    private class ModelStubWithMultipleStudents extends ModelStub {

        private List<Student> students = new ArrayList<>();

        public ModelStubWithMultipleStudents() {
            // ALICE and BOB with no module classes
            students.add(new StudentBuilder(ALICE).withModuleClasses().build());
            students.add(new StudentBuilder(BOB).withModuleClasses().build());
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return FXCollections.observableArrayList(students);
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            students.set(students.indexOf(target), editedStudent);
        }

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            return true;
        }
    }
}
