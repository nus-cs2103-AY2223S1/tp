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
import seedu.address.testutil.QuestionBuilder;

public class AddQCommandTest {

    @Test
    public void constructor_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddQCommand(null));
    }

    @Test
    public void execute_questionAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingQuestionAdded modelStub = new ModelStubAcceptingQuestionAdded();
        Question validQuestion = new QuestionBuilder().build();

        CommandResult commandResult = new AddQCommand(validQuestion).execute(modelStub);

        assertEquals(String.format(AddQCommand.MESSAGE_SUCCESS, validQuestion), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validQuestion), modelStub.questionsAdded);
    }

    @Test
    public void execute_duplicateQuestion_throwsCommandException() {
        Question validQuestion = new QuestionBuilder().build();
        AddQCommand addQCommand = new AddQCommand(validQuestion);
        ModelStub modelStub = new ModelStubWithQuestion(validQuestion);

        assertThrows(CommandException.class,
                AddQCommand.MESSAGE_DUPLICATE_QUESTION, () -> addQCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Question q1 = new QuestionBuilder().withDescription("Q1").build();
        Question q2 = new QuestionBuilder().withDescription("Q2").build();
        AddQCommand addFirstQuestionCommand = new AddQCommand(q1);
        AddQCommand addSecondQuestionCommand = new AddQCommand(q2);

        // same object -> returns true
        assertTrue(addFirstQuestionCommand.equals(addFirstQuestionCommand));

        // same values -> returns true
        AddQCommand addFirstQuestionCommandCopy = new AddQCommand(q1);
        assertTrue(addFirstQuestionCommand.equals(addFirstQuestionCommandCopy));

        // different types -> returns false
        assertFalse(addFirstQuestionCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstQuestionCommand.equals(null));

        // different question -> returns false
        assertFalse(addFirstQuestionCommand.equals(addSecondQuestionCommand));
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
     * A Model stub that contains a single question.
     */
    private class ModelStubWithQuestion extends ModelStub {
        private final Question question;

        ModelStubWithQuestion(Question question) {
            requireNonNull(question);
            this.question = question;
        }

        @Override
        public boolean hasQuestion(Question question) {
            requireNonNull(question);
            return this.question.isSameQuestion(question);
        }
    }

    /**
     * A Model stub that always accept the question being added.
     */
    private class ModelStubAcceptingQuestionAdded extends ModelStub {
        final ArrayList<Question> questionsAdded = new ArrayList<>();

        @Override
        public boolean hasQuestion(Question question) {
            requireNonNull(question);
            return questionsAdded.stream().anyMatch(question::isSameQuestion);
        }

        @Override
        public void addQuestion(Question question) {
            requireNonNull(question);
            questionsAdded.add(question);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
