package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.question.Question;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.TutorialBuilder;

public class AddTutorialCommandTest {

    @Test
    public void constructor_nullTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTutorialCommand(null));
    }

    @Test
    public void execute_tutorialAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTutorialAdded modelStub = new ModelStubAcceptingTutorialAdded();
        Tutorial validTutorial = new TutorialBuilder().build();

        CommandResult commandResult = new AddTutorialCommand(validTutorial).execute(modelStub);

        assertEquals(String.format(AddTutorialCommand.MESSAGE_SUCCESS, validTutorial),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTutorial), modelStub.tutorialsAdded);
    }

    @Test
    public void execute_duplicateTutorial_throwsCommandException() {
        Tutorial validTutorial = new TutorialBuilder().build();
        AddTutorialCommand addTutorialCommand = new AddTutorialCommand(validTutorial);
        ModelStub modelStub = new ModelStubWithTutorial(validTutorial);

        assertThrows(CommandException.class,
                AddTutorialCommand.MESSAGE_DUPLICATE_TUTORIAL, () -> addTutorialCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tutorial tutorial1 = new TutorialBuilder().withTime("2022-12-01 1400").build();
        Tutorial tutorial2 = new TutorialBuilder().withTime("2022-12-01 1600").build();
        AddTutorialCommand addTutorial1Command = new AddTutorialCommand(tutorial1);
        AddTutorialCommand addTutorial2Command = new AddTutorialCommand(tutorial2);

        // same object -> returns true
        assertTrue(addTutorial1Command.equals(addTutorial1Command));

        // same values -> returns true
        AddTutorialCommand addTutorial1CommandCopy = new AddTutorialCommand(tutorial1);
        assertTrue(addTutorial1Command.equals(addTutorial1CommandCopy));

        // different types -> returns false
        assertFalse(addTutorial1Command.equals(1));

        // null -> returns false
        assertFalse(addTutorial1Command.equals(null));

        // different tutorial -> returns false
        assertFalse(addTutorial1Command.equals(addTutorial2Command));
    }

    /**
     * A default model stub that have all methods failing.
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public boolean hasStudent(Student student) {
            return false;
        }

        @Override
        public void deleteStudent(Student target) {

        }

        @Override
        public void addStudent(Student student) {

        }

        @Override
        public void setStudent(Student target, Student editedStudent) {

        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return null;
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {

        }

        @Override
        public boolean hasQuestion(Question question) {
            return false;
        }

        @Override
        public void deleteQuestion(Question target) {

        }

        @Override
        public void addQuestion(Question question) {

        }

        @Override
        public void setQuestion(Question target, Question editedQuestion) {

        }

        @Override
        public void markQuestion(Index index) {

        }

        @Override
        public void unmarkQuestion(Index index) {

        }

        @Override
        public ObservableList<Question> getFilteredQuestionList() {
            return null;
        }

        @Override
        public void updateFilteredQuestionList(Predicate<Question> predicate) {

        }

        //tutorial
        @Override
        public void addTutorial(Tutorial tutorial) {

        }

        @Override
        public boolean hasTutorial(Tutorial tutorial) {
            return false;
        }

        @Override
        public void deleteTutorial(Tutorial target) {

        }

        @Override
        public void setTutorial(Tutorial target, Tutorial editedTutorial) {

        }

        @Override
        public ObservableList<Tutorial> getFilteredTutorialList() {
            return null;
        }

        @Override
        public void updateFilteredTutorialList(Predicate<Tutorial> predicate) {

        }
    }

    /**
     * A Model stub that contains a single tutorial.
     */
    private class ModelStubWithTutorial extends ModelStub {
        private final Tutorial tutorial;

        ModelStubWithTutorial(Tutorial tutorial) {
            requireNonNull(tutorial);
            this.tutorial = tutorial;
        }

        @Override
        public boolean hasTutorial(Tutorial tutorial) {
            requireNonNull(tutorial);
            return this.tutorial.isSameTutorial(tutorial);
        }
    }

    /**
     * A Model stub that always accept the tutorial being added.
     */
    private class ModelStubAcceptingTutorialAdded extends ModelStub {
        final ArrayList<Tutorial> tutorialsAdded = new ArrayList<>();

        @Override
        public boolean hasTutorial(Tutorial tutorial) {
            requireNonNull(tutorial);
            return tutorialsAdded.stream().anyMatch(tutorial::isSameTutorial);
        }

        @Override
        public void addTutorial(Tutorial tutorial) {
            requireNonNull(tutorial);
            tutorialsAdded.add(tutorial);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
