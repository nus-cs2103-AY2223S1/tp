package friday.logic.commands;

import static friday.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import friday.commons.core.GuiSettings;
import friday.model.Friday;
import friday.model.Model;
import friday.model.ReadOnlyFriday;
import friday.model.ReadOnlyUserPrefs;
import friday.model.alias.Alias;
import friday.model.alias.ReservedKeyword;
import friday.model.student.Student;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class SortCommandTest {

    @Test
    public void constructor_nullComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null));
    }

    @Test
    public void execute_studentsSortedByModel_sortSuccessful() throws Exception {
        ModelStubAcceptingSorting modelStub = new ModelStubAcceptingSorting();
        Comparator<Student> comparator = (x, y) -> x.getName().compareTo(y.getName());

        CommandResult commandResult = new SortCommand(comparator).execute(modelStub);

        assertEquals(SortCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Comparator<Student> comparatorOne = (x, y) -> x.getName().compareTo(y.getName());
        Comparator<Student> comparatorTwo = (x, y) -> x.getConsultation().compareTo(y.getConsultation());
        SortCommand sortCommandOne = new SortCommand(comparatorOne);
        SortCommand sortCommandTwo = new SortCommand(comparatorTwo);

        // same object -> returns true
        assertTrue(sortCommandOne.equals(sortCommandOne));

        // same values -> returns true
        SortCommand sortCommandOneCopy = new SortCommand(comparatorOne);
        assertTrue(sortCommandOne.equals(sortCommandOneCopy));

        // different types -> returns false
        assertFalse(sortCommandOne.equals(1));

        // null -> returns false
        assertFalse(sortCommandOne.equals(null));

        // different student -> returns false
        assertFalse(sortCommandOne.equals(sortCommandTwo));
    }

    /**
     * A default model stub that have all the methods failing.
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
        public Path getFridayFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFridayFilePath(Path fridayFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFriday(ReadOnlyFriday newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFriday getFriday() {
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
        public ObservableList<Student> getStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedStudentList(Comparator<Student> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAlias(Alias alias) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAlias(String key) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAlias(Alias alias, ReservedKeyword keyword) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeAlias(Alias target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getKeyword(String key) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accepts sorting.
     */
    private class ModelStubAcceptingSorting extends ModelStub {
        private ObservableList<Student> students = getFriday().getStudentList();
        final SortedList<Student> sortedStudents = new SortedList<>(students);

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public void updateSortedStudentList(Comparator<Student> comparator) {
            requireNonNull(comparator);
            sortedStudents.setComparator(comparator);
            students = sortedStudents;
        }

        @Override
        public ReadOnlyFriday getFriday() {
            return new Friday();
        }
    }
}
