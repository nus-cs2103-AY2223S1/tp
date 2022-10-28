package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import jeryl.fyp.commons.core.GuiSettings;
import jeryl.fyp.commons.core.index.Index;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.model.FypManager;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.ReadOnlyFypManager;
import jeryl.fyp.model.ReadOnlyUserPrefs;
import jeryl.fyp.model.student.Deadline;
import jeryl.fyp.model.student.DeadlineList;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.testutil.StudentBuilder;


public class AddStudentCommandTest {

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddStudentCommand(null));
    }

    @Test
    public void execute_studentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingStudentAdded modelStub = new ModelStubAcceptingStudentAdded();
        Student validStudent = new StudentBuilder().build();

        CommandResult commandResult = new AddStudentCommand(validStudent).execute(modelStub);

        assertEquals(String.format(AddStudentCommand.MESSAGE_SUCCESS, validStudent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStudent), modelStub.studentsAdded);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student validStudent = new StudentBuilder().build();
        AddStudentCommand addStudentCommand = new AddStudentCommand(validStudent);
        ModelStub modelStub = new ModelStubWithStudent(validStudent);

        assertThrows(CommandException.class, AddStudentCommand.MESSAGE_DUPLICATE_STUDENT, () ->
                addStudentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Student alice = new StudentBuilder().withStudentName("Alice").build();
        Student bob = new StudentBuilder().withStudentName("Bob").build();
        AddStudentCommand addAliceCommand = new AddStudentCommand(alice);
        AddStudentCommand addBobCommand = new AddStudentCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddStudentCommand addAliceCommandCopy = new AddStudentCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different student -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public Path getFypManagerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFypManagerFilePath(Path fypManagerFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFypManager(ReadOnlyFypManager newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFypManager getFypManager() {
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
        public boolean hasDeadline(Student student, Deadline deadline) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDeadline(Student student, Deadline deadline) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDeadline(Student student, Deadline deadline) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeadline(Student student, Deadline target, Deadline editedDeadline) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getUncompletedStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getCompletedStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public DeadlineList listDeadlineUnderStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Student getStudentByStudentId(StudentId studentId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Index getIndexByStudentId(StudentId studentId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public SortedList<Student> getSortedByProjectStatusUncompletedStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public SortedList<Student> getSortedByProjectNameUncompletedStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public SortedList<Student> getSortedCompletedStudentList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single student.
     */
    private class ModelStubWithStudent extends ModelStub {
        private final Student student;

        ModelStubWithStudent(Student student) {
            requireNonNull(student);
            this.student = student;
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return this.student.isSameStudentId(student);
        }
    }

    /**
     * A Model stub that always accept the student being added.
     */
    private class ModelStubAcceptingStudentAdded extends ModelStub {
        final ArrayList<Student> studentsAdded = new ArrayList<>();

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return studentsAdded.stream().anyMatch(student::isSameStudentId);
        }

        @Override
        public void addStudent(Student student) {
            requireNonNull(student);
            studentsAdded.add(student);
        }

        @Override
        public ReadOnlyFypManager getFypManager() {
            return new FypManager();
        }
    }

}
