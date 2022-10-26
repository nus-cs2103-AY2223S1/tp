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
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.question.Question;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.QuestionBuilder;

public class MarkQCommandTest {

    @Test
    public void constructor_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkQCommand(null));
    }

    @Test
    public void execute_questionAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingQuestionsAdded modelStub = new ModelStubAcceptingQuestionsAdded();
        Question validQuestion = new QuestionBuilder().build();
        Index validIndex = new IndexBuilder().withIndex(1).build();


        CommandResult commandResult = new MarkQCommand(validIndex).execute(modelStub);

        assertEquals(String.format(MarkQCommand.MESSAGE_MARK_QUESTION_SUCCESS, validQuestion), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validQuestion), modelStub.questionsAdded);
    }


    @Test
    public void equals() {
        Question q1 = new QuestionBuilder().withDescription("Q1").build();
        Question q2 = new QuestionBuilder().withDescription("Q2").build();
        MarkQCommand markQ1Command = new MarkQCommand(q1);

        // same object -> returns true
        assertTrue(markQ1Command.equals(markQ1Command));

        // same values -> returns true
        MarkQCommand MarkQ1CommandCopy = new MarkQCommand(q1);
        assertTrue(markQ1Command.equals(MarkQ1CommandCopy));

        // different types -> returns false
        assertFalse(markQ1Command.equals(1));

        // null -> returns false
        assertFalse(markQ1Command.equals(null));
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
        public void addPerson(Person person) {

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
        public boolean hasPerson(Person person) {
            return false;
        }

        @Override
        public void deletePerson(Person target) {

        }

        @Override
        public void setPerson(Person target, Person editedPerson) {

        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {

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
     * A Model stub that contains 2 questions.
     */
    private class ModelStubWithQuestions extends ModelStub {
        private final Question q1;
        private final Question q2;

        ModelStubWithQuestions(Question q1, Question q2) {
            requireNonNull(q1);
            requireNonNull(q2);
            this.q1 = q1;
            this.q2 = q2;
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
