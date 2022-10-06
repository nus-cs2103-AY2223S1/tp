package jarvis.logic.commands;

import static jarvis.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import jarvis.logic.commands.student.AddCommand;
import jarvis.model.student.Student;
import org.junit.jupiter.api.Test;

import jarvis.commons.core.GuiSettings;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.StudentBook;
import jarvis.model.Model;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.ReadOnlyUserPrefs;
import jarvis.testutil.PersonBuilder;
import javafx.collections.ObservableList;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Student validStudent = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validStudent).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validStudent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStudent), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Student validStudent = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validStudent);
        ModelStub modelStub = new ModelStubWithPerson(validStudent);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Student alice = new PersonBuilder().withName("Alice").build();
        Student bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
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
        public Path getStudentBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudentBookFilePath(Path studentBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudentBook(ReadOnlyStudentBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyStudentBook getStudentBook() {
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
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Student student;

        ModelStubWithPerson(Student student) {
            requireNonNull(student);
            this.student = student;
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return this.student.isSameStudent(student);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Student> personsAdded = new ArrayList<>();

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return personsAdded.stream().anyMatch(student::isSameStudent);
        }

        @Override
        public void addStudent(Student student) {
            requireNonNull(student);
            personsAdded.add(student);
        }

        @Override
        public ReadOnlyStudentBook getStudentBook() {
            return new StudentBook();
        }
    }

}
