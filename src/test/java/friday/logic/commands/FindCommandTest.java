package friday.logic.commands;

import static friday.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static friday.logic.commands.CommandTestUtil.assertCommandSuccess;
import static friday.testutil.TypicalStudents.CARL;
import static friday.testutil.TypicalStudents.ELLE;
import static friday.testutil.TypicalStudents.FIONA;
import static friday.testutil.TypicalStudents.getTypicalFriday;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import friday.commons.core.GuiSettings;
import friday.model.Friday;
import friday.model.Model;
import friday.model.ModelManager;
import friday.model.ReadOnlyFriday;
import friday.model.ReadOnlyUserPrefs;
import friday.model.UserPrefs;
import friday.model.alias.Alias;
import friday.model.alias.ReservedKeyword;
import friday.model.student.NameContainsKeywordsPredicate;
import friday.model.student.Student;
import javafx.collections.ObservableList;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalFriday(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFriday(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getStudentList());
    }

    @Test
    public void execute_studentsFindByModel_findUnsuccessful() throws Exception {
        FindCommandTest.ModelStubAcceptingFinding modelStub = new FindCommandTest.ModelStubAcceptingFinding();
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        CommandResult commandResult = new FindCommand(predicate).execute(modelStub);
        System.out.println(commandResult);
        assertEquals("0 students listed!", commandResult.getFeedbackToUser());
    }
    @Test
    public void execute_multipleKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getStudentList());
    }

    @Test
    public void execute_multipleKeywords_oneStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(CARL), model.getStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
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
            //throw new AssertionError("This method should not be called.");
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

        @Override
        public String displayAliases() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accepts finding.
     */
    private class ModelStubAcceptingFinding extends FindCommandTest.ModelStub {
        private ObservableList<Student> students = getFriday().getStudentList();
        private Model model = new ModelManager(getTypicalFriday(), new UserPrefs());

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public void updateSortedStudentList(Comparator<Student> comparator) {
            NameContainsKeywordsPredicate predicate = preparePredicate(" ");
            requireNonNull(comparator);
            model.updateFilteredStudentList(predicate);
            students = model.getStudentList();
        }

        @Override
        public ReadOnlyFriday getFriday() {
            return new Friday();
        }
    }
}
