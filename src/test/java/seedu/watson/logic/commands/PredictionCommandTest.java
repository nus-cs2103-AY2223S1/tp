package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.watson.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import seedu.watson.commons.core.GuiSettings;
import seedu.watson.logic.commands.exceptions.CommandException;
import seedu.watson.model.Model;
import seedu.watson.model.ReadOnlyDatabase;
import seedu.watson.model.ReadOnlyUserPrefs;
import seedu.watson.model.student.Name;
import seedu.watson.model.student.Student;
import seedu.watson.testutil.StudentBuilder;

public class PredictionCommandTest {

    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
            () -> new PredictionCommand(null, null, 0.0));
    }

    @Test
    public void equals() {
        Name name = new Name("Alice Yeoh");
        String subjectName = "Math";
        double futureDifficulty = 4.0;

        PredictionCommand predictionAliceYeohMathCommand =
            new PredictionCommand(name, subjectName, futureDifficulty);

        PredictionCommand predictionAliceYeohMathCommandCopy =
            new PredictionCommand(name, subjectName, futureDifficulty);

        // same object -> returns true
        assertEquals(predictionAliceYeohMathCommand, predictionAliceYeohMathCommand);

        // same values -> returns true
        assertEquals(predictionAliceYeohMathCommand, predictionAliceYeohMathCommandCopy);
    }

    @Test
    public void testExecute() throws CommandException {
        ModelStub modelStub = new ModelStub();
        PredictionCommand predictionCommand = new PredictionCommand(new Name("Alice"), "MATH", 4.0);
        assertEquals("Grade prediction for Alice for their next MATH assessment is 71.58 percent",
            predictionCommand.execute(modelStub).getGradePredicted());
    }

    /**
     * A default model stub that have all methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyDatabase getDatabase() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyDatabase newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Student getStudentByName(Name name) {
            return new StudentBuilder().withName("Alice").build();
        }

        @Override
        public void deletePerson(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Student> createPersonIsInClassPredicate(String studentClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortListByGrade(boolean isInAscending, String subjectName) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single student.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Student student;

        ModelStubWithPerson(Student student) {
            requireNonNull(student);
            this.student = student;
        }

        @Override
        public boolean hasPerson(Student student) {
            requireNonNull(student);
            return this.student.isSamePerson(student);
        }
    }
}
