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
import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.stubs.ModelStub;
import seedu.taassist.model.stubs.ModelStubWithNoModuleClass;
import seedu.taassist.model.student.Student;
import seedu.taassist.testutil.StudentBuilder;

class UnassignCommandTest {

    @Test
    public void execute_noModuleClass_throwsCommandException() {
        ModelStubWithNoModuleClass modelStub = new ModelStubWithNoModuleClass();
        List<Index> indices = new ArrayList<>();

        UnassignCommand unassignCommand = new UnassignCommand(indices, CS1101S);
        String expectedMessage = String.format(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                modelStub.getModuleClassList());

        assertThrows(CommandException.class, expectedMessage, () -> unassignCommand.execute(modelStub));
    }

    @Test
    public void execute_indexOutOfRange_throwsCommandException() {
        ModelStubOneStudentAndModule modelStub = new ModelStubOneStudentAndModule();

        List<Index> indices = new ArrayList<>();
        indices.add(Index.fromOneBased(1));
        indices.add(Index.fromOneBased(2));

        UnassignCommand unassignCommand = new UnassignCommand(indices, CS1101S);
        String expectedMessage = Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;

        assertThrows(CommandException.class, expectedMessage, () -> unassignCommand.execute(modelStub));
    }

    @Test
    public void execute_unassignOneStudent_success() throws CommandException {
        ModelStubOneStudentAndModule modelStub = new ModelStubOneStudentAndModule();
        ModuleClass moduleClass = modelStub.getModuleClassList().get(0);

        List<Index> indices = new ArrayList<>();
        indices.add(Index.fromOneBased(1));

        UnassignCommand unassignCommand = new UnassignCommand(indices, moduleClass);
        Student expectedStudent = new StudentBuilder(ALICE).withModuleClasses().build();
        String expectedMessage = UnassignCommand.getSuccessMessage(new ArrayList<>(List.of(expectedStudent)),
                moduleClass);

        CommandResult commandResult = unassignCommand.execute(modelStub);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedStudent, modelStub.student);
    }

    @Test
    public void execute_unassignMultipleStudents_success() throws CommandException {
        ModelStubMultipleStudentsOneModule modelStub = new ModelStubMultipleStudentsOneModule();
        ModuleClass moduleClass = modelStub.getModuleClassList().get(0);

        List<Index> indices = new ArrayList<>();
        indices.add(Index.fromOneBased(1));
        indices.add(Index.fromOneBased(2));

        UnassignCommand unassignCommand = new UnassignCommand(indices, moduleClass);
        Student expectedStudent1 = new StudentBuilder(ALICE).withModuleClasses().build();
        Student expectedStudent2 = new StudentBuilder(BOB).withModuleClasses().build();
        String expectedMessage = UnassignCommand.getSuccessMessage(new ArrayList<>(
                List.of(expectedStudent1, expectedStudent2)), moduleClass);

        CommandResult commandResult = unassignCommand.execute(modelStub);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedStudent1, modelStub.students.get(0));
        assertEquals(expectedStudent2, modelStub.students.get(1));
    }


    @Test
    public void equals() {
        UnassignCommand unassignFirstCommand = new UnassignCommand(List.of(Index.fromOneBased(1)), CS1101S);
        UnassignCommand unassignSecondCommand = new UnassignCommand(List.of(Index.fromOneBased(2)), CS1101S);

        // same object -> returns true
        assertEquals(unassignFirstCommand, unassignFirstCommand);

        // same values -> returns true
        UnassignCommand unassignFirstCommandCopy = new UnassignCommand(List.of(Index.fromOneBased(1)), CS1101S);
        assertEquals(unassignFirstCommand, unassignFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(unassignFirstCommand, 1);

        // null -> returns false
        assertNotEquals(unassignFirstCommand, null);

        // different student -> returns false
        assertNotEquals(unassignFirstCommand, unassignSecondCommand);
    }

    /**
     * A Model stub with one filtered student with an assigned class: CS1101S.
     * Always says it has a module.
     */
    private static class ModelStubOneStudentAndModule extends ModelStub {

        private Student student = new StudentBuilder(ALICE).withModuleClasses(CS1101S).build();

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return FXCollections.observableArrayList(student);
        }

        @Override
        public ObservableList<ModuleClass> getModuleClassList() {
            return FXCollections.observableArrayList(List.of(CS1101S));
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            student = editedStudent;
        }

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            return true;
        }

        @Override
        public ModuleClass getModuleClassWithSameName(ModuleClass moduleClass) {
            return moduleClass;
        }
    }

    /**
     * Model stub with multiple filtered students with one same assigned class.
     * Always says it has a module.
     */
    private static class ModelStubMultipleStudentsOneModule extends ModelStub {

        private List<Student> students = new ArrayList<>();

        public ModelStubMultipleStudentsOneModule() {
            // ALICE and BOB with one module class
            students.add(new StudentBuilder(ALICE).withModuleClasses(CS1101S).build());
            students.add(new StudentBuilder(BOB).withModuleClasses(CS1101S).build());
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return FXCollections.observableArrayList(students);
        }

        @Override
        public ObservableList<ModuleClass> getModuleClassList() {
            return FXCollections.observableArrayList(List.of(CS1101S));
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            requireAllNonNull(target, editedStudent);
            students.set(students.indexOf(target), editedStudent);
        }

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            return true;
        }

        @Override
        public ModuleClass getModuleClassWithSameName(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return moduleClass;
        }
    }
}
