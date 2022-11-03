package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1101S;
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
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.stubs.ModelStub;
import seedu.taassist.model.stubs.ModelStubWithNoModuleClass;
import seedu.taassist.model.student.Student;
import seedu.taassist.testutil.StudentBuilder;

class AssignCommandTest {

    @Test
    public void execute_noModuleClass_throwsCommandException() {
        ModelStubWithNoModuleClass modelStub = new ModelStubWithNoModuleClass();
        List<Index> indices = new ArrayList<>();

        AssignCommand assignCommand = new AssignCommand(indices, CS1101S);
        String expectedMessage = String.format(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                modelStub.getModuleClassList());

        assertThrows(CommandException.class, expectedMessage, () -> assignCommand.execute(modelStub));
    }

    @Test
    public void execute_indexOutOfRange_throwsCommandException() {
        ModelStubWithOneStudent modelStub = new ModelStubWithOneStudent();

        List<Index> indices = new ArrayList<>();
        indices.add(Index.fromOneBased(1));
        indices.add(Index.fromOneBased(2));

        AssignCommand assignCommand = new AssignCommand(indices, CS1101S);
        String expectedMessage = Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
        assertThrows(CommandException.class, expectedMessage, () -> assignCommand.execute(modelStub));
    }

    @Test
    public void execute_assignOneStudent_success() throws CommandException {
        ModelStubWithOneStudent modelStub = new ModelStubWithOneStudent();

        List<Index> indices = new ArrayList<>();
        indices.add(Index.fromOneBased(1));

        AssignCommand assignCommand = new AssignCommand(indices, CS1101S);

        Student expectedStudent = new StudentBuilder(ALICE).withModuleClasses(CS1101S).build();
        String expectedMessage = AssignCommand.getSuccessMessage(new ArrayList<>(List.of(expectedStudent)), CS1101S);

        assertEquals(expectedMessage, assignCommand.execute(modelStub).getFeedbackToUser());
        assertEquals(expectedStudent, modelStub.student);
    }

    @Test
    void execute_assignMultipleStudents_success() throws CommandException {
        ModelStubWithMultipleStudents modelStub = new ModelStubWithMultipleStudents();

        List<Index> indices = new ArrayList<>();
        indices.add(Index.fromOneBased(1));
        indices.add(Index.fromOneBased(2));

        AssignCommand assignCommand = new AssignCommand(indices, CS1101S);
        Student expectedStudent1 = new StudentBuilder(ALICE).withModuleClasses(CS1101S).build();
        Student expectedStudent2 = new StudentBuilder(BOB).withModuleClasses(CS1101S).build();
        String expectedMessage = AssignCommand.getSuccessMessage(
                new ArrayList<>(List.of(expectedStudent1, expectedStudent2)), CS1101S);
        assertEquals(expectedMessage, assignCommand.execute(modelStub).getFeedbackToUser());
        assertEquals(expectedStudent1, modelStub.students.get(0));
        assertEquals(expectedStudent2, modelStub.students.get(1));
    }

    @Test
    public void equals() {
        AssignCommand assignFirstCommand = new AssignCommand(List.of(Index.fromOneBased(1)), CS1101S);
        AssignCommand assignSecondCommand = new AssignCommand(List.of(Index.fromOneBased(2)), CS1101S);

        // same object -> returns true
        assertEquals(assignFirstCommand, assignFirstCommand);

        // same values -> returns true
        AssignCommand assignFirstCommandCopy = new AssignCommand(List.of(Index.fromOneBased(1)), CS1101S);
        assertEquals(assignFirstCommand, assignFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(assignFirstCommand, 1);

        // null -> returns false
        assertNotEquals(assignFirstCommand, null);

        // different student -> returns false
        assertNotEquals(assignFirstCommand, assignSecondCommand);
    }

    /**
     * A Model stub with one filtered student. Always says it has a module.
     */
    private static class ModelStubWithOneStudent extends ModelStub {

        // ALICE with no module classes
        private Student student = new StudentBuilder(ALICE).withModuleClasses().build();

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return FXCollections.observableArrayList(student);
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            requireAllNonNull(target, editedStudent);
            student = editedStudent;
        }

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return true;
        }

        @Override
        public ModuleClass getModuleClassWithSameName(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return moduleClass;
        }
    }

    /**
     * Model stub with multiple filtered students. Always says it has a module.
     */
    private static class ModelStubWithMultipleStudents extends ModelStub {

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
            requireAllNonNull(target, editedStudent);
            students.set(students.indexOf(target), editedStudent);
        }

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return true;
        }

        @Override
        public ModuleClass getModuleClassWithSameName(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return moduleClass;
        }
    }
}
