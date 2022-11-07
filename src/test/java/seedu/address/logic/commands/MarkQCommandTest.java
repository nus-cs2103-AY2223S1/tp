package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MarkQCommand.MESSAGE_MARK_QUESTION_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_QUESTION;
import static seedu.address.testutil.TypicalQuestions.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.question.Question;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.QuestionBuilder;

public class MarkQCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkQCommand(null));
    }

    @Test
    public void execute_questionMarkedByModel_markSuccessful() {
        Question questionToBeMarked = model.getFilteredQuestionList().get(INDEX_FIRST_QUESTION.getZeroBased());
        Question editedQuestion = new QuestionBuilder(questionToBeMarked)
                .withImportantTag(true)
                .build();
        MarkQCommand markQCommand = new MarkQCommand(INDEX_FIRST_QUESTION);

        String expectedMessage = String.format(MESSAGE_MARK_QUESTION_SUCCESS, editedQuestion);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setQuestion(model.getFilteredQuestionList().get(0), editedQuestion);

        assertCommandSuccess(markQCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_questionMarkedByModel_markFailure() throws Exception {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredQuestionList().size() + 1);
        MarkQCommand markQCommand = new MarkQCommand(outOfBoundIndex);

        assertCommandFailure(markQCommand, model, Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Question q1 = new QuestionBuilder().withDescription("Q1").build();
        ModelStubAcceptingQuestionsAdded modelStub = new ModelStubAcceptingQuestionsAdded();
        modelStub.addQuestion(q1);
        MarkQCommand markFirstQuestionCommand = new MarkQCommand(INDEX_FIRST_QUESTION);

        // same object -> returns true
        assertTrue(markFirstQuestionCommand.equals(markFirstQuestionCommand));

        // same values -> returns true
        MarkQCommand markFirstQuestionCommandCopy = new MarkQCommand(INDEX_FIRST_QUESTION);
        assertTrue(markFirstQuestionCommand.equals(markFirstQuestionCommandCopy));

        // different types -> returns false
        assertFalse(markFirstQuestionCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstQuestionCommand.equals(null));
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
     * A Model stub that contains 1 question.
     */
    private class ModelStubWithQuestion extends ModelStub {
        private final Question q1;

        ModelStubWithQuestion(Question q1) {
            requireNonNull(q1);
            this.q1 = q1;
        }

    }

    /**
     * A Model stub that always accept the questions being added.
     */
    private class ModelStubAcceptingQuestionsAdded extends ModelStub {
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
